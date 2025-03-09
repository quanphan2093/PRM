package com.example.myapplication.Project;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Project.Models.Cart;
import com.example.myapplication.Project.Models.Product;
import com.example.myapplication.R;
import com.example.myapplication.Slot8.ProductSl8;
import com.example.myapplication.Slot8.Slot9CartActivity;
import com.example.myapplication.Slot8.Slot9CartManager;
import com.squareup.picasso.Picasso;

public class ProductDetailActivity extends AppCompatActivity {
    ImageView imageView;
    TextView ProdName, CateName, ProdDetail, Price;
    EditText Quantity;
    Button btnAdd;
    private Cart cart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);
        cart =Cart.getInstance();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imageView=findViewById(R.id.image_product);
        ProdName =findViewById(R.id.tv_productName);
        CateName =findViewById(R.id.tv_prd_category);
        ProdDetail =findViewById(R.id.detail_product);
        Quantity =findViewById(R.id.tv_prd_quantity);
        Price =findViewById(R.id.tv_prd_price);
        btnAdd =findViewById(R.id.btn_prd_AddToCart);
        Intent intent = getIntent();
        Product product= intent.getParcelableExtra("PRODUCT");
        if(product!=null){
            ProdName.setText(product.getProdName());
            Price.setText(String.valueOf(product.getPrice()));
            CateName.setText(product.getCateName());
            Quantity.setText("1");
            ProdDetail.setText(product.getProdDetail());
        }

        btnAdd.setOnClickListener(v -> {
            Intent intent1 = getIntent();
            Product product1 = intent1.getParcelableExtra("PRODUCT");

            if (product1 != null) {
                Cart cart = Cart.getInstance();
                int newQuantity = Integer.parseInt(Quantity.getText().toString());
                boolean productExists = false;

                for (Product p : cart.getCartItems()) {
                    if (p.getProdId() == product1.getProdId()) {
                        p.setQuantity(p.getQuantity() + newQuantity); // Cập nhật số lượng
                        p.setPrice(p.getUnitPrice() * p.getQuantity());
                        productExists = true;
                        break;
                    }
                }

                if (!productExists) {
                    product1.setQuantity(newQuantity);
                    product1.setPrice(product1.getPrice() * newQuantity); // Cập nhật giá
                    cart.addProductToCart(product1);
                }

                Intent cartIntent = new Intent(this, CartActivity.class);
                cartIntent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT); // Không mở Activity mới nếu đã tồn tại
                startActivity(cartIntent);
            }
        });


    }
}