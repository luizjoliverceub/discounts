package com.example.order;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DiscountServiceTest {
    @InjectMocks
    private final DiscountService discountService = new DiscountService();

    @Mock
    PromoService promoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testApplyDiscount_Over20Items() {
        Order order = new Order("John Doe",
                true,
                21,
                100.0,
                null);
        discountService.applyDiscount(order);
        assertEquals(85.0, order.getTotalAmount());  // 15% desconto
    }

    @Test
    void testApplyDiscount_LoyaltyMember_Over10Items() {
        Order order = new Order("Jane Doe", true, 12, 100.0, null);
        discountService.applyDiscount(order);
        assertEquals(90.0, order.getTotalAmount());  // 10% desconto
    }

    @Test
    void testApplyDiscount_LoyaltyMember_Under10Items() {
        Order order = new Order("Jane Doe", true, 8, 100.0, null);
        discountService.applyDiscount(order);
        assertEquals(100.0, order.getTotalAmount());  // Sem desconto
    }

    @Test
    void testApplyDiscount_NonLoyaltyMember_Over20Items() {
        Order order = new Order("Jake Doe", false, 20, 200.0, null);
        discountService.applyDiscount(order);
        assertEquals(170.0, order.getTotalAmount());  // 15% desconto
    }

    @Test
    void testApplyDiscount_DuringPromotionPeriod2() {
        Order order = new Order("Mary Doe", false, 6, 100.0, null);
        LocalDate today = LocalDate.now();
        when(promoService.checkPromoPeriod(any(LocalDate.class))).thenReturn(true);

        discountService.aplicaDescontoDataPromocional(order, today);
        assertEquals(95.0, order.getTotalAmount());  // 5% desconto durante promoção

    }

    @Test
    void testApplyDiscount_NotDuringPromotionPeriod() {
        Order order = new Order("Mary Doe", false, 6, 100.0, null);
        LocalDate today = LocalDate.now();

        discountService.aplicaDescontoDataPromocional(order, today);
        assertEquals(100.0, order.getTotalAmount());  // 5% desconto durante promoção

    }

    @Test
    void testApplyDiscount_WithBlackFridayCoupon() {
        Order order = new Order("Lucy Doe", false, 5, 100.0, "BLACKFRIDAY");
        discountService.applyDiscount(order);
        assertEquals(80.0, order.getTotalAmount());  // 20% desconto com cupom BLACKFRIDAY
    }

    @Test
    void testApplyDiscount_WithNewYearCoupon() {
        Order order = new Order("Lucy Doe", false, 5, 100.0, "NEWYEAR");
        discountService.applyDiscount(order);
        assertEquals(90.0, order.getTotalAmount());  // 10% desconto com cupom NEWYEAR
    }

    @Test
    void testApplyDiscount_NoCoupon() {
        Order order = new Order("Lucy Doe", false, 5, 100.0, null);
        discountService.applyDiscount(order);
        assertEquals(100.0, order.getTotalAmount());  // Sem desconto
    }


}