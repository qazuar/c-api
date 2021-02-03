package utils;

import external.Receiver;
import steam.ItemObj;
import steam.MarketItemObj;
import steam.MarketObj;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MarketItemScanner {

    private Receiver receiver;

    public MarketItemScanner(Receiver receiver) {
        this.receiver = receiver;
    }

    /**
     * This is a POC
     * Currently it will just look for kato 2014 stickers (hardcoded)
     *
     * @param link
     * @param count
     * @return
     */
    public List<ItemObj> scan(String link, int count) {
        List<ItemObj> items = receiver.getItems(link, count);

        List<ItemObj> filtered = new ArrayList<>();

        for (ItemObj i : items) {
            if (hasRareStickers(i)) {
                filtered.add(i);
            }

            if (hasRarePattern(i)) {
                filtered.add(i);
            }
        }

        return filtered;
    }

    private boolean hasRareStickers(ItemObj item) {
        for (String s : item.getStickers()) {
            if (s != null) {
                s = s.toLowerCase();

                if (s.contains("katowice 2014")) {
                    return true;
                }

                if (s.contains("crown")) {
                    return true;
                }

                if (s.contains("katowice 2015")) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean hasRarePattern(ItemObj item) {
        return false;
    }
}
