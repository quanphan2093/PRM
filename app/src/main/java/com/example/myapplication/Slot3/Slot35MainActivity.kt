package com.example.myapplication.Slot3

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R

class Slot35MainActivity : AppCompatActivity() {
    var lv: ListView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_slot35_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lv = findViewById<ListView>(R.id.slot35lv)
        getDataToListView()
    }

    private fun getDataToListView() {
        val arr = listOf<String>("a","b","c","d","e")
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1,arr)
        lv!!.adapter= adapter
    }
}