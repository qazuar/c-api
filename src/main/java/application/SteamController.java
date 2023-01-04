package application;

import facade.SteamFacade;
import org.springframework.http.MediaType;
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

    @RequestMapping(value = PATH + "scan", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public MarketItemList scanMarketItems(
            @RequestParam(value="link") String link,
            @RequestParam(value="count", defaultValue="100") Integer count,
            @RequestParam(value="minfloat", defaultValue="0.0") float minFloat,
            @RequestParam(value="maxfloat", defaultValue="1.0") float maxFloat,
            @RequestParam(value="seeds", defaultValue="") int[] seeds,
            @RequestParam(value="stickers", defaultValue="") String[] stickers) {
        return steamFacade.getMarketItemList(link, count, minFloat, maxFloat, seeds, stickers);
    }

    @RequestMapping(value = PATH + "find", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public MarketItemList findMarketItems(
            @RequestParam(value="item") String item,
            @RequestParam(value="type") String type) {
        return steamFacade.findMarketItemList(item, type);
    }
}
