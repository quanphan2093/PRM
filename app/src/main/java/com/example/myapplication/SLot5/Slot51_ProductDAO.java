package com.example.myapplication.SLot5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class Slot51_ProductDAO {
    private Slot51_SqLiteHelper sqLiteHelper;
    private SQLiteDatabase db;
    private Context context;
    //goi ham tao database va bang du lieu
    public Slot51_ProductDAO(Context context) {
        this.context = context;
        sqLiteHelper= new Slot51_SqLiteHelper(context);
        db = sqLiteHelper.getWritableDatabase();
    }

    //insert du lieu
    public int insertProduct(Slot51_Product p){
        ContentValues values= new ContentValues();//data for insert
        //put data
        values.put("id",p.getId());
        values.put("name",p.getName());
        values.put("price",p.getPrice());
        values.put("img",p.getImage());
        //execute
        if(db.insert("Product",null,values)<0){
            return -1;
        }
        return 1;
    }

    public List<Slot51_Product> getAllData(){
        List<Slot51_Product> list = new ArrayList<>();
        //cursor for read data
        Cursor c = db.query("Product",null,null,null,null,null,null);
        c.moveToFirst();
        while (!c.isAfterLast()){//neu khong phai ban ghi cuoi cung thi tiep tuc doc
            Slot51_Product p = new Slot51_Product();
            p.setId(c.getString(0));
            p.setName(c.getString(1));
            p.setPrice(c.getDouble(2));
            list.add(p);
            c.moveToNext();
        }
        c.close();
        return list;
    }
}
