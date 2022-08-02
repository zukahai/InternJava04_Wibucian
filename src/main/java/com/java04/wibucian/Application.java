package com.java04.wibucian;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.java04.wibucian.controllers")
@ComponentScan(basePackages = "com.java04.wibucian.services")
@ComponentScan(basePackages = "com.java04.wibucian.repositories")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
