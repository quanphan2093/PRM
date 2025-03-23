package com.example.myapplication.Project.Response;

import com.google.gson.annotations.SerializedName;

public class VNPayResponse {
    @SerializedName("payment_url")
    private String paymentUrl;

    public String getPaymentUrl() {
        return paymentUrl;
    }
}
