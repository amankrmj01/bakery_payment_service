package com.shah_s.bakery_payment_service;

import org.springframework.boot.SpringApplication;

public class TestBakeryPaymentServiceApplication {

    public static void main(String[] args) {
        SpringApplication.from(BakeryPaymentServiceApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
