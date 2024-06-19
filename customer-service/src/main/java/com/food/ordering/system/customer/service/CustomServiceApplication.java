package com.food.ordering.system.customer.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.food.ordering.system")
public class CustomServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CustomServiceApplication.class, args);
    }
}
