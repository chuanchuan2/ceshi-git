package com.cloud.eraka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class ErakaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ErakaApplication.class, args);
    }

}
