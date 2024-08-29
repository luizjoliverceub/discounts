package com.example.order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class LoyaltyServiceTest {

    private LoyaltyService loyaltyService;

    @BeforeEach
    void setUp() {
        loyaltyService = new LoyaltyService();
    }

    @Test
    void testIsLoyaltyMember_True() {
        assertTrue(loyaltyService.isLoyaltyMember("Jane Doe"));
    }

    @Test
    void testIsLoyaltyMember_False() {
        assertFalse(loyaltyService.isLoyaltyMember("John Doe"));
    }

    @Test
    void testGetLoyaltyDiscount() {
        assertEquals(0.05, loyaltyService.getLoyaltyDiscount());
    }
}