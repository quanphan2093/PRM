package com.example.myapplication.SLot5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import org.w3c.dom.Text;

import java.util.List;

public class Slot51_Adapter extends BaseAdapter {
    private Context context;
    private List<Slot51_Product> list;

    public Slot51_Adapter(Context context, List<Slot51_Product> list) {
        this.context = context;
        this.list = list;
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
        Slot51_ViewHolder holder;
        if(convertView == null){
            convertView= LayoutInflater.from(context).inflate(R.layout.slot41itemview,parent,false);
            holder= new Slot51_ViewHolder();
            holder.img=convertView.findViewById(R.id.slot41_itemview_img);
            holder.tvId=convertView.findViewById(R.id.slot41_itemview_name);
            holder.ivName=convertView.findViewById(R.id.slot41_itemview_age);
            holder.tvPrice=convertView.findViewById(R.id.slot41_itemview_price);
            convertView.setTag(holder);
        }
        else{
            holder= (Slot51_ViewHolder) convertView.getTag();
        }
        Slot51_Product p = list.get(position);
        holder.img.setImageResource(R.drawable.android);
        holder.tvId.setText(p.getId());
        holder.tvPrice.setText(String.valueOf(p.getPrice()));
        holder.ivName.setText(p.getName());
        return convertView;
    }

    static class Slot51_ViewHolder{
        ImageView img;
        TextView tvId,ivName,tvPrice;
    }
}
