package com.example.myapplication.Slot4

import android.os.Bundle
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.R

class Slot42MainActivity : AppCompatActivity() {
    private var lv : ListView? = null
    private var adapter: Slot41_CustomAdapter? = null
    private var list= ArrayList<Slot41_Student>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_slot41_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        lv =findViewById<ListView>(R.id.slot41lv)
        list.add( Slot41_Student("Nguyen Van A","18",R.drawable.android));
        list.add( Slot41_Student("Nguyen Van B","19",R.drawable.blogger));
        list.add( Slot41_Student("Nguyen Van C","20",R.drawable.border));
        list.add( Slot41_Student("Nguyen Van D","21",R.drawable.chrome));
        list.add( Slot41_Student("Nguyen Van E","2",R.drawable.hp));
        adapter= Slot41_CustomAdapter(list,this)
        lv!!.adapter=adapter
    }
}