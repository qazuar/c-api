package enums;

public enum RareStickerEnum {

    KATOWICE_2014("katowice 2014"),
    KATOWICE_2015("katowice 2015"),
    CROWN("crown"),
    HOWLING_DAWN("howling dawn");

    private String name;

    RareStickerEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
