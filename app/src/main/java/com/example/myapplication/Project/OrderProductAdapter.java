package com.example.myapplication.Project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Project.Models.Product;
import com.example.myapplication.R;

import java.util.List;

public class OrderProductAdapter extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> selectedProducts;

    public OrderProductAdapter(Context context, List<Product> selectedProducts) {
        super(context, 0, selectedProducts);
        this.context = context;
        this.selectedProducts = selectedProducts;
    }

    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.order_product_cart, parent, false);
        }
        Product currentProduct = getItem(position);
        TextView ProdName = listItem.findViewById(R.id.order_product_name);
        ProdName.setText(currentProduct.getProdName());
        TextView Price = listItem.findViewById(R.id.order_price);
        Price.setText(String.valueOf(currentProduct.getPrice()));
        TextView CateName = listItem.findViewById(R.id.order_category_name);
        CateName.setText(currentProduct.getCateName());
        TextView quantity = listItem.findViewById(R.id.order_quantity);
        quantity.setText(String.valueOf(currentProduct.getQuantity()));

        return listItem;
    }
}
