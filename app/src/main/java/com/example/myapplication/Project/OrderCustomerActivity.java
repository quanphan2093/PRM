package com.example.myapplication.Project;

import static com.example.myapplication.Project.URL.PublicURL.URL_STRING;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Project.Models.OrderCustomer;
import com.example.myapplication.Project.Models.Product;
import com.example.myapplication.Project.Response.InterfaceProduct;
import com.example.myapplication.Project.Response.InterfacrOrder;
import com.example.myapplication.Project.Response.ResponseOrderCustomer;
import com.example.myapplication.Project.Response.ResponseSelectProduct;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderCustomerActivity extends AppCompatActivity {
    ListView lv;
    List<OrderCustomer> ls = new ArrayList<>();
    OrderCustomerAdapter adapter;
    Context context = this;
    String strKq = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_customer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lv = findViewById(R.id.list_order);
        adapter =new OrderCustomerAdapter(ls, context);
        lv.setAdapter(adapter);
        SelectData();
    }

    private void SelectData(){
        strKq="";
        //b1 Create retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL_STRING)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //b2 prepare the select function
        InterfacrOrder interfacrOrder = retrofit.create(InterfacrOrder.class);
        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
        String userId = sharedPreferences.getString("userId", "");
        Call<ResponseOrderCustomer> call = interfacrOrder.GetOrder(Integer.parseInt(userId));
        //b3 excute function
        call.enqueue(new Callback<ResponseOrderCustomer>() {
            @Override
            public void onResponse(Call<ResponseOrderCustomer> call, Response<ResponseOrderCustomer> response) {
                ResponseOrderCustomer responseSelectPrd = response.body();
                ls= Arrays.asList(responseSelectPrd.getOrder());
                adapter.updateData(ls);
            }

            @Override
            public void onFailure(Call<ResponseOrderCustomer> call, Throwable t) {
                strKq +=t.getMessage();
            }
        });
    }
}