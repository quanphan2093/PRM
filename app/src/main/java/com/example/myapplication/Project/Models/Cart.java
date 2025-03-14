package com.example.myapplication.Project.Models;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    private static Cart instance;
    private List<Product> cartItems;
    Cart(){
        cartItems= new ArrayList<>();
    }
    public static synchronized Cart getInstance(){
        if(instance == null){
            instance=new Cart();
        }
        return instance;
    }

    public void addProductToCart(Product product){
        cartItems.add(product);
    }

    public List<Product> getCartItems(){
        return cartItems;
    }
}
