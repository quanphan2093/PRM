package com.example.myapplication.Slot8;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Slot8MainActivity extends AppCompatActivity {
    ListView lv;
    List<ProductSl8> ls = new ArrayList<>();
    Slot8Adapter adapter;
    Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_slot8_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lv = findViewById(R.id.slot8_listview);
        adapter = new Slot8Adapter(ls,context);
        lv.setAdapter(adapter);
        //lay du lieu tu server
        new FetchProductTask().execute();
    }

    private class FetchProductTask extends AsyncTask<Void, Void, String> {
        //doc du lieu tu server
        @Override
        protected String doInBackground(Void... voids) {
            StringBuilder response = new StringBuilder(); //chua du lieu doc duoc
            try{
                //duong dan doc du lieu
                URL url = new URL("https://hungnttg.github.io/shopgiay.json");
                //ket noi
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                //thiet lap phuong thuc doc du lieu
                connection.setRequestMethod("GET");
                //tao buffer doc du lieu
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                //doc theo tung dong du lieu
                String line = "";//dong de luu du lieu
                while ((line=reader.readLine()) != null){
                    response.append(line);//add line vao response
                }
                reader.close();
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return response.toString();
        }
        //tra ket qua ve cho client
        @Override
        protected void onPostExecute(String result) {
            //xu li ket qua
            if(result != null && !result.isEmpty()){ //not null va not blank
                try{
                    //lay ve doi tuong JSON
                    JSONObject json = new JSONObject(result);
                    //lay ve mang Product
                    JSONArray productArray = json.getJSONArray("products");
                    //su dung vong lap de doc tung doi tuong con
                    for(int i = 0;i<productArray.length();i++){
                        //lay ve 1 doi tuong
                        JSONObject productobject = productArray.getJSONObject(i);
                        //lay ve cac truong trong doi tuong con
                        String styleId = productobject.getString("styleid");
                        String brands_filter_facet= productobject.getString("brands_filter_facet");
                        String price =productobject.getString("price");
                        String searchImage=productobject.getString("search_image");
                        String product_additional_info = productobject.getString("product_additional_info");
                        //tao doi tuong moi chua du lieu doc duoc
                        ProductSl8 productSl8 = new ProductSl8(searchImage,styleId,price,brands_filter_facet);
                        //them doi tuong vao list
                        ls.add(productSl8);
                    }
                    adapter.notifyDataSetChanged();//cap nhat lai adapter
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                Toast.makeText(getApplicationContext(),"Loi doc du lieu",Toast.LENGTH_LONG).show();
            }
            super.onPostExecute(result);
        }
    }
}