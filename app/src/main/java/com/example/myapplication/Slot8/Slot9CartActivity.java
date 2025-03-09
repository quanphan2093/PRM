package com.example.myapplication.Slot8;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import java.util.List;

public class Slot9CartActivity extends AppCompatActivity {

    ListView listView;
    Slot9CartAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot9_cart);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView = findViewById(R.id.slot9_cart_activity);
        //init list of cart and adapter
        Slot9CartManager cartManager= Slot9CartManager.getInstance(); //only one
        List<ProductSl8> cartItem = cartManager.getCartItems(); //cart item for cartManager
        //init adapter
        adapter = new Slot9CartAdapter(this,cartItem);
        listView.setAdapter(adapter);


    }
}