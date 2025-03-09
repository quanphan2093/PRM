package com.example.myapplication.slot6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.List;

public class Slot6Adapter extends BaseAdapter {
    private List<Slot6SanPham> ls;
    private Context context;

    public Slot6Adapter(List<Slot6SanPham> ls, Context context) {
        this.ls = ls;
        this.context = context;
    }

    @Override
    public int getCount() {
        return ls.size();
    }

    @Override
    public Object getItem(int position) {
        return ls.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Slot6ViewHolder holder ;
        if(convertView == null){
            holder= new Slot6ViewHolder();
            convertView= LayoutInflater.from(context)
                    .inflate(R.layout.slot6_itemview,parent,false);
            holder.img=convertView.findViewById(R.id.slot6_itemview_img);
            holder.tvmasp=convertView.findViewById(R.id.slot6_itemview_masp);
            holder.tvtensp=convertView.findViewById(R.id.slot6_itemview_tensp);
            holder.tvsl=convertView.findViewById(R.id.slot6_itemview_sl);
            holder.btnSua=convertView.findViewById(R.id.slot6_itemview_btnsua);
            holder.btnXoa=convertView.findViewById(R.id.slot6_itemview_btnxoa);
            convertView.setTag(holder);
        }else{
            holder = (Slot6ViewHolder) convertView.getTag();
        }
        Slot6SanPham sp = ls.get(position);
        if(sp!=null){
            holder.img.setImageResource(R.drawable.android);
            holder.tvmasp.setText(sp.getMasp());
            holder.tvtensp.setText(sp.getTensp());
            holder.tvsl.setText(String.valueOf(sp.getSl()));

            //xu li su kien btn sua
            holder.btnSua.setOnClickListener(v -> {
                //bt
                //mo dialog va truyen du lieu vao dialog
                //sau khi sua dong dialog va tra du lieu va activity

                //C2: dung intent
            });

            //xu li su kien btn xoa
            holder.btnXoa.setOnClickListener(v -> {
                Slot6SanPhamDAO dao = new Slot6SanPhamDAO(context);
                dao.deleteSP(ls.get(position).getMasp());
                ls.remove(position);
                notifyDataSetChanged();
            });
        }
        return convertView;
    }

    static class Slot6ViewHolder{
        ImageView img;
        TextView tvmasp,tvtensp,tvsl;
        Button btnSua,btnXoa;
    }
}
