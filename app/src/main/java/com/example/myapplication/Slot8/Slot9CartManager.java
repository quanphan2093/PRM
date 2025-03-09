package com.example.myapplication.Slot8;

import java.util.ArrayList;
import java.util.List;

public class Slot9CartManager {
    private static Slot9CartManager instance;
    private List<ProductSl8> cartItems;
    Slot9CartManager(){
        cartItems= new ArrayList<>();
    }
    public static synchronized Slot9CartManager getInstance(){
        if(instance == null){
            instance=new Slot9CartManager();
        }
        return instance;
    }

    public void addProductToCart(ProductSl8 productSl8){
        cartItems.add(productSl8);
    }

    public List<ProductSl8> getCartItems(){
        return cartItems;
    }
}
