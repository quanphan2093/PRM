package com.example.myapplication.Slot8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.R;

import java.util.List;

public class Slot9CartAdapter extends ArrayAdapter<ProductSl8> {
    private Context context;

    public Slot9CartAdapter( Context context, List<ProductSl8> products) {
        super(context,0, products);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null){
            listItem= LayoutInflater.from(context).inflate(R.layout.slot9_cart_item,parent,false);
        }
        //get current product
        ProductSl8 currentProduct = getItem(position);
        //display info of product in cart
        TextView productBrand = listItem.findViewById(R.id.slot9_cart_item_tvbrand);
        productBrand.setText(currentProduct.getProduct_additional_info());
        TextView productId = listItem.findViewById(R.id.slot9_cart_item_tvid);
        productId.setText(currentProduct.getStyleid());
        TextView productQuantity = listItem.findViewById(R.id.slot9_cart_item_tvquantity);
        productQuantity.setText("Quantity: " +1);
        return listItem;
    }
}
