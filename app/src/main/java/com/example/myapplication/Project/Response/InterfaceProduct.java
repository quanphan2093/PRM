package com.example.myapplication.Project.Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface InterfaceProduct {
    @GET("select_prd.php")
    Call<ResponseSelectProduct> GetData();

    @GET("search_prd.php")
    Call<ResponseSelectProduct> SearchPrd(@Query("Search") String search);
    @FormUrlEncoded
    @POST("insert_prd.php")
    Call<ResponeProduct> insertPrd(
            @Field("id") String id,
            @Field("name") String name,
            @Field("price") String price,
            @Field("description") String description
    );

    @FormUrlEncoded
    @POST("delete_prd.php")
    Call<ResponeProduct> deletePrd(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("update_prd.php")
    Call<ResponeProduct> updatePrd(
            @Field("id") String id,
            @Field("name") String name,
            @Field("price") String price,
            @Field("description") String description
    );
}
