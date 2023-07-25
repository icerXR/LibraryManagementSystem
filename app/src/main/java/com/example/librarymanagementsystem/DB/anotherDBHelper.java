package com.example.librarymanagementsystem.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class anotherDBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME="order_List.db";
    private static final int DB_VERSION=1;
    private static SQLiteOpenHelper mInstance;

    private static synchronized SQLiteOpenHelper getmInstance(Context context){
        if(mInstance==null){
            mInstance = new anotherDBHelper(context,DB_NAME,null,DB_VERSION);

        }
        return mInstance;
    }


    public anotherDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        String create_Order  ="CREATE TABLE IF NOT EXISTS Order_List" + "(" +
                "_book_name TEXT," +
                " _time TEXT," +
                " book_number INTEFER," +
                "PRIMARY KEY (_book_name,_time)"+
                ");";
        String create_Over_Order  ="CREATE TABLE IF NOT EXISTS Order_List" + "(" +
                "_book_name TEXT," +
                " _time TEXT," +
                " book_number INTEFER," +
                "PRIMARY KEY (_book_name,_time)"+
                ");";
        DB.execSQL(create_Order);
        DB.execSQL(create_Over_Order);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
