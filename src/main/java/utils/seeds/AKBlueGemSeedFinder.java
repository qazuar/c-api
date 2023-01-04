package utils.seeds;

public class AKBlueGemSeedFinder implements SeedFinder {

    private final int[] TIER1_SEEDS = {661,321,555,955,760,592,387,151,168,828,179,809,617,670};
    private final int[] TIER2_SEEDS = {
            532,341,935,541,4,733,310,922,512,519,323,713,715,525,571,985,575,578,708,577,978,586,969,961,557,950,798,996,791,770,775,754,752,381,382,375,363,605,205,823,216,172,627,
            413,220,228,189,426,429,428,610,611,112,849,844,430,126,122,442,139,862,868,450,147,856,463,689,888,887,872,695,698,892,690,647,34,28,32,13,82,74,479,278,103,92,905,256
    };
    private final int[] TIER3_SEEDS = {332,337,334,948,530,746,539,349};

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
