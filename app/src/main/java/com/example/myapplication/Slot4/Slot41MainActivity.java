package com.example.myapplication.Slot4;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class Slot41MainActivity extends AppCompatActivity {

    ListView lv;
    Slot41Adapter adapter;
    List<Slot41SinhVien> list= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot41_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lv=findViewById(R.id.slot41lv);
        list.add(new Slot41SinhVien("Nguyen Van A","18",R.drawable.android));
        list.add(new Slot41SinhVien("Nguyen Van B","19",R.drawable.blogger));
        list.add(new Slot41SinhVien("Nguyen Van C","20",R.drawable.border));
        list.add(new Slot41SinhVien("Nguyen Van D","21",R.drawable.chrome));
        list.add(new Slot41SinhVien("Nguyen Van E","2",R.drawable.hp));

        adapter=new Slot41Adapter(this,list);
        lv.setAdapter(adapter);
    }
}