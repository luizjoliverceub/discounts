package com.example.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PromoServiceTest {
    PromoService service;
    @BeforeEach
    void setUp() {
        service = new PromoService();
    }

    @Test
    public void test_TodayBetweenPromoPeriod_ReturnsTrue() {
        //Prepare
        LocalDate today = LocalDate.now();
        LocalDate start = LocalDate.now().minusDays(1);
        LocalDate end = LocalDate.now().plusDays(1);
        service.setPromoStart(start);
        service.setPromoEnd(end);

        //Execute
        boolean result = service.checkPromoPeriod(today);

        //Assert
        assertTrue(result);

    }

    @Test
    public void test_TodayAfterPromoPeriod_ReturnsFalse() {
        //Prepare
        LocalDate today = LocalDate.now();
        LocalDate start = LocalDate.now().minusDays(1);
        LocalDate end = LocalDate.now().plusDays(1);
        service.setPromoStart(start);
        service.setPromoEnd(end);

        //Execute
        boolean result = service.checkPromoPeriod(today);

        //Assert
        assertFalse(result);

    }

}