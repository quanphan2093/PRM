package com.example.myapplication.Slot1

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R
import com.example.myapplication.R.*

class Slot12MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(layout.activity_slot12_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var tv1 = findViewById<TextView>(id.slotTv1)
        var btn1 = findViewById<Button>(id.slotBtn1)
        var tv2 =findViewById<TextView>(id.slot12tv3)
        var tv3 =findViewById<TextView>(id.slot12tv4)
        tv1.text="10";
        tv2.text="20";
        btn1.setOnClickListener({

        })
    }
}