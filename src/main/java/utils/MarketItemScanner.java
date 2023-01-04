package utils;

import external.Receiver;
import steam.ItemObj;
import steam.Sticker;

import java.util.ArrayList;
import java.util.List;

public class MarketItemScanner {

    private Receiver receiver;

    public MarketItemScanner(Receiver receiver) {
        this.receiver = receiver;
    }

    public List<ItemObj> scan(String link, int count, float minFloat, float maxFloat, int[] seeds, String[] stickers) {
        List<ItemObj> items = receiver.getItems(link, count);

        // Filtered items
        List<ItemObj> filtered = new ArrayList<>();

        for (ItemObj i : items) {
            // Float check
            float iFloat = Float.parseFloat(i.getFloatValue());
            if (!(iFloat >= minFloat && iFloat <= maxFloat)) { continue; }

            // Seed check
            if (seeds.length > 0) {
                boolean seedMatch = false;

                for (int s : seeds) {
                    if (s == i.getPaintSeed()) {
                        seedMatch = true;
                    }
                }

                if (!seedMatch) { continue; }
            }

            // Sticker check
            if (stickers.length > 0) {
                if (i.getStickers().isEmpty()) { continue; }

                boolean stickerMatch = false;

                for (Sticker sticker : i.getStickers()) {
                    for (String sf : stickers) {
                        for (String ss : sticker.getName().split(" ")) {
                            if (ss.toLowerCase().contains(sf.trim().toLowerCase())) {
                                stickerMatch = true;
                            }
                        }
                    }
                }

                if (!stickerMatch) { continue ; }
            }

            filtered.add(i);
        }

        return filtered;
    }
}
