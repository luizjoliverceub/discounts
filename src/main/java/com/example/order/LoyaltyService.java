package com.example.order;

public class LoyaltyService {


    public boolean isLoyaltyMember(String customerName) {

        return "Jane Doe".equals(customerName);
    }

    public double getLoyaltyDiscount() {
        return 0.05;
    }
}