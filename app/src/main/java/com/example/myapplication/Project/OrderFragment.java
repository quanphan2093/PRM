package com.example.myapplication.Project;

import static com.example.myapplication.Project.URL.PublicURL.URL_STRING;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Project.Models.Address;
import com.example.myapplication.Project.Models.OrderDetail;
import com.example.myapplication.Project.Models.Payment;
import com.example.myapplication.Project.Models.Product;
import com.example.myapplication.Project.Response.InterfacrOrder;
import com.example.myapplication.Project.Response.ResponeProduct;
import com.example.myapplication.Project.Response.ResponseOrder;
import com.example.myapplication.Project.Response.ResponseSelectPayment;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrderFragment extends Fragment {
    ListView lvProduct, lvPayment;
    EditText location, phoneNumber, Note;
    Button btnOrder;
    private OrderProductAdapter orderAdapter;
    private List<Product> selectedProducts;
    private PaymentAdapter paymentAdapter;
    private List<Payment> payments = new ArrayList<>();
    String strKq="";
    private int newAddressId = -1;
    private int newOrderId = -1;
    private Context context;

    public OrderFragment() {
        // Constructor rỗng
    }

    public void setSelectedProducts(List<Product> products) {
        this.selectedProducts = products;
        if (orderAdapter != null) {
            orderAdapter.updateData(selectedProducts);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_order, container, false);

        context = getActivity();
        lvProduct = view.findViewById(R.id.list_product_cart);
        lvPayment = view.findViewById(R.id.list_payment);
        location = view.findViewById(R.id.order_location);
        phoneNumber = view.findViewById(R.id.order_phone_number);
        Note = view.findViewById(R.id.order_note);
        btnOrder = view.findViewById(R.id.order_btnOrder);

        if (getArguments() != null) {
            selectedProducts = getArguments().getParcelableArrayList("selectedProducts");
        }
        if (selectedProducts == null) {
            selectedProducts = new ArrayList<>();
        }
        orderAdapter = new OrderProductAdapter(context, selectedProducts);
        lvProduct.setAdapter(orderAdapter);
        paymentAdapter = new PaymentAdapter(payments, context);
        lvPayment.setAdapter(paymentAdapter);
        SelectData();
        btnOrder.setOnClickListener(v -> {
            Toast.makeText(context, "Đặt hàng thành công!", Toast.LENGTH_SHORT).show();
        });

        return view;
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
        Call<ResponseSelectPayment> call = interfacrOrder.GetData();
        //b3 excute function
        call.enqueue(new Callback<ResponseSelectPayment>() {
            @Override
            public void onResponse(Call<ResponseSelectPayment> call, Response<ResponseSelectPayment> response) {
                if (response.body() != null && response.body().getPayments() != null) {
                    // Update existing list instead of reassigning it
                    payments= Arrays.asList(response.body().getPayments());
                    paymentAdapter.updateData(payments);
                }
            }
            @Override
            public void onFailure(Call<ResponseSelectPayment> call, Throwable t) {
                strKq=t.getMessage();
            }
        });
    }
//    private void InsertAddress() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(URL_STRING)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        InterfacrOrder interfacrOrder = retrofit.create(InterfacrOrder.class);
//        Address address = new Address();
//        address.setLocation(location.getText().toString());
//        address.setPhoneNumber(phoneNumber.getText().toString());
//        address.setNote(Note.getText().toString());
//        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
//        String userId = sharedPreferences.getString("userId", "");
//        Call<ResponseOrder> call = interfacrOrder.insertAddress(
//                address.getLocation(), address.getPhoneNumber(), address.getNote(), Integer.parseInt(userId)
//        );
//
//        call.enqueue(new Callback<ResponseOrder>() {
//            @Override
//            public void onResponse(Call<ResponseOrder> call, Response<ResponseOrder> response) {
//                if (response.body() != null) {
//                    newAddressId = response.body().getId(); // Lấy ID mới
//                    InsertOrder(); // Gọi tiếp InsertOrder() khi InsertAddress thành công
//                } else {
//                    Toast.makeText(getApplicationContext(), "Thêm địa chỉ thất bại!", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseOrder> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), "Lỗi mạng khi thêm địa chỉ!", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    // Bước 2: Insert Order sau khi có Address ID
//    private void InsertOrder() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(URL_STRING)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        InterfacrOrder interfacrOrder = retrofit.create(InterfacrOrder.class);
//        int paymentId = getIntent().getIntExtra("SELECTED_PAYMENT_ID", 1);
//        SharedPreferences sharedPreferences = getSharedPreferences("MyAppPrefs", MODE_PRIVATE);
//        String userId = sharedPreferences.getString("userId", "");
//        Call<ResponseOrder> call = interfacrOrder.insertOrder(Integer.parseInt(userId), paymentId, newAddressId,1);
//        call.enqueue(new Callback<ResponseOrder>() {
//            @Override
//            public void onResponse(Call<ResponseOrder> call, Response<ResponseOrder> response) {
//                if (response.body() != null) {
//                    newOrderId = response.body().getId(); // Lấy Order ID mới
//                    InsertOrderDetail(); // Gọi tiếp InsertOrderDetail() khi InsertOrder thành công
//                } else {
//                    Toast.makeText(getApplicationContext(), "Tạo đơn hàng thất bại!", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseOrder> call, Throwable t) {
//                Log.e("OrderActivity", "Lỗi mạng khi tạo đơn hàng: " + t.getMessage(), t);
//                Toast.makeText(getApplicationContext(), "Lỗi mạng khi tạo đơn hàng!", Toast.LENGTH_LONG).show();
//            }
//        });
//    }
//
//    // Bước 3: Insert danh sách sản phẩm vào Order
//    private void InsertOrderDetail() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(URL_STRING)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        InterfacrOrder interfacrOrder = retrofit.create(InterfacrOrder.class);
//
//        for (Product pro : selectedProducts) {
//            OrderDetail orderDetail = new OrderDetail();
//            orderDetail.setQuantity(pro.getQuantity());
//            orderDetail.setProdId(pro.getProdId());
//            orderDetail.setPrice(pro.getPrice());
//
//            Call<ResponeProduct> call = interfacrOrder.insertOrderDetail(
//                    newOrderId, orderDetail.getProdId(), orderDetail.getQuantity(), orderDetail.getPrice()
//            );
//
//            call.enqueue(new Callback<ResponeProduct>() {
//                @Override
//                public void onResponse(Call<ResponeProduct> call, Response<ResponeProduct> response) {
//                    Toast.makeText(getApplicationContext(), "Sản phẩm " + pro.getProdName() + " đã thêm vào đơn hàng!", Toast.LENGTH_SHORT).show();
//                }
//
//                @Override
//                public void onFailure(Call<ResponeProduct> call, Throwable t) {
//                    Toast.makeText(getApplicationContext(), "Lỗi khi thêm sản phẩm " + pro.getProdName(), Toast.LENGTH_SHORT).show();
//                }
//            });
//        }
//    }
}