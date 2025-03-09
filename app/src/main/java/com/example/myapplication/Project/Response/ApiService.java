package com.example.myapplication.Project.Response;

import com.example.myapplication.Project.Models.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @Headers("Content-Type: application/json")
    @POST("save_user.php") // Đường dẫn file PHP
    Call<ApiResponse> saveUser(@Body User user);
}
