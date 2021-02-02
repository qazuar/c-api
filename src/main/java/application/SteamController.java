package application;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rest.test.Test;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class SteamController {

    private final String PATH = "steam/";

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping(PATH + "marketpage")
    public Test test(@RequestParam(value="link") String link) {
        return null;
    }

}
