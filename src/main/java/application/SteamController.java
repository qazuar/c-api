package application;

import common.MarketObj;
import enums.ApiEnum;
import external.Receiver;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class SteamController {

    private final String PATH = "steam/";
    private final AtomicLong counter = new AtomicLong();
    private final Receiver receiver = new Receiver();

    @RequestMapping(PATH + "marketpage")
    public MarketObj getMarketItems(@RequestParam(value="link") String link) {
        try {
            link = link.replaceAll(ApiEnum.STEAM_COMMUNITY_ADDRESS.getPath(), "").replaceAll(" ", "%20");
            return receiver.getMarketObject(link, 100);
        } catch (Exception e) {
            return null;
        }
    }

}
