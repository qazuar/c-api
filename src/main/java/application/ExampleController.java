package application;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rest.example.ExampleClass;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ExampleController {

    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/example")
    public ExampleClass greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new ExampleClass(counter.incrementAndGet(), String.format("Hello %s!", name));
    }
}
