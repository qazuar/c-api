package external;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import enums.ApiEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steam.ItemObj;
import steam.MarketItemObj;
import steam.MarketObj;
import steam.Sticker;
import utils.Misc;

import java.math.BigDecimal;
import java.util.*;

public class Receiver {

    private final Logger logger = LoggerFactory.getLogger(Receiver.class);

    private final int STEAM_MARKET_LIST_LIMIT = 100;

    private final Map<String, ItemObj> cache;
    private final Connector connector;

    public Receiver() {
        this.cache = new HashMap<>();
        this.connector = new Connector(null,null);
    }

    public List<ItemObj> getItems(String marketPageLink, int count) {
        // Steam market page has a limit of 100 per page
        // Count has to be split into segments of 1-100, 101-200 etc.

        if (count > STEAM_MARKET_LIST_LIMIT) {
            List<ItemObj> items = new ArrayList<>();

            for (int x = 0; x < Math.ceil(count / (double) STEAM_MARKET_LIST_LIMIT); x++) {
                int start = (x * STEAM_MARKET_LIST_LIMIT);
                List<ItemObj> tItems = getItems(marketPageLink, start, Math.min(STEAM_MARKET_LIST_LIMIT, count - start));

                if (tItems.size() < STEAM_MARKET_LIST_LIMIT) {
                    logger.debug("Stopped fetching due to count-exceed");
                    break;
                }

                items.addAll(tItems);
            }

            return items;
        }

        return getItems(marketPageLink, 0, count);
    }

    private List<ItemObj> getItems(String marketPageLink, int start, int count) {
        String queryStartCount = "?query=&start=" + start + "&count=" + count;

        List<ItemObj> items = new ArrayList<>();

        Request request = Request.newRequest();
        request.setServer(ApiEnum.STEAM_COMMUNITY_ADDRESS.getPath());

        connector.get(request, marketPageLink + queryStartCount);

        Map<String, List<String>> map = Misc.steamHtmlToMap(request.getResponseXml());

        int index = 0;
        int listId = start + 1;

        for (String link : map.get("links")) {
            ItemObj item = getItem(ApiEnum.STEAM_RUN_CSGO_PREFIX.getPath() + link.replace("%assetid%", map.get("assetids").get(index)));
            item.setPrice(map.get("prices").get(index));
            item.setListId(String.valueOf(listId));

            items.add(item);

            index++;
            listId++;
        }

        logger.info(String.format("Retrieved %s items from %s", items.size(), marketPageLink));

        return items;
    }

    public ItemObj getItem(String inspectLink) {
        ItemObj item = cache.get(inspectLink);

        item = item == null ? fetch(inspectLink) : item;

        return item;
    }

    private ItemObj fetch(String inspectLink) {
        logger.info("Fetching item: " + inspectLink);
        
        Request request = Request.newRequest();

        request.setServer(ApiEnum.CSGOFLOAT_API.getPath());
        connector.get(request, "?url=" + inspectLink);

        Gson gson = new Gson();

        LinkedTreeMap<String, LinkedTreeMap<String, String>> head = gson.fromJson(request.getResponseXml(), LinkedTreeMap.class);
        LinkedTreeMap<String, String> map = null;

        for (String iteminfo : head.keySet()) {
            map = head.get(iteminfo);
        }

        ItemObj item = map2ItemObj(map);

        cache.put(inspectLink, item);

        return item;
    }

    private ItemObj map2ItemObj(LinkedTreeMap<String, String> map) {
        if (map == null) {
            return null;
        }

        ItemObj item = new ItemObj();

        //for (String key : map.keySet()) {
        //    System.out.println(String.format("Key: %s -> Value: %s", key, map.get(key)));
        //}

        item.setOrigin(String.format("%s", map.get("origin")));
        item.setQuality(String.format("%s", map.get("quality")));
        item.setRarity(String.format("%s", map.get("rarity")));
        item.setPaintSeed(String.format("%s", map.get("paintseed")));
        item.setDefinitionIndex(String.format("%s", map.get("defindex")));
        item.setPaintIndex(String.format("%s", map.get("paintindex")));
        item.setFloatValue(String.format("%s", map.get("floatvalue")));
        item.setImageUrl(map.get("imageurl"));
        item.setMinFloat(String.format("%s", map.get("min")));
        item.setMaxFloat(String.format("%s", map.get("max")));
        item.setWeaponType(map.get("weapon_type"));
        item.setItemName(map.get("item_name"));
        item.setRarityName(map.get("rarity_name"));
        item.setQualityName(map.get("quality_name"));
        item.setOriginName(map.get("origin_name"));
        item.setWearName(map.get("wear_name"));
        item.setFullItemName(map.get("full_item_name"));

        item.setFullItemNameClean(item.getFullItemName().split("\\(")[0]);
        item.setExterior(item.getFullItemName().split("\\(")[1].replace(")", ""));

        // A safety fix for really low float values
        item.setFloatValue(BigDecimal.valueOf(Double.valueOf(item.getFloatValue())).toString());

        // Stickers is of type ArrayList and will cause an exception when unless formatted into a string
        List<Sticker> stickers = Misc.mapString2StickerList(String.format("%s", map.get("stickers")));

        item.getStickers().addAll(stickers);

        return item;
    }

    public MarketObj getMarketObject(String path, int count) {
        String queryStartCount = "?query=&start=0&count=" + count;

        MarketObj obj = new MarketObj();

        Request request = Request.newRequest();
        request.setServer(ApiEnum.STEAM_COMMUNITY_ADDRESS.getPath());

        connector.get(request, path + queryStartCount);

        Map<String, List<String>> map = Misc.steamHtmlToMap(request.getResponseXml());

        for (String s : request.getStrings()) {
            if (s.contains("var line1=[[")) { // Line is technically var line1=[["Nov..
                String salesHistory = s.replace("var line1=", "").trim();
                String[] salesHistoryArray = salesHistory.split("],");
                obj.addMarketSale(salesHistoryArray);
                break;
            }
        }

        int index = 0;
        int listId = 1;

        for (String link : map.get("links")) {
            MarketItemObj item = new MarketItemObj(listId, ApiEnum.STEAM_RUN_CSGO_PREFIX.getPath() + link.replace("%assetid%", map.get("assetids").get(index)), map.get("prices").get(index));
            obj.getItems().add(item);

            index++;
            listId++;
        }

        return obj;
    }
}
