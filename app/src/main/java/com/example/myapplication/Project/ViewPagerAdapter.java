package com.example.myapplication.Project;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private final CartFragment cartFragment = new CartFragment();
    private final OrderFragment orderFragment = new OrderFragment();

    public ViewPagerAdapter(@NonNull CartFragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return position == 0 ? cartFragment : orderFragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public OrderFragment getOrderFragment() {
        return orderFragment;
    }
}
