package com.example.myapplication.Project;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.Project.Models.Cart;
import com.example.myapplication.Project.Models.Product;
import com.example.myapplication.R;

import java.util.List;

public class CartFragment extends Fragment implements CartAdapter.OnProductSelectedListener{
    private ListView listView;
    private CartAdapter adapter;
    private Button btnAdd;

    public CartFragment() {
        // Constructor rỗng
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_order, container, false);

//        listView = view.findViewById(R.id.list_cart);
//        btnAdd = view.findViewById(R.id.order);
//        btnAdd.setVisibility(View.GONE); // Ban đầu ẩn nút Order
//
//        // Lấy sản phẩm trong giỏ hàng
//        Cart cart = Cart.getInstance();
//        List<Product> cartItem = cart.getCartItems();
//
//        // Khởi tạo adapter
//        adapter = new CartAdapter(getActivity(), cartItem, this);
//        listView.setAdapter(adapter);
//
//        // Xử lý khi nhấn Order
//        btnAdd.setOnClickListener(v -> {
//            List<Product> selectedProducts = adapter.getSelectedProducts();
//            if (!selectedProducts.isEmpty()) {
//                addToOrder(selectedProducts);
//                // Chuyển sang OrderFragment bằng callback
//                if (getActivity() instanceof CartFragmentActivity) {
//                    ((CartFragmentActivity) getActivity()).moveToOrderFragment(selectedProducts);
//                }
//            }
//        });

        return view;
    }

    @Override
    public void onProductSelected(boolean hasSelection) {
        btnAdd.setVisibility(hasSelection ? View.VISIBLE : View.GONE);
    }

    private void addToOrder(List<Product> selectedProducts) {
        for (Product product : selectedProducts) {
            OrderManager.getInstance().addProduct(product);
        }
        Toast.makeText(getActivity(), "Đã thêm vào đơn hàng!", Toast.LENGTH_SHORT).show();
    }
}