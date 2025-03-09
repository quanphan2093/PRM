package com.example.myapplication.SLot5;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

public class Slot51_SqLiteHelper extends SQLiteOpenHelper {
    //ham tao co so du lieu
    public Slot51_SqLiteHelper( Context context) {
        super(context, "CSDL1", null, 1);
    }

    //thuc thi cac lenh: tao bang du lieu
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE Product (\n" +
                "\t id text PRIMARY KEY,\n" +
                "\t name text,\n" +
                "\t price real,\n" +
                "\t img real\n" +
                ")");
    }

    //ham upgrade
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE if exists Product");
    }
}
