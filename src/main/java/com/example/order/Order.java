package com.example.order;

public class Order {
    private String customerName;
    private boolean isLoyaltyMember;
    private int itemCount;
    private double totalAmount;
    private String couponCode;

    public Order(String customerName, boolean isLoyaltyMember, int itemCount, double totalAmount, String couponCode) {
        this.customerName = customerName;
        this.isLoyaltyMember = isLoyaltyMember;
        this.itemCount = itemCount;
        this.totalAmount = totalAmount;
        this.couponCode = couponCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public boolean isLoyaltyMember() {
        return isLoyaltyMember;
    }

    public int getItemCount() {
        return itemCount;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void applyDiscount(double discount) {
        this.totalAmount -= discount;
    }

    public boolean hasCoupon() {
        return couponCode != null && !couponCode.isEmpty();
    }

    public String getCouponCode() {
        return couponCode;
    }
}
