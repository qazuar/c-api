package enums;

public enum ScanFilter {

    ALL("all"),
    STICKERS("stickers"),
    FLOAT("float");

    private String name;

    ScanFilter(String name) {
        this.name = name;
    }

    public static boolean hasFilter(String[] filters, ScanFilter filter) {
        for (String f : filters) {
            if (f.equalsIgnoreCase(filter.getName())) {
                return true;
            }
        }

        return false;
    }

    public String getName() {
        return this.name;
    }
}
