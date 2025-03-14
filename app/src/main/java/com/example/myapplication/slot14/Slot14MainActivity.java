package com.example.myapplication.slot14;

import android.content.Context;
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
import com.example.myapplication.Slot13.Prod;

import java.util.ArrayList;
import java.util.List;

public class Slot14MainActivity extends AppCompatActivity {
    EditText txtId, txtName, txtPrice, txtDescription;
    Button btnSelect, btnInsert, btnUpdate, btnDelete;
    TextView tvKQ;
    String strKq = "";
    List<Prod> ls= new ArrayList<>();
    Context context =this;
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
            FunctionVolley functionVolley = new FunctionVolley();
            functionVolley.read_json_Array_of_objects(context,tvKQ);
        });
        btnInsert.setOnClickListener(v -> {
            FunctionVolley functionVolley = new FunctionVolley();
            functionVolley.insertVolley(context,tvKQ,txtId.getText().toString(),txtName.getText().toString(),txtPrice.getText().toString(),txtDescription.getText().toString() );
        });
    }
}