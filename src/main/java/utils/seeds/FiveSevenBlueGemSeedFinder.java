package utils.seeds;

public class FiveSevenBlueGemSeedFinder implements SeedFinder {

    private final int[] TIER1_SEEDS = {278,363,690,868};
    private final int[] TIER2_SEEDS = {72,151,215,700,770,849,888,189,25};
    private final int[] TIER3_SEEDS = {103,381,387,555,905,28,179,122,955};

    @Override
    public boolean isRareSeed(int seed) {
        for (int i : TIER1_SEEDS) {
            if (i == seed) {
                return true;
            }
        }

        for (int i : TIER2_SEEDS) {
            if (i == seed) {
                return true;
            }
        }

        for (int i : TIER3_SEEDS) {
            if (i == seed) {
                return true;
            }
        }

        return false;
    }

    @Override
    public String getSeedName(int seed) {
        int rank = 1;

        for (int i : TIER1_SEEDS) {
            if (i == seed) {
                return "Tier 1 bluegem #" + rank ;
            }

            rank ++;
        }

        rank = 1;

        for (int i : TIER2_SEEDS) {
            if (i == seed) {
                return "Tier 2 bluegem #" + rank;
            }

            rank ++;
        }

        rank = 1;

        for (int i : TIER3_SEEDS) {
            if (i == seed) {
                return "Tier 3 bluegem #" + rank;
            }

            rank ++;
        }

        return "undefined seed name";
    }
}
