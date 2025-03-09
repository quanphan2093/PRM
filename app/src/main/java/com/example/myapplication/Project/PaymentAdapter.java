package com.example.myapplication.Project;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.myapplication.Project.Models.Cart;
import com.example.myapplication.Project.Models.Payment;
import com.example.myapplication.Project.Models.Product;
import com.example.myapplication.R;

import java.util.List;

public class PaymentAdapter extends BaseAdapter {
    private List<Payment> list;
    private Context context;
    private int selectedPosition = -1; // To track selected radio button

    public PaymentAdapter(List<Payment> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PaymentViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.payment_adapter, parent, false);
            viewHolder = new PaymentViewHolder();
            viewHolder.tv = convertView.findViewById(R.id.payment_paymentMethod);
            viewHolder.btnRadio = convertView.findViewById(R.id.payment_radio);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (PaymentViewHolder) convertView.getTag();
        }

        Payment p = list.get(position);
        if (p != null) {
            viewHolder.tv.setText(p.getPaymentMethod());
        }
        viewHolder.btnRadio.setChecked(position == selectedPosition);
        viewHolder.btnRadio.setOnClickListener(v -> {
            selectedPosition = position;
            int selectedProductId = list.get(selectedPosition).getPaymentId();
            notifyDataSetChanged();
            Intent intent = new Intent();
            intent.putExtra("SELECTED_PAYMENT_ID", selectedProductId);
            ((Activity) context).setResult(Activity.RESULT_OK, intent);
        });

        return convertView;
    }
    public void updateData(List<Payment> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }

    static class PaymentViewHolder {
        TextView tv;
        RadioButton btnRadio;
    }
}
