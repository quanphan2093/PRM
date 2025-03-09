package com.example.myapplication.slot6;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.myapplication.SLot5.Slot51_SqLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class Slot6SanPhamDAO {
    private Slot6DbHelper helper;
    private SQLiteDatabase db;
    private Context context;

    public Slot6SanPhamDAO(Context context) {
        this.context = context;
        helper = new Slot6DbHelper(context);
        db=helper.getWritableDatabase();
    }

    //them
    public int insertSP(Slot6SanPham sp){
        ContentValues values = new ContentValues();
        values.put("masp",sp.getMasp());
        values.put("tensp",sp.getTensp());
        values.put("sl",String.valueOf(sp.getSl()));
        if(db.insert("sanpham",null,values)<0){
            return -1;
        }
        return 1;
    }

    //sua
    public int updateSP(Slot6SanPham sp){
        ContentValues values = new ContentValues();
        values.put("masp",sp.getMasp());
        values.put("tensp",sp.getTensp());
        values.put("sl",String.valueOf(sp.getSl()));
        if(db.update("sanpham",values,"masp=?",new String[]{sp.getMasp()})<0){
            return -1;
        }
        return 1;
    }

    //xoa
    public int deleteSP(String masp){
        if(db.delete("sanpham","masp=?",new String[]{masp})<0){
            return -1;
        }
        return 1;
    }

    //hien thi
    public List<Slot6SanPham> getAllSp(){
        List<Slot6SanPham> ls = new ArrayList<>();
        Cursor c = db.query("sanpham",null,null,null,null,null,null);
        c.moveToFirst();
        while (!c.isAfterLast()){
            Slot6SanPham sp = new Slot6SanPham();
            sp.setMasp(c.getString(0));
            sp.setTensp(c.getString(1));
            sp.setSl(c.getInt(2));
            ls.add(sp);
            c.moveToNext();
        }
        c.close();
        return ls;
    }
}
