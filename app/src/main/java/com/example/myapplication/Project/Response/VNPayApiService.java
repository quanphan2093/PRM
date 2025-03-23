package com.example.myapplication.Project.Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface VNPayApiService {
    @FormUrlEncoded
    @POST("vnpay_create_payment.php")
    Call<VNPayResponse> createPayment(
            @Field("amount") String amount
    );

    @GET("check_vnpay_status.php")
    Call<PaymentResponse> checkVNPayStatus();
}
