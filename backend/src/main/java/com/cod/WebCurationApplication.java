package com.cod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class WebCurationApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(WebCurationApplication.class);
        app.addListeners(new ApplicationPidFileWriter());
        app.run(args);
    }
}
