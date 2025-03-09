package com.example.myapplication.Slot8;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Slot8Adapter extends BaseAdapter {
    private List<ProductSl8> list;
    private Context context;

    public Slot8Adapter(List<ProductSl8> list, Context context) {
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
        Slot8ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.slot8_itemview, parent,false);
            viewHolder = new Slot8ViewHolder();
            viewHolder.img=convertView.findViewById(R.id.slot8_itemview_img);
            viewHolder.tvSl=convertView.findViewById(R.id.slot8_itemview_sl);
            viewHolder.tvBranch=convertView.findViewById(R.id.slot8_itemview_branch);
            viewHolder.tvStyleId=convertView.findViewById(R.id.slot8_itemview_styleid);
            viewHolder.tvPrice=convertView.findViewById(R.id.slot8_itemview_price);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder= (Slot8ViewHolder) convertView.getTag();
        }
        ProductSl8 p = list.get(position);
        if(p != null){
            viewHolder.tvPrice.setText(p.getPrice());
            viewHolder.tvStyleId.setText(p.getStyleid());
            viewHolder.tvSl.setText("1");
            viewHolder.tvBranch.setText(p.getProduct_additional_info());
            Picasso.get().load(p.getSearch_image()).into(viewHolder.img); //doc anh tu mang
        }
        //---event---
        convertView.setOnClickListener(v ->{
            ProductSl8 productSl8 = list.get(position);
            Intent intent = new Intent(context,Slot9DetailActivity.class);
            intent.putExtra("PRODUCT",productSl8);
            context.startActivity(intent);
        });
        //---edn event...
        return convertView;
    }

    static class Slot8ViewHolder{
        ImageView img;
        TextView tvStyleId, tvBranch, tvSl, tvPrice;
    }
}
