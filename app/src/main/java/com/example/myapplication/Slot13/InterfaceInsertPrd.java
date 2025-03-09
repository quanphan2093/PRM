package com.example.myapplication.Slot13;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface InterfaceInsertPrd {
    @FormUrlEncoded
    @POST("insert_prd.php")
    Call<ResponseInsertPrd> insertPrd(
            @Field("id") String id,
            @Field("name") String name,
            @Field("price") String price,
            @Field("description") String description
    );

    @FormUrlEncoded
    @POST("delete_prd.php")
    Call<ResponseInsertPrd> deletePrd(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("update_prd.php")
    Call<ResponseInsertPrd> updatePrd(
            @Field("id") String id,
            @Field("name") String name,
            @Field("price") String price,
            @Field("description") String description
    );
}
