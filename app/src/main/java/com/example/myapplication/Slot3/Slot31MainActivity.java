package com.example.myapplication.Slot3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

public class Slot31MainActivity extends AppCompatActivity {
    EditText txt1, txt2,txt3;
    Button btn1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot31_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        txt1=findViewById(R.id.slot31txt);
        txt2=findViewById(R.id.slot32txt);
        txt3=findViewById(R.id.slot33txt);
        btn1= findViewById(R.id.slot31btn1);
        btn1.setOnClickListener(x -> {
            sendData();
        });
    }

    private void sendData() {
        int a =Integer.parseInt(txt1.getText().toString());
        int b =Integer.parseInt(txt2.getText().toString());
        int c =Integer.parseInt(txt3.getText().toString());
        Intent intent=new Intent(this, Slot32MainActivity.class);
        intent.putExtra("a",a);
        intent.putExtra("b",b);
        intent.putExtra("c",c);
        startActivity(intent);
    }
}