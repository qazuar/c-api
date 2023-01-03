package application;

import facade.SteamFacade;
import org.springframework.web.bind.annotation.RequestMethod;
import rest.steam.MarketItemList;
import steam.ItemObj;
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

    @RequestMapping(value = PATH + "scan", method = RequestMethod.GET, produces = {"application/xml"})
    public MarketItemList scanMarketItems(
            @RequestParam(value="link") String link,
            @RequestParam(value="filters", defaultValue="all") String[] filters,
            @RequestParam(value="count", defaultValue="100") Integer count) {
        return steamFacade.getMarketItemList(link, filters, count);
    }

}
