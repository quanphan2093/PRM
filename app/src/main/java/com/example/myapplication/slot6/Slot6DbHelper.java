package com.example.myapplication.slot6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Slot6DbHelper extends SQLiteOpenHelper {
    public Slot6DbHelper(Context context) {
        super(context, "CSDLSP",null,1); // tao csdl khi duoc goi
    }

    //tao bang du lieu
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE sanpham (\n" +
                " masp TEXT PRIMARY KEY,\n" +
                " tensp TEXT,\n" +
                " sl TEXT\n" +
                ")");
    }

    //upgrade csdl
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if EXISTS sanpham");
    }
}
