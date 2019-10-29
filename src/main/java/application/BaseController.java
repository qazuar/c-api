package application;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rest.example.ExampleClass;
import rest.status.Status;
import rest.test.Test;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class BaseController {

    private final long startup = System.currentTimeMillis();
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/status")
    public Status status() {
        return new Status(counter.incrementAndGet(), startup);
    }

    @RequestMapping("/test")
    public Test test(@RequestParam(value="input", defaultValue="") String input) {
        return new Test(input);
    }
}
