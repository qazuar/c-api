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

    public List<ItemObj> find(String item, String type) {
        switch (type) {
            case "bluegem": {
                return findBlueGem(item);
            }

            default: {
                return new ArrayList<>();
            }
        }
    }

    private List<ItemObj> findBlueGem(String item) {
        switch (item) {
            case "ak47": {
                return findAK47BlueGem();
            }

            default: {
                return new ArrayList<>();
            }
        }
    }

    private List<ItemObj> findAK47BlueGem() {
        List<ItemObj> items = new ArrayList<>();

        for (ExteriorEnum e : ExteriorEnum.values()) {
            String link = MarketEnum.AK47_CASE_HARDENED.getMarketLink(e.getUrl(), false);
            int count = 150;
            items.addAll(receiver.getItems(link, count));
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
