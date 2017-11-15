package com.xeoneux.caravansary.caravansary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan("com.xeoneux.caravansary")
public class CaravansaryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaravansaryApplication.class, args);
    }
}
