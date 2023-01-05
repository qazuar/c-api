package utils;

import enums.ApiEnum;
import enums.ExteriorEnum;
import enums.MarketEnum;
import external.Receiver;
import steam.ItemObj;
import utils.seeds.AKBlueGemSeedFinder;
import utils.seeds.SeedFinder;

import java.util.ArrayList;
import java.util.List;

public class MarketItemFinder {

    private Receiver receiver;

    private final SeedFinder AK_BLUEGEM_FINDER = new AKBlueGemSeedFinder();

    public MarketItemFinder(Receiver receiver) {
        this.receiver = receiver;
    }

    public List<ItemObj> find(String item, String type, float minFloat, float maxFloat) {
        List<ItemObj> items = new ArrayList<>();
        String key = item + ":" + type;

        switch (key) {
            case "ak47:bluegem": {
                items.addAll(findAK47BlueGem());
            }

            default: { }
        }

        List<ItemObj> filtered = new ArrayList<>();

        for (ItemObj i : items) {
            // Float check
            float iFloat = Float.parseFloat(i.getFloatValue());
            if (!(iFloat >= minFloat && iFloat <= maxFloat)) { continue; }

            filtered.add(i);
        }

        return filtered;
    }

    private List<ItemObj> findAK47BlueGem() {
        List<ItemObj> items = new ArrayList<>();

        for (boolean stattrak : new boolean[]{true, false}) { // Check both stattrak and non-stattrak
            for (ExteriorEnum e : ExteriorEnum.values()) {
                String link = MarketEnum.AK47_CASE_HARDENED.getMarketLink(e.getUrl(), stattrak);
                int count = 150; // Currently, highest number of aks is 142 on any page, thus 150 makes sense as a limit.
                items.addAll(receiver.getItems(link, count));
            }
        }

        List<ItemObj> filtered = new ArrayList<>();

        for (ItemObj i : items) {
            if (AK_BLUEGEM_FINDER.isRareSeed(i.getPaintSeed())) {
                i.setPaintSeedName(AK_BLUEGEM_FINDER.getSeedName(i.getPaintSeed()));
                filtered.add(i);
            }
        }

        return filtered;
    }
}
