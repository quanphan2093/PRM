package com.example.myapplication.Project.Response;


import com.example.myapplication.Project.Models.Product;

public class ResponseSelectProduct {
    private Product[] products;
    private String message;

    public Product[] getProducts() {
        return products;
    }
    public String getMessage() {
        return message;
    }
}
