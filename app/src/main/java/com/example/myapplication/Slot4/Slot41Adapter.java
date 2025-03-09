package com.example.myapplication.Slot4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

public class Slot41Adapter extends BaseAdapter {
    private Context context;
    private List<Slot41SinhVien> list;

    public Slot41Adapter(Context context, List<Slot41SinhVien> list) {
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
        //create view
        Slot41_ViewHolder holder;
        if(convertView==null){ // if not exist view -> create a new view
            convertView= LayoutInflater.from(context)
                    .inflate(R.layout.slot41itemview,parent,false);
            //refer to the view holder
            holder = new Slot41_ViewHolder();
            holder.im_pic=convertView.findViewById(R.id.slot41_itemview_img);
            holder.tv_name=convertView.findViewById(R.id.slot41_itemview_name);
            holder.tv_age=convertView.findViewById(R.id.slot41_itemview_age);
            //create a template for later
            convertView.setTag(holder);
        }else{//exist view -> use old view
            holder=(Slot41_ViewHolder) convertView.getTag();
        }
        Slot41SinhVien sv = list.get(position);
        holder.im_pic.setImageResource(sv.getPic());
        holder.tv_name.setText(sv.getName());
        holder.tv_age.setText(sv.getAge());
        //set data for view
        return convertView;
    }

    //create a class for refer to the item view
    static class Slot41_ViewHolder{
        ImageView im_pic;
        TextView tv_name;
        TextView tv_age;
    }
}
