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

    public List<ItemObj> scan(String link, int count, String[] filters) {
        List<ItemObj> items = receiver.getItems(link, count);

        if (ScanFilter.hasFilter(filters, ScanFilter.ALL)) {
            return items;
        }

        // Filtered items
        List<ItemObj> filtered = new ArrayList<>();

        for (ItemObj i : items) {
            boolean add = true;

            if (ScanFilter.hasFilter(filters, ScanFilter.FLOAT)) {
                add = hasLowFloat(i);
            }

            if (ScanFilter.hasFilter(filters, ScanFilter.STICKERS)) {
                add = hasStickers(i);
            }

            if (add) { filtered.add(i); }
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

    private boolean hasStickers(ItemObj item) {
        return !item.getStickers().isEmpty();
    }

    private boolean hasLowFloat(ItemObj item) {
        Double fv = Double.parseDouble(item.getFloatValue());

        if (fv < 0.07) { // FN
            return fv < 0.02;
        } else if (fv < 0.15) { // MW
            return fv < 0.1;
        } else if (fv < 0.38) { // FT
            return fv < 0.2;
        } else { // WW/BS is not considered low, but still return true to avoid 0 results.
            return true;
        }
    }
}
