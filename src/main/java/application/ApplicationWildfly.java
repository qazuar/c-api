package application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Collections;

@SpringBootApplication
public class ApplicationWildfly extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApplicationWildfly.class);
    }

    public static void mainE(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        //application.setDefaultProperties(Collections.singletonMap("server.port", "8083"));

        application.run(args);
    }
}
