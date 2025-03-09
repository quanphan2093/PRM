package com.example.myapplication.Project;

import com.example.myapplication.Project.Models.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
    private static OrderManager instance;
    private List<Product> orderList;

    private OrderManager() {
        orderList = new ArrayList<>();
    }

    public static synchronized OrderManager getInstance() {
        if (instance == null) {
            instance = new OrderManager();
        }
        return instance;
    }

    public void addProduct(Product product) {
        orderList.add(product);
    }

    public List<Product> getOrderList() {
        return orderList;
    }
}
