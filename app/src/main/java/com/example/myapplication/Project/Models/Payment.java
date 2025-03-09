package com.example.myapplication.Project.Models;

public class Payment {
    private int PaymentId;
    private String PaymentMethod;

    public Payment() {
    }

    public Payment(int paymentId, String paymentMethod) {
        PaymentId = paymentId;
        PaymentMethod = paymentMethod;
    }

    public int getPaymentId() {
        return PaymentId;
    }

    public void setPaymentId(int paymentId) {
        PaymentId = paymentId;
    }

    public String getPaymentMethod() {
        return PaymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        PaymentMethod = paymentMethod;
    }
}
