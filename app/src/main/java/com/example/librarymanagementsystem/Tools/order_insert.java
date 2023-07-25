package com.example.librarymanagementsystem.Tools;

import android.database.sqlite.SQLiteDatabase;

import com.example.librarymanagementsystem.entity.*;
public class order_insert {//用于order类型的插入
    SQLiteDatabase DB;
    String tableName;
    order order;

    public order_insert(SQLiteDatabase DB, String tableName, com.example.librarymanagementsystem.entity.order order) {
        this.DB = DB;
        this.tableName = tableName;
        this.order = order;
    }

    public void order_inset_to(){
        if(DB.isOpen()){
            String sql = "insert into " + tableName + "(_book_name,book_number) values('"+ order.getBook_name() + "'," + order.getBook_number() + ");";
            DB.execSQL(sql);
        }
    }
}
