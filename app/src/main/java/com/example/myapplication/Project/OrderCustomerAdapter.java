package com.example.myapplication.Project;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.Project.Models.OrderCustomer;
import com.example.myapplication.Project.Models.Payment;
import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class OrderCustomerAdapter extends BaseAdapter {
    private List<OrderCustomer> list;
    private Context context;

    public OrderCustomerAdapter(List<OrderCustomer> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OrderViewHoldere viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.order_customer_adapter, parent, false);
            viewHolder = new OrderViewHoldere();
            viewHolder.ProdName = convertView.findViewById(R.id.order_product_name);
            viewHolder.Phone = convertView.findViewById(R.id.order_phone);
            viewHolder.Price = convertView.findViewById(R.id.order_price);
            viewHolder.Quantity = convertView.findViewById(R.id.order_customer_quantity);
            viewHolder.Address = convertView.findViewById(R.id.order_address);
            viewHolder.img = convertView.findViewById(R.id.image_order);
            viewHolder.Status = convertView.findViewById(R.id.order_customer_status);
            viewHolder.btnCancel = convertView.findViewById(R.id.btnCancel);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (OrderViewHoldere) convertView.getTag();
        }

        OrderCustomer order = list.get(position);
        if (order != null) {
            viewHolder.ProdName.setText(order.getProductName());
            viewHolder.Phone.setText(order.getPhoneNumber());
            viewHolder.Price.setText(String.valueOf(order.getPrice()));
            viewHolder.Quantity.setText(String.valueOf(order.getQuantity()));
            viewHolder.Address.setText(order.getLocation());
            viewHolder.Status.setText(order.getStatus());
            Picasso.get()
                    .load(order.getImage()) // URL ảnh
                    .placeholder(R.drawable.cart) // Ảnh tạm
                    .error(R.drawable.border) // Nếu lỗi
                    .into(viewHolder.img);
            if (order.getStatusId() == 1) {
                viewHolder.btnCancel.setVisibility(View.GONE);
            } else {
                viewHolder.btnCancel.setVisibility(View.VISIBLE);
            }
            viewHolder.btnCancel.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Xác nhận hủy đơn");
                builder.setMessage("Bạn có chắc chắn muốn hủy đơn hàng này không?");

                builder.setPositiveButton("Có", (dialog, which) -> {
                    order.setStatusId(2);
                    notifyDataSetChanged();
                });

                builder.setNegativeButton("Không", (dialog, which) -> dialog.dismiss());

                AlertDialog dialog = builder.create();
                dialog.show();
            });

        }
        return convertView;
    }
    public void updateData(List<OrderCustomer> newList) {
        list.clear();
        list.addAll(newList);
        notifyDataSetChanged();
    }
    static class OrderViewHoldere{
        ImageView img;
        TextView ProdName, Quantity,Price, Phone,Address, Status;
        Button btnCancel;
    }
}
