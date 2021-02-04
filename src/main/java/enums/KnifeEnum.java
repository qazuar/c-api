package enums;

public enum KnifeEnum {

    BAYONET_SLAUGHTER("market/listings/730/%E2%98%85%20Bayonet%20%7C%20Slaughter%20%28{}%29", true),
    KARAMBIT_DOPPLER("market/listings/730/%E2%98%85%20Karambit%20%7C%20Doppler%20%28{}%29", true),
    KARAMBIT_LORE("market/listings/730/%E2%98%85%20Karambit%20%7C%20Lore%20%28{}%29", true),
    KARAMBIT_MARBLE_FADE("market/listings/730/%E2%98%85%20Karambit%20%7C%20Marble%20Fade%20%28{}%29", true),
    M9_LORE("market/listings/730/%E2%98%85%20M9%20Bayonet%20%7C%20Lore%20%28{}%29", true),
    TALON_FADE("market/listings/730/%E2%98%85%20Talon%20Knife%20%7C%20Fade%20%28{}%29", true),
    TALON_DOPPLER("market/listings/730/%E2%98%85%20Talon%20Knife%20%7C%20Doppler%20%28{}%29", true);

    private final String statTrakPrefix = "StatTrak%E2%84%A2%20";
    private String marketLink;
    private Boolean hasStatTrak;

    KnifeEnum(String marketLink, Boolean hasStatTrak) {
        this.marketLink = marketLink;
        this.hasStatTrak = hasStatTrak;
    }

    public String getMarketLink(String exterior, boolean statTrak) {
        return this.marketLink.replace("{}", exterior).replace("/730/", (statTrak && this.hasStatTrak) ? "/730/" + this.statTrakPrefix : "/730/");
    }

}
