package utils;

import enums.RareStickerEnum;
import enums.ScanFilter;
import external.Receiver;
import steam.ItemObj;

import java.util.ArrayList;
import java.util.List;

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
    public List<ItemObj> scan(String link, int count, String filter) {
        List<ItemObj> items = receiver.getItems(link, count);

        List<ItemObj> filtered = new ArrayList<>();

        ScanFilter scanFilter = ScanFilter.getFilter(filter);

        if (scanFilter == null) {
            return items;
        } else if (scanFilter.equals(ScanFilter.STICKERS)) {
            for (ItemObj i : items) {
                if (hasRareStickers(i)) {
                    filtered.add(i);
                }
            }
        } else if (scanFilter.equals(ScanFilter.PATTERN)) {
            for (ItemObj i : items) {
                if (hasRarePattern(i)) {
                    filtered.add(i);
                }
            }
        }

        return filtered;
    }

    private boolean hasRareStickers(ItemObj item) {
        for (String s : item.getStickers()) {
            if (s != null) {
                s = s.toLowerCase();

                if (s.contains("holo") || s.contains("foil")) {
                    for (RareStickerEnum r : RareStickerEnum.values()) {
                        if (s.contains(r.getName())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private boolean hasRarePattern(ItemObj item) {
        return false;
    }
}
