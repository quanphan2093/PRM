package com.example.myapplication.Project;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Project.Models.Product;
import com.example.myapplication.Project.Response.InterfaceProduct;
import com.example.myapplication.Project.Response.ResponseSelectProduct;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductActivity extends AppCompatActivity {
    ListView lv;
    List<Product> ls = new ArrayList<>();
    ProductAdapter adapter;
    Context context = this;
    String strKq = "";
    EditText search;
    Button btnSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lv = findViewById(R.id.listProduct);
        search = findViewById(R.id.search_product);
        btnSearch = findViewById(R.id.btnSearch);
        adapter =new ProductAdapter(ls, context);
        lv.setAdapter(adapter);
        SelectData();
        btnSearch.setOnClickListener( v -> {
            SearchPrd();
        });
    }

    private void SelectData(){
        strKq="";
        //b1 Create retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.121.102/project/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2 prepare the select function
        InterfaceProduct interfaceSelectPrd = retrofit.create(InterfaceProduct.class);
        Call<ResponseSelectProduct> call = interfaceSelectPrd.GetData();
        //b3 excute function
        call.enqueue(new Callback<ResponseSelectProduct>() {
            @Override
            public void onResponse(Call<ResponseSelectProduct> call, Response<ResponseSelectProduct> response) {
                ResponseSelectProduct responseSelectPrd = response.body();
                ls= Arrays.asList(responseSelectPrd.getProducts());
                adapter.updateData(ls);
            }

            @Override
            public void onFailure(Call<ResponseSelectProduct> call, Throwable t) {
                strKq +=t.getMessage();
            }
        });
    }

    private void SearchPrd(){
        strKq="";
        //b1 Create retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.121.102/project/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2 prepare the select function
        InterfaceProduct interfaceSelectPrd = retrofit.create(InterfaceProduct.class);
        String searchValue = search.getText().toString();
        Call<ResponseSelectProduct> call = interfaceSelectPrd.SearchPrd(searchValue);
        //b3 excute function
        call.enqueue(new Callback<ResponseSelectProduct>() {
            @Override
            public void onResponse(Call<ResponseSelectProduct> call, Response<ResponseSelectProduct> response) {
                ResponseSelectProduct responseSelectPrd = response.body();
                ls= Arrays.asList(responseSelectPrd.getProducts());
                adapter.updateData(ls);
            }

            @Override
            public void onFailure(Call<ResponseSelectProduct> call, Throwable t) {
                strKq +=t.getMessage();
            }
        });
    }
}