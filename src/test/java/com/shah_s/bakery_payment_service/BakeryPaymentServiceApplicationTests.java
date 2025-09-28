package com.shah_s.bakery_payment_service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class BakeryPaymentServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
