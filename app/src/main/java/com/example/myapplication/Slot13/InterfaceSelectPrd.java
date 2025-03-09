package com.example.myapplication.Slot13;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceSelectPrd {
    @GET("select_prd.php")
    Call<ResponseSelectPrd> GetData();
}
