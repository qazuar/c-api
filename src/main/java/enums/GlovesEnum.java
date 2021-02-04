package enums;

public enum GlovesEnum {

    SPECIALIST_FADE("market/listings/730/%E2%98%85%20Specialist%20Gloves%20%7C%20Fade%20%28{}%29", false),
    SPECIALIST_CRIMSON_KIMONO("market/listings/730/%E2%98%85%20Specialist%20Gloves%20%7C%20Crimson%20Kimono%20%28{}%29", false),
    SPORT_PANDORA("market/listings/730/%E2%98%85%20Sport%20Gloves%20%7C%20Pandora%27s%20Box%20%28{}%29", false),
    SPORT_OMEGA("market/listings/730/%E2%98%85%20Sport%20Gloves%20%7C%20Omega%20%28{}%29", false),
    SPORT_SUPERCONDUCTOR("market/listings/730/%E2%98%85%20Sport%20Gloves%20%7C%20Superconductor%20%28{}%29", false),
    SPORT_VICE("market/listings/730/%E2%98%85%20Sport%20Gloves%20%7C%20Vice%20%28{}%29", false);

    private final String statTrakPrefix = "StatTrak%E2%84%A2%20";
    private String marketLink;
    private Boolean hasStatTrak;

    GlovesEnum(String marketLink, Boolean hasStatTrak) {
        this.marketLink = marketLink;
        this.hasStatTrak = hasStatTrak;
    }

    public String getMarketLink(String exterior, boolean statTrak) {
        return this.marketLink.replace("{}", exterior).replace("/730/", (statTrak && this.hasStatTrak) ? "/730/" + this.statTrakPrefix : "/730/");
    }

}
