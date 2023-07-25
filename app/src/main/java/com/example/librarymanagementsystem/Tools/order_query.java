package com.example.librarymanagementsystem.Tools;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;


import com.example.librarymanagementsystem.entity.order;
import com.example.librarymanagementsystem.R;

import java.util.ArrayList;
import java.util.List;

public class order_query {
    Activity activity;
    SQLiteDatabase DB;
    String Order_Table_Name;
    public order_query(Activity activity,SQLiteDatabase DB,String Order_Table_Name) {
        this.activity=activity;
        this.DB=DB;
        this.Order_Table_Name=Order_Table_Name;
    }

    public List<order> order_query_to() {
        if (DB.isOpen()) {
            //返回游标
            Cursor cursor = DB.rawQuery("select * from "+Order_Table_Name+";", null);
            List<order> orders= new ArrayList<>();
            ListView listView = (ListView) activity.findViewById(R.id.order_activity_send_listview);

            //迭代游标
            while (cursor.moveToNext()) {

                order order = new order();
                order.setBook_name(cursor.getString(0));
                order.setBook_number(cursor.getInt(2));
                orders.add(order);
            }
            // listView.setAdapter(new book_neededAdapter(book_neededListList,this));

            cursor.close();
            DB.close();

            return orders;
        }
        return null;
    }
}
