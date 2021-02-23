package utils;

import enums.ApiEnum;
import steam.Sticker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Misc {

    public static String encodeMarketLink(String link) {
        return link.replaceAll(ApiEnum.STEAM_COMMUNITY_ADDRESS.getPath(), "").replaceAll(" ", "%20").replaceAll("™", "%E2%84%A2").replaceAll("★", "%E2%98%85");
    }

    public static String getClientOS() {
        return System.getProperty("os.name").split(" ")[0] + "/Java " + System.getProperty("java.version");
    }

    public static String stringMultiply(String value, Integer multiply) {
        String r = "";

        for (int i = 0; i < multiply; i++) {
            r += value;
        }

        return r;
    }

    public static String convertMs2TimeString(long ms) {
        double mps = 1000.0;
        double spm = 60.0;
        double mph = 60.0;

        double millis = (double) ms;
        double seconds = 0.0;
        double minutes = 0.0;
        double hours = 0.0;

        while (millis >= mps) {
            seconds += 1.0;
            millis -= mps;
        }

        while (seconds >= spm) {
            minutes += 1.0;
            seconds -= spm;
        }

        while (minutes >= mph) {
            hours += 1.0;
            minutes -= mph;
        }

        String time = (hours < 10.0 ? "0" + (int) hours : (int) hours) + ":" + (minutes < 10.0 ? "0" + (int) minutes : (int) minutes) + ":" + (seconds < 10.0 ? "0" + (int) seconds : (int) seconds);

        return time;
    }

    // Example string:
    // [
    //   {slot=0.0, wear=0.2869558334350586, stickerId=53.0, codename=kat2014_dignitas_holo, material=emskatowice2014/dignitas_holo, name=Team Dignitas (Holo) | Katowice 2014},
    //   {slot=1.0, wear=0.09748642891645432, stickerId=127.0, codename=cologne2014_hellraisers_holo, material=cologne2014/hellraisers_holo, name=HellRaisers (Holo) | Cologne 2014},
    //   {slot=2.0, stickerId=1830.0, codename=atlanta2017_signature_deadfox_foil, material=atlanta2017/sig_deadfox_foil, name=DeadFox (Foil) | Atlanta 2017}
    // ]
    public static List<Sticker> mapString2StickerList(String value) {
        value = value.replaceAll("\\{", "").replaceAll("\\[", "").replaceAll("}]", "");
        String[] s = value.split("}, ");

        List<Sticker> stickers = new ArrayList<>();

        for (String str : s) {
            Sticker sticker = new Sticker();

            for (String v : str.split(", ")) {
                String[] kv = v.split("=");

                if (kv[0].equals("slot")) {
                    sticker.setSlot(kv[1]);
                } else if (kv[0].equals("wear")) {
                    sticker.setWear(kv[1]);
                } else if (kv[0].equals("stickerId")) {
                    sticker.setId(kv[1]);
                } else if (kv[0].equals("codename")) {
                    sticker.setCodename(kv[1]);
                } else if (kv[0].equals("material")) {
                    sticker.setMaterial(kv[1]);
                } else if (kv[0].equals("name")) {
                    sticker.setName(kv[1]);
                }
            }

            // Perhaps refactor to check earlier if the string has any stickers in it
            if (sticker.getName() != null) {
                stickers.add(sticker);
            }
        }

        return stickers;
    }

    /**
     * Convert steam market page HTML into a map of data including:
     *  - Links
     *  - Prices
     *  - asset IDs
     * @param response
     * @return
     */
    public static Map<String, List<String>> steamHtmlToMap(String response) {
        Map<String, List<String>> map = new HashMap<>();

        List<String> links = new ArrayList<>();
        List<String> assetIds = new ArrayList<>();
        List<String> prices = new ArrayList<>();

        String x, y, z;

        for (String s : response.split(" ")) {
            if (s.contains("+csgo") && !s.contains("%listing")) {
                x = s.split("\\+csgo_econ_action_preview")[1].split("\"")[0].trim();

                if (!links.contains(x)) {
                    links.add(x);
                }
            }

            if (s.contains("\"asset\":{\"currency\":0,\"appid\":730,\"contextid\":\"2\",\"id\":")) {
                y = s.split("\"asset\"")[1].split("\"id\":")[1].split(",")[0].replace("\"", "");

                if (!assetIds.contains(y)) {
                    assetIds.add(y);
                }
            }
        }

        for (String s : response.split("span")) {
            if (s.contains("market_listing_price_with_fee")) {
                z = s.split("market_listing_price_with_fee\">")[1].replace("</", "").trim();

                prices.add(z);
            }
        }

        map.put("links", links);
        map.put("assetids", assetIds);
        map.put("prices", prices);

        return map;
    }
}
