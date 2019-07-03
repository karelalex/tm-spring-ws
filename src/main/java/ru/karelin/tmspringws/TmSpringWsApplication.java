package ru.karelin.tmspringws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TmSpringWsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TmSpringWsApplication.class, args);
    }

}
