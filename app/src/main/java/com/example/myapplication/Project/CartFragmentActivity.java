package com.example.myapplication.Project;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.example.myapplication.Project.Models.Product;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.List;

public class CartFragmentActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private OrderFragment orderFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cart_fragment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        viewPager = findViewById(R.id.view_pager);
        orderFragment = new OrderFragment();

        ViewPagerAdapter adapter = new ViewPagerAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.setUserInputEnabled(false);
    }

    public void moveToOrderFragment(List<Product> selectedProducts) {
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("selectedProducts", new ArrayList<>(selectedProducts));

        OrderFragment orderFragment = new OrderFragment();
        orderFragment.setArguments(bundle);

        viewPager.setCurrentItem(1, true);
        viewPager.setUserInputEnabled(true);
    }

}