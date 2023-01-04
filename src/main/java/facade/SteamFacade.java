package facade;

import external.Receiver;
import mappers.RestMapper;
import rest.steam.MarketItemList;
import steam.ItemObj;
import utils.MarketItemScanner;
import utils.Misc;

public class SteamFacade {

    private Receiver receiver = new Receiver();
    private MarketItemScanner scanner = new MarketItemScanner(receiver);

    public ItemObj getItemObj(String link) {
        return receiver.getItem(Misc.encodeMarketLink(link));
    }

    public MarketItemList getMarketItemList(String link, Integer count, float minFloat, float maxFloat, int[] seeds, String[] stickers) {
        return RestMapper.mapItemObjList2MarketItemList(
                scanner.scan(Misc.encodeMarketLink(link), count, minFloat, maxFloat, seeds, stickers)
        );
    }
}
