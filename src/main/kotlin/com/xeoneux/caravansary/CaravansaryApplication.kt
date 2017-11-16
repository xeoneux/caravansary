package com.xeoneux.caravansary

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
class CaravansaryApplication

fun main(args: Array<String>) {
    SpringApplication.run(CaravansaryApplication::class.java, *args)
}
