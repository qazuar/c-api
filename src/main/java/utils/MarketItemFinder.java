package utils;

import enums.ExteriorEnum;
import enums.MarketEnum;
import external.Receiver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import steam.ItemObj;
import utils.seeds.AKBlueGemSeedFinder;
import utils.seeds.FiveSevenBlueGemSeedFinder;
import utils.seeds.SeedFinder;

import java.util.ArrayList;
import java.util.List;

public class MarketItemFinder {

    private final Logger logger = LoggerFactory.getLogger(MarketItemFinder.class);

    private final Receiver receiver;

    private final SeedFinder AK_BLUEGEM_FINDER = new AKBlueGemSeedFinder();
    private final SeedFinder FIVESEVNE_BLUEGEM_FINDER = new FiveSevenBlueGemSeedFinder();

    public MarketItemFinder(Receiver receiver) {
        this.receiver = receiver;
    }

    public List<ItemObj> find(String item, String type, float minFloat, float maxFloat) {
        List<ItemObj> items = new ArrayList<>();
        String key = item + ":" + type;

        switch (key) {
            case "ak47:bluegem": {
                items.addAll(findAK47BlueGems());
                break;
            }

            case "fiveseven:bluegem": {
                items.addAll(findFiveSevenBlueGems());
                break;
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

    private List<ItemObj> findAK47BlueGems() {
        logger.debug("Searching for AK-47 bluegems");

        List<ItemObj> items = new ArrayList<>();

        for (boolean stattrak : new boolean[]{true, false}) { // Check both stattrak and non-stattrak
            for (ExteriorEnum e : ExteriorEnum.values()) {
                String link = MarketEnum.AK47_CASE_HARDENED.getMarketLink(e.getUrl(), stattrak);
                int count = 150; // Currently, highest number of aks is 142 on any page
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

    private List<ItemObj> findFiveSevenBlueGems() {
        logger.debug("Searching for Five-Seven bluegems");

        List<ItemObj> items = new ArrayList<>();

        for (boolean stattrak : new boolean[]{true, false}) { // Check both stattrak and non-stattrak
            for (ExteriorEnum e : ExteriorEnum.values()) {
                String link = MarketEnum.FIVESEVEN.getMarketLink(e.getUrl(), stattrak);
                int count = 200; // Currently, highest number of fivesevens is 188 on any page
                items.addAll(receiver.getItems(link, count));
            }
        }

        List<ItemObj> filtered = new ArrayList<>();

        for (ItemObj i : items) {
            if (FIVESEVNE_BLUEGEM_FINDER.isRareSeed(i.getPaintSeed())) {
                i.setPaintSeedName(FIVESEVNE_BLUEGEM_FINDER.getSeedName(i.getPaintSeed()));
                filtered.add(i);
            }
        }

        return filtered;
    }
}
