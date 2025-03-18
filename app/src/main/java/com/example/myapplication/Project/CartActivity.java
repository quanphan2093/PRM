package com.example.myapplication.Project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Project.Models.Cart;
import com.example.myapplication.Project.Models.Product;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity implements CartAdapter.OnProductSelectedListener {
    ListView listView;
    CartAdapter adapter;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart);

        listView = findViewById(R.id.list_cart);
        btnAdd = findViewById(R.id.order);
        btnAdd.setVisibility(View.GONE); // Ban đầu ẩn nút Order

        // Lấy sản phẩm trong giỏ hàng
        Cart cart = Cart.getInstance();
        List<Product> cartItem = cart.getCartItems();

        // Khởi tạo adapter
        adapter = new CartAdapter(this, cartItem, this);
        listView.setAdapter(adapter);

        // Xử lý khi nhấn Order
        btnAdd.setOnClickListener(v -> {
            List<Product> selectedProducts = adapter.getSelectedProducts();
            if (!selectedProducts.isEmpty()) {
                addToOrder(selectedProducts);
                Intent intent = new Intent(CartActivity.this, OrderActivity.class);
                intent.putParcelableArrayListExtra("SELECTED_PRODUCTS", new ArrayList<>(selectedProducts));
                startActivity(intent);
            }
        });
    }

    @Override
    public void onProductSelected(boolean hasSelection) {
        btnAdd.setVisibility(hasSelection ? View.VISIBLE : View.GONE);
    }

    private void addToOrder(List<Product> selectedProducts) {
        for (Product product : selectedProducts) {
            OrderManager.getInstance().addProduct(product);
        }
        Toast.makeText(this, "Đã thêm vào đơn hàng!", Toast.LENGTH_SHORT).show();
    }
}
