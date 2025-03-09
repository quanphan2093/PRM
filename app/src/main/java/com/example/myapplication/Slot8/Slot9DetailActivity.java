package com.example.myapplication.Slot8;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

public class Slot9DetailActivity extends AppCompatActivity {

    ImageView imageView;
    TextView tvstyleid, tvbrand, tvprice, tvproductinfo;
    Button btnAdd;
    private Slot9CartManager cartManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot9_detail);
        cartManager= Slot9CartManager.getInstance(); // get instance of cart
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imageView=findViewById(R.id.slot9_detail_search_image);
        tvstyleid=findViewById(R.id.slot9_detail_styleid);
        tvbrand=findViewById(R.id.slot9_detail_brand);
        tvprice=findViewById(R.id.slot9_detail_price);
        tvproductinfo=findViewById(R.id.slot9_detail_product_info);
        btnAdd=findViewById(R.id.slot9_detail_btnAdd);
        //receive data from intent
        Intent intent = getIntent();
        ProductSl8 productSl8 = intent.getParcelableExtra("PRODUCT"); //get object

        //display detail of object
        if(productSl8!=null){
            //get image
            Picasso.get().load(productSl8.getSearch_image()).into(imageView);
            //get all text
            tvstyleid.setText("style ID: " + productSl8.getStyleid());
            tvbrand.setText("Brand: " + productSl8.getProduct_additional_info());
            tvprice.setText("Price: " + productSl8.getPrice());
            tvproductinfo.setText("More info: " + "Day la Product");
        }
        //add product to cart
        btnAdd.setOnClickListener(v -> {
            Intent intent1 = getIntent();
            ProductSl8 productSl81 = intent1.getParcelableExtra("PRODUCT");
            if(productSl81 != null){
                //add product to cart
                cartManager.addProductToCart(productSl81);
                //open new activity
                Intent cartIntent = new Intent(this, Slot9CartActivity.class);
                startActivity(cartIntent);
            }
        });
    }
}