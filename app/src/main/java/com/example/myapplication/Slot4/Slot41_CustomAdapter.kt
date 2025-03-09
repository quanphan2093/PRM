package com.example.myapplication.Slot4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.myapplication.R

class Slot41_CustomAdapter(private val list: ArrayList<Slot41_Student>,
    private val context: Context) : BaseAdapter() {

    override fun getCount(): Int {
        return list.size
    }

    override fun getItem(position: Int): Any {
        return list.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val holder: slot41_ViewHodlerKl
        var convertView = convertView
        if(convertView == null){
            holder= slot41_ViewHodlerKl()
            convertView=LayoutInflater.from(context)
                .inflate(R.layout.slot41itemview,null)
            holder.img_pic=convertView.findViewById(R.id.slot41_itemview_img)
            holder.tv_name=convertView.findViewById(R.id.slot41_itemview_name)
            holder.tv_age=convertView.findViewById(R.id.slot41_itemview_age)
            convertView.tag=holder
        }
        else{
            holder=convertView.tag as slot41_ViewHodlerKl
        }
        holder.img_pic!!.setImageResource(list[position].pic)
        holder.tv_name!!.setText(list[position].name)
        holder.tv_age!!.setText(list[position].age)
        return convertView
    }

    internal class slot41_ViewHodlerKl{
        var img_pic: ImageView? =null
        var tv_name: TextView? =null
        var tv_age: TextView? =null
    }
}