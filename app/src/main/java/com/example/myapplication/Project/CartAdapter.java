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
        if (listItem == null) {
            listItem = LayoutInflater.from(context).inflate(R.layout.cart_adapter, parent, false);
        }

        Product currentProduct = getItem(position);
        if (currentProduct == null) return listItem;

        // Hiển thị thông tin sản phẩm
        TextView ProdName = listItem.findViewById(R.id.cart_product_name);
        ProdName.setText(currentProduct.getProdName());

        TextView Price = listItem.findViewById(R.id.cart_price);
        Price.setText(String.valueOf(currentProduct.getPrice()));

        EditText quantity = listItem.findViewById(R.id.cart_quantity);
        quantity.setText(String.valueOf(currentProduct.getQuantity()));

        // Xử lý chọn sản phẩm
        RadioButton radioSelect = listItem.findViewById(R.id.cart_radiobtn);
        radioSelect.setChecked(selectedPositions.contains(position));
        radioSelect.setOnClickListener(v -> {
            if (selectedPositions.contains(position)) {
                selectedPositions.remove(position);
            } else {
                selectedPositions.add(position);
            }
            notifyProductSelection();
        });

        // Xóa sản phẩm khỏi giỏ hàng
        Button btnDelete = listItem.findViewById(R.id.cart_btndelete);
        btnDelete.setOnClickListener(v -> {
            productList.remove(position);
            selectedPositions.remove(position); // Xóa khỏi danh sách chọn
            notifyDataSetChanged();
            notifyProductSelection();
        });

        // Cập nhật số lượng sản phẩm
        Button btnUpdate = listItem.findViewById(R.id.cart_btnupdate);
        btnUpdate.setOnClickListener(v -> {
            int newQuantity;
            try {
                newQuantity = Integer.parseInt(quantity.getText().toString());
            } catch (NumberFormatException e) {
                newQuantity = currentProduct.getQuantity(); // Giữ nguyên nếu nhập sai
            }
            currentProduct.setQuantity(newQuantity);
            currentProduct.setPrice(newQuantity * currentProduct.getUnitPrice());
            notifyDataSetChanged();
        });

        return listItem;
    }

    private void notifyProductSelection() {
        if (listener != null) {
            listener.onProductSelected(!selectedPositions.isEmpty());
        }
    }

    public List<Product> getSelectedProducts() {
        List<Product> selectedProducts = new ArrayList<>();
        for (int pos : selectedPositions) {
            if (pos < productList.size()) {
                selectedProducts.add(productList.get(pos));
            }
        }
        return selectedProducts;
    }

    public interface OnProductSelectedListener {
        void onProductSelected(boolean hasSelection);
    }
}
