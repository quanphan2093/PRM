package com.example.myapplication.Slot3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class Slot32MainActivity extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot32_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv= findViewById(R.id.Slot32tv1);
        Intent i = getIntent();
        int a =i.getExtras().getInt("a");
        int b =i.getExtras().getInt("b");
        int c =i.getExtras().getInt("c");
        int delta = b*b -4*a*c;
        if(a==0){
            tv.setText("Phuong trinh co 1 nghiem: x = "+-c/b);
        }
        else{
            if(delta<0){
                tv.setText("Phuong trinh vo nghiem");
            }
            else if(delta ==0){
                tv.setText("Phuong trinh co nghiem kep: x=" +-b/(2*a));
            }else {
                tv.setText("Phuong trinh co 2 nghiem: x1="+(-b+Math.sqrt(delta))/(2*a) + "va x2 ="+(-b-Math.sqrt(delta))/(2*a));
            }
        }
    }
}