package com.example.myapplication.Project.Response;

public class ApiResponse {
    private String message;
    private String error;
    private String  userId;

    public String getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }
}

