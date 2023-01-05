package facade;

import external.Receiver;
import mappers.RestMapper;
import rest.steam.MarketItemList;
import steam.ItemObj;
import utils.MarketItemFinder;
import utils.MarketItemScanner;
import utils.Misc;

public class SteamFacade {

    private final Receiver receiver = new Receiver();

    private final MarketItemScanner scanner = new MarketItemScanner(receiver);
    private final MarketItemFinder finder = new MarketItemFinder(receiver);

    public ItemObj getItemObj(String link) {
        return receiver.getItem(Misc.encodeMarketLink(link));
    }

    public MarketItemList getMarketItemList(String link, Integer count, float minFloat, float maxFloat, int[] seeds, String[] stickers) {
        return RestMapper.mapItemObjList2MarketItemList(
                scanner.scan(Misc.encodeMarketLink(link), count, minFloat, maxFloat, seeds, stickers)
        );
    }

    public MarketItemList findMarketItemList(String item, String type, float minFloat, float maxFloat) {
        return RestMapper.mapItemObjList2MarketItemList(
                finder.find(item, type, minFloat, maxFloat)
        );
    }
}
