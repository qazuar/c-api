package utils;

import enums.RareStickerEnum;
import enums.ScanFilter;
import external.Receiver;
import steam.ItemObj;
import steam.Sticker;

import java.util.ArrayList;
import java.util.List;

public class MarketItemScanner {

    private final String HOLO = "holo";
    private final String FOIL = "foil";
    private final String GOLD = "gold";

    private Receiver receiver;

    public MarketItemScanner(Receiver receiver) {
        this.receiver = receiver;
    }

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
        for (Sticker s : item.getStickers()) {
            String name = s.getName().toLowerCase();

            if (name.contains(HOLO) || name.contains(FOIL) || name.contains(GOLD)) {
                for (RareStickerEnum r : RareStickerEnum.values()) {
                    if (name.contains(r.getName())) {
                        return true;
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
