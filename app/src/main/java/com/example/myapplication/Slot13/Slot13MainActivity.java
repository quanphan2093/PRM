package com.example.myapplication.Slot13;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Slot13MainActivity extends AppCompatActivity {
    EditText txtId, txtName, txtPrice, txtDescription;
    Button btnSelect, btnInsert, btnUpdate, btnDelete;
    TextView tvKQ;
    String strKq = "";
    List<Prod> ls= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot13_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtId = findViewById(R.id.id);
        txtName = findViewById(R.id.name);
        txtPrice=findViewById(R.id.price);
        txtDescription = findViewById(R.id.des);
        btnSelect = findViewById(R.id.select);
        btnInsert = findViewById(R.id.insert);
        btnUpdate = findViewById(R.id.update);
        btnDelete = findViewById(R.id.delete);
        tvKQ = findViewById(R.id.kq);
        btnSelect.setOnClickListener(v -> {
            SelectData();
        });
        btnInsert.setOnClickListener(v -> {
            InsertData();
        });
        btnUpdate.setOnClickListener(v -> {
            UpdateData();
        });
        btnDelete.setOnClickListener(v -> {
            DeleteData();
        });
    }

    private void InsertData(){
        //tao doi tuong retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.33.50.236/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // chuan bi ham insert
        InterfaceInsertPrd interfaceInsertPrd = retrofit.create(InterfaceInsertPrd.class);
        Prod p = new Prod();
        p.setId(txtId.getText().toString());
        p.setName(txtName.getText().toString());
        p.setPrice(txtPrice.getText().toString());
        p.setDescription(txtDescription.getText().toString());
        Call<ResponseInsertPrd> call= interfaceInsertPrd.insertPrd(p.getId(),p.getName(),p.getPrice(),p.getDescription());
        //thuc thi ham
        call.enqueue(new Callback<ResponseInsertPrd>() {
            @Override
            public void onResponse(Call<ResponseInsertPrd> call, Response<ResponseInsertPrd> response) {
                //thanh cong
                ResponseInsertPrd responseInsertPrd = response.body();
                tvKQ.setText(responseInsertPrd.getMessage());
            }

            @Override
            public void onFailure(Call<ResponseInsertPrd> call, Throwable t) {
                //that bai
                tvKQ.setText(t.getMessage());
            }
        });
    }
    private void SelectData(){
        strKq="";
        //b1 Create retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.33.50.236/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2 prepare the select function
        InterfaceSelectPrd interfaceSelectPrd = retrofit.create(InterfaceSelectPrd.class);
        Call<ResponseSelectPrd> call = interfaceSelectPrd.GetData();
        //b3 excute function
        call.enqueue(new Callback<ResponseSelectPrd>() {
            @Override
            public void onResponse(Call<ResponseSelectPrd> call, Response<ResponseSelectPrd> response) {
                //thanh cong
                ResponseSelectPrd responseSelectPrd = response.body();
                ls= Arrays.asList(responseSelectPrd.getProducts());
                for(Prod p: ls){
                    strKq+="ID: "+ p.getId()+"; Name: "+p.getName()+"; Price: "+p.getPrice()+"; Description: "+p.getDescription()+"\n";
                }
                tvKQ.setText(strKq);
            }

            @Override
            public void onFailure(Call<ResponseSelectPrd> call, Throwable t) {
                //that bai
                tvKQ.setText(t.getMessage());
            }
        });
    }

    private void UpdateData(){
        strKq="";
        //b1 Create retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.33.50.236/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2 prepare function
        InterfaceInsertPrd interfaceInsertPrd = retrofit.create(InterfaceInsertPrd.class);
        Call<ResponseInsertPrd> call = interfaceInsertPrd.updatePrd(txtId.getText().toString(),txtName.getText().toString(), txtPrice.getText().toString(), txtDescription.getText().toString());
        //thuc thi ham
        call.enqueue(new Callback<ResponseInsertPrd>() {
            @Override
            public void onResponse(Call<ResponseInsertPrd> call, Response<ResponseInsertPrd> response) {
                ResponseInsertPrd responseInsertPrd = response.body();
                tvKQ.setText(responseInsertPrd.getMessage());
            }

            @Override
            public void onFailure(Call<ResponseInsertPrd> call, Throwable t) {
                tvKQ.setText(t.getMessage());
            }
        });
    }

    private void DeleteData(){
        strKq="";
        //b1 Create retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.33.50.236/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2 prepare function
        InterfaceInsertPrd interfaceInsertPrd = retrofit.create(InterfaceInsertPrd.class);
        Call<ResponseInsertPrd> call = interfaceInsertPrd.deletePrd(txtId.getText().toString());
        //thuc thi ham
        call.enqueue(new Callback<ResponseInsertPrd>() {
            @Override
            public void onResponse(Call<ResponseInsertPrd> call, Response<ResponseInsertPrd> response) {
                ResponseInsertPrd responseInsertPrd = response.body();
                tvKQ.setText(responseInsertPrd.getMessage());
            }

            @Override
            public void onFailure(Call<ResponseInsertPrd> call, Throwable t) {
                tvKQ.setText(t.getMessage());
            }
        });
    }
}