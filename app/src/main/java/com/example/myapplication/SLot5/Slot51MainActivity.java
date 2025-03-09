package com.example.myapplication.SLot5;

import android.content.Context;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Slot51MainActivity extends AppCompatActivity {
    EditText txtId, txtName, txtPrice;
    Button btnInser, btnSelect;
    ListView lv;
    Slot51_Adapter adapter;
    List<Slot51_Product> ls=new ArrayList<>();
    Context context=this;
    Slot51_ProductDAO dao;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot51_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txtId=findViewById(R.id.slot51_txtid);
        txtName=findViewById(R.id.slot51_txtname);
        txtPrice=findViewById(R.id.slot51_txtprice);
        btnInser=findViewById(R.id.slot51_btnInsert);
        btnSelect=findViewById(R.id.slot51_btnSelect);
        lv=findViewById(R.id.slot51_lv);
        dao = new Slot51_ProductDAO(context); //tao csdl va bang du lieu
        adapter= new Slot51_Adapter(context,ls);
        lv.setAdapter(adapter);
        btnInser.setOnClickListener(v -> {
                Slot51_Product p = new Slot51_Product();
                p.setId(txtId.getText().toString());
                p.setName(txtName.getText().toString());
                p.setPrice(Double.parseDouble(txtPrice.getText().toString()));
                p.setImage(1);
                dao.insertProduct(p);
        });
        btnSelect.setOnClickListener(v ->{
            ls=dao.getAllData();
            adapter= new Slot51_Adapter(context,ls);
            lv.setAdapter(adapter);
        });
    }
}