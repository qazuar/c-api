package application;

import facade.SteamFacade;
import rest.steam.MarketItemList;
import steam.ItemObj;
import steam.MarketObj;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SteamController {

    private final String PATH = "steam/";

    private final SteamFacade steamFacade = new SteamFacade();

    @RequestMapping(PATH + "inspect")
    public ItemObj getItem(@RequestParam(value="link") String link) {
        return steamFacade.getItemObj(link);
    }

    @RequestMapping(PATH + "scan")
    public MarketItemList scanMarketItems(@RequestParam(value="link") String link, @RequestParam(value="filter", defaultValue="default") String filter) {
        return steamFacade.getMarketItemList(link, filter);
    }

}
