package application;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.status.Status;
import rest.test.PayloadTest;
import rest.test.Test;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@XmlRootElement
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

    // produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    @RequestMapping(value = "/payloadtest", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.TEXT_XML_VALUE})
    public ResponseEntity<?> test2(@RequestBody PayloadTest bean) {
        System.out.println(bean.getContent());
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
