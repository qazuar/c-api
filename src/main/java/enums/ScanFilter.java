package enums;

public enum ScanFilter {

    STICKERS("stickers"),
    PATTERN("pattern"),
    FLOAT("float");

    private String name;

    ScanFilter(String name) {
        this.name = name;
    }

    public static ScanFilter getFilter(String name) {
        for (ScanFilter s : values()) {
            if (s.getName().equals(name)) {
                return s;
            }
        }

        return null;
    }

    public String getName() {
        return this.name;
    }
}
