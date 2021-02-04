package facade;

import enums.ApiEnum;
import enums.ScanFilter;
import external.Receiver;
import mappers.RestMapper;
import rest.steam.MarketItemList;
import steam.ItemObj;
import utils.MarketItemScanner;
import utils.Misc;

import java.util.List;

public class SteamFacade {

    private Receiver receiver = new Receiver();
    private MarketItemScanner scanner = new MarketItemScanner(receiver);

    public ItemObj getItemObj(String link) {
        return receiver.getItem(Misc.encodeMarketLink(link));
    }

    public MarketItemList getMarketItemList(String link, String filter, Integer count) {
        return RestMapper.mapItemObjList2MarketItemList(scanner.scan(Misc.encodeMarketLink(link), count, filter));
    }
}
