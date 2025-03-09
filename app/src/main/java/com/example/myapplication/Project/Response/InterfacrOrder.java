package com.example.myapplication.Project.Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface InterfacrOrder {
    @GET("select_payment.php")
    Call<ResponseSelectPayment> GetData();

    @FormUrlEncoded
    @POST("insert_order.php")
    Call<ResponseOrder> insertOrder(
            @Field("UserId") int userId,
            @Field("PaymentId") int PaymentId,
            @Field("AddressId") int AddressId
    );

    @FormUrlEncoded
    @POST("insert_order_detail.php")
    Call<ResponeProduct> insertOrderDetail(
            @Field("OrderId") int OrderId,
            @Field("ProdId") int ProdId,
            @Field("Quantity") int quantity,
            @Field("Price") float price
    );

    @FormUrlEncoded
    @POST("insert_address.php")
    Call<ResponseOrder> insertAddress(
            @Field("Location") String Location,
            @Field("PhoneNumber") String PhoneNumber,
            @Field("Note") String Note,
            @Field("UserId") int UserId
    );
}
