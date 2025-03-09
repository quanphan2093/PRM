package com.example.myapplication.Project.Response;

import com.example.myapplication.Project.Models.Payment;

public class ResponseSelectPayment {
    private Payment[] payments;
    private String message;

    public Payment[] getPayments() {
        return payments;
    }

    public String getMessage() {
        return message;
    }
}
