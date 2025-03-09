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
import com.example.myapplication.Slot8.ProductSl8;
import com.example.myapplication.Slot8.Slot9CartAdapter;
import com.example.myapplication.Slot8.Slot9CartManager;

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
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView= findViewById(R.id.list_cart);
        btnAdd = findViewById(R.id.order);
//        btnAdd.setVisibility(View.GONE);
        Cart cart= Cart.getInstance(); //only one
        List<Product> cartItem = cart.getCartItems(); //cart item for cartManager
        //init adapter
        adapter = new CartAdapter(this,cartItem,this);
        listView.setAdapter(adapter);
        btnAdd.setOnClickListener(v -> {
            Intent intent = new Intent(CartActivity.this, OrderActivity.class);
            List<Product> selectedProducts = adapter.getSelectedProducts();
            addToOrder(selectedProducts);
            intent.putParcelableArrayListExtra("SELECTED_PRODUCTS", new ArrayList<>(selectedProducts));
            startActivity(intent);
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
    private List<Product> getCartProducts() {
        // Hàm lấy danh sách sản phẩm từ giỏ hàng
        return Cart.getInstance().getCartItems();
    }
}