package facade;

import enums.ApiEnum;
import external.Receiver;
import mappers.RestMapper;
import rest.steam.MarketItemList;
import steam.ItemObj;
import steam.MarketObj;
import utils.MarketItemScanner;

import java.util.List;
import java.util.Map;

public class SteamFacade {

    private Receiver receiver = new Receiver();
    private MarketItemScanner scanner = new MarketItemScanner(receiver);

    public MarketObj getMarketObj(String link, int count) {
        link = link.replaceAll(ApiEnum.STEAM_COMMUNITY_ADDRESS.getPath(), "").replaceAll(" ", "%20").replaceAll("™", "%E2%84%A2");
        return receiver.getMarketObject(link, count);
    }

    public ItemObj getItemObj(String link) {
        link = link.replaceAll(" ", "%20");
        return receiver.getItem(link);
    }

    public MarketItemList getMarketScanObj(String link, String filter) {
        link = link.replaceAll(ApiEnum.STEAM_COMMUNITY_ADDRESS.getPath(), "").replaceAll(" ", "%20").replaceAll("™", "%E2%84%A2");

        List<ItemObj> items = scanner.scan(link, 1000);

        return RestMapper.mapItemObjList2MarketItemList(items);
    }
}
