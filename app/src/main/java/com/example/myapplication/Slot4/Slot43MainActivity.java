package com.example.myapplication.Slot4;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Slot43MainActivity extends AppCompatActivity {
    ListView lv;
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
        //B1: tao itemView (da tao)
        //B2: tao datasource
        List<Map<String, Object>> list = new ArrayList<>();
        HashMap<String,Object> hashMap = new HashMap<>();
        hashMap.put("name","Quan");
        hashMap.put("age","22");
        hashMap.put("pic",R.drawable.android);
        list.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("name","Quan");
        hashMap.put("age","23");
        hashMap.put("pic",R.drawable.apple);
        list.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put("name","Quan");
        hashMap.put("age","24");
        hashMap.put("pic",R.drawable.chrome);
        list.add(hashMap);

        //B3: anh xa nguon du lieu
        String[] from={"name","age","pic"};
        int[] to ={R.id.slot41_itemview_name,R.id.slot41_itemview_age,R.id.slot41_itemview_img};
        //B4: tao adapter
        SimpleAdapter adapter = new SimpleAdapter(this,list,R.layout.slot41itemview,from,to);
        //B5: dua du lieu len listview
        lv.setAdapter(adapter);
    }
}