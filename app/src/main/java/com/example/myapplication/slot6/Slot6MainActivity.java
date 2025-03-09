package com.example.myapplication.slot6;

import android.content.Context;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Slot6MainActivity extends AppCompatActivity {
    ListView lv;
    Button btnload, btninsert, btnedit, btndelete;
    EditText txtmasp, txttensp, txtsl;
    Context context=this;
    Slot6SanPhamDAO dao;
    List<Slot6SanPham> ls = new ArrayList<>();
    Slot6Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot6_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lv=findViewById(R.id.slot6_lv);
        btnload=findViewById(R.id.slot6_btnload);
        btninsert=findViewById(R.id.slot6_btninsert);
        btnedit=findViewById(R.id.slot6_btnedit);
        btndelete=findViewById(R.id.slot6_btndelete);
        txtmasp=findViewById(R.id.slot6_txtmasp);
        txttensp=findViewById(R.id.slot6_txttensp);
        txtsl=findViewById(R.id.slot6_txtsl);
        dao= new Slot6SanPhamDAO(context); // tao db va bang du lieu
        ls=dao.getAllSp();
        adapter= new Slot6Adapter(ls,context);
        lv.setAdapter(adapter);
        btnload.setOnClickListener(v -> {
            ls.clear();
            ls=dao.getAllSp();
            adapter= new Slot6Adapter(ls,context);
            lv.setAdapter(adapter);
        });
        btninsert.setOnClickListener(v -> {
            Slot6SanPham p = new Slot6SanPham();
            p.setMasp(txtmasp.getText().toString());
            p.setTensp(txttensp.getText().toString());
            p.setSl(Integer.parseInt(txtsl.getText().toString()));
            int result= dao.insertSP(p);
            if(result == 1){
                Toast.makeText(getApplicationContext(),"Insert thanh cong",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(),"Insert that bai",Toast.LENGTH_LONG).show();
            }
        });
        btndelete.setOnClickListener(v -> {
            int result = dao.deleteSP(txtmasp.getText().toString());
            if(result == 1){
                Toast.makeText(getApplicationContext(),"Delete thanh cong",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(),"Delete that bai",Toast.LENGTH_LONG).show();
            }
        });
        btnedit.setOnClickListener(v -> {
            Slot6SanPham p = new Slot6SanPham();
            p.setMasp(txtmasp.getText().toString());
            p.setTensp(txttensp.getText().toString());
            p.setSl(Integer.parseInt(txtsl.getText().toString()));
            int result= dao.updateSP(p);
            if(result == 1){
                Toast.makeText(getApplicationContext(),"Update thanh cong",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(getApplicationContext(),"Update that bai",Toast.LENGTH_LONG).show();
            }
        });
    }
}