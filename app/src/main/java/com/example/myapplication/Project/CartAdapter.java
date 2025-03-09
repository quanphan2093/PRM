package com.example.myapplication.Project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.myapplication.Project.Models.Product;
import com.example.myapplication.R;
import com.example.myapplication.Slot8.ProductSl8;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CartAdapter extends ArrayAdapter<Product> {
    private Context context;
    private List<Product> productList;
    private Set<Integer> selectedPositions = new HashSet<>();
    private OnProductSelectedListener listener;

    public CartAdapter(Context context, List<Product> productList, OnProductSelectedListener listener) {
        super(context, 0, productList);
        this.context = context;
        this.productList = productList;
        this.listener = listener;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null){
            listItem= LayoutInflater.from(context).inflate(R.layout.cart_adapter,parent,false);
        }
        //get current product
        Product currentProduct = getItem(position);
        //display info of product in cart
        TextView ProdName = listItem.findViewById(R.id.cart_product_name);
        ProdName.setText(currentProduct.getProdName());
        TextView Price = listItem.findViewById(R.id.cart_price);
        Price.setText(String.valueOf(currentProduct.getPrice()));
        EditText quantity = listItem.findViewById(R.id.cart_quantity);
        quantity.setText(String.valueOf(currentProduct.getQuantity()));
        RadioButton radioSelect = listItem.findViewById(R.id.cart_radiobtn);
        radioSelect.setChecked(selectedPositions.contains(position));
        radioSelect.setOnClickListener(v -> {
            if (selectedPositions.contains(position)) {
                selectedPositions.remove(position); // Nếu đang bật, thì tắt
                radioSelect.setChecked(false);
            } else {
                selectedPositions.add(position); // Nếu đang tắt, thì bật
                radioSelect.setChecked(true);
            }
        });
        Button btnDelete = listItem.findViewById(R.id.cart_btndelete);
        btnDelete.setOnClickListener(v -> {
            productList.remove(position);
            notifyDataSetChanged();
        });
        Button btnUpdate = listItem.findViewById(R.id.cart_btnupdate);
        btnUpdate.setOnClickListener( v ->{
            int newQuantity = Integer.parseInt(quantity.getText().toString());
            currentProduct.setQuantity(newQuantity);
            currentProduct.setPrice(newQuantity * currentProduct.getUnitPrice());
            notifyDataSetChanged();
        });
        return listItem;
    }

    public List<Product> getSelectedProducts() {
        List<Product> selectedProducts = new ArrayList<>();
        for (int pos : selectedPositions) {
            selectedProducts.add(productList.get(pos));
        }
        return selectedProducts;
    }

    public interface OnProductSelectedListener {
        void onProductSelected(boolean hasSelection);
    }
}
