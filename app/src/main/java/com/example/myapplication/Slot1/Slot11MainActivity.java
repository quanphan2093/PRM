package com.example.myapplication.Slot1;

import android.content.Intent;
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

public class Slot11MainActivity extends AppCompatActivity {
    //Khai bao cac id
    TextView tv1;
    EditText tv2,tv4;
    EditText tv3;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot11_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //anh xa cac id vao code java
        tv1=findViewById(R.id.txtView);
        tv2=findViewById(R.id.textNum1);
        tv3=findViewById(R.id.textNum2);
        tv4=findViewById(R.id.textNum3);
        btn1=findViewById(R.id.btn1);
        Intent intent = new Intent(Slot11MainActivity.this, Slot13MainActivity.class);
        btn1.setOnClickListener(v -> {
            int a = Integer.parseInt(tv2.getText().toString());
            int b = Integer.parseInt(tv3.getText().toString());
            int c = Integer.parseInt(tv4.getText().toString());

            String result;

            if (a == 0) {
                if (c != 0) {
                    result = "Phuong trinh co 1 nghiem: " + (-b / (double) c);
                } else {
                    result = "Phuong trinh vo nghiem (chia cho 0)";
                }
            } else {
                int delta = b * b - 4 * a * c;

                if (delta == 0) {
                    result = "Phuong trinh co nghiem kep: " + (-b / (2.0 * a));
                } else if (delta < 0) {
                    result = "Phuong trinh vo nghiem";
                } else {
                    double root1 = (-b - Math.sqrt(delta)) / (2.0 * a);
                    double root2 = (-b + Math.sqrt(delta)) / (2.0 * a);
                    result = "Phuong trinh co 2 nghiem: " + root1 + " va " + root2;
                }
            }

            intent.putExtra("result", result);
            startActivity(intent);
        });
    }
}