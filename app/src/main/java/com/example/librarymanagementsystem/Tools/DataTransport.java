package com.example.librarymanagementsystem.Tools;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.librarymanagementsystem.entity.order;

public class DataTransport {//数据转移工具类，将同类数据在表中转移，模拟通过互联网传输数据
    SQLiteDatabase DB;
    String table1;//被转移表
    String table2;//目标表

    public DataTransport(SQLiteDatabase DB, String table1, String table2) {
        this.DB = DB;
        this.table1 = table1;
        this.table2 = table2;
    }
    public void data_tansport(){
        Cursor cursor = DB.rawQuery("select * from "+ table1, null);

        while (cursor.moveToNext()) {

            order order = new order();
            order.setBook_name(cursor.getString(0));
            order.setBook_number(cursor.getInt(2));
            order_insert order_insert = new order_insert(DB,table2,order);
            order_insert.order_inset_to();
            String sql = "DELETE FROM "+ table1 +" WHERE _book_name ='" + order.getBook_name() +"';";
            DB.execSQL(sql);

        }
    }
}
