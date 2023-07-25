package com.example.librarymanagementsystem.Tools;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.ListView;


import com.example.librarymanagementsystem.entity.book_needed;
import com.example.librarymanagementsystem.R;

import java.util.ArrayList;
import java.util.List;

public class book_needed_query {
    Activity activity;
    SQLiteDatabase DB;
    public book_needed_query(Activity activity,SQLiteDatabase DB) {
        this.activity=activity;
        this.DB=DB;
    }

    public List<book_needed> book_needed_query_to() {
        if (DB.isOpen()) {
            //返回游标
            Cursor cursor = DB.rawQuery("select * from Book_needed", null);
            List<book_needed> book_neededListList= new ArrayList<>();
            ListView listView = (ListView) activity.findViewById(R.id.book_needed_listview);

            //迭代游标
            while (cursor.moveToNext()) {

                String _book_name = cursor.getString(0);
                int book_number = cursor.getInt(1);
                Log.d("DB00", "book_name:" + _book_name + "\t number" + book_number);
                book_needed book_needed = new book_needed();
                book_needed.setBook_name(cursor.getString(0));
                book_needed.setBook_number(cursor.getInt(1));
                book_neededListList.add(book_needed);
            }
           // listView.setAdapter(new book_neededAdapter(book_neededListList,this));

            cursor.close();
            DB.close();

            return book_neededListList;
        }
        return null;
    }
}
