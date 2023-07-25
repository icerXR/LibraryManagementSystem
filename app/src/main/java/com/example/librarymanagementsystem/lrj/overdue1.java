package com.example.librarymanagementsystem.lrj;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.librarymanagementsystem.BaseActivity;
import com.example.librarymanagementsystem.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class overdue1 extends BaseActivity {
    private ArrayList<HashMap<String,String>> data = new ArrayList<>();
    private  ListView listView;
    Context context = this;
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent intent=getIntent();
        String account = intent.getStringExtra("account");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overdue1);

        SQLiteOpenHelper helper =  MySqliteOpenHelp.getInstance(context);
        SQLiteDatabase db = helper.getReadableDatabase();

        ListView listView = (ListView) findViewById(R.id.lv);
        if(db.isOpen()) {
            //返回游标
            Cursor cursor = db.rawQuery("select * from User_books where user_account = '"+account+"';" , null);
            //迭代游标
            while (cursor.moveToNext()) {
                String bookName = cursor.getString(cursor.getColumnIndex("bookName"));
                String bookAuthor = cursor.getString(cursor.getColumnIndex("bookAuthor"));
                String bookNumber = cursor.getString(cursor.getColumnIndex("bookNumber"));
                String bookOverdue = cursor.getString(cursor.getColumnIndex("bookOverdue"));

                data.add(DataControl.getBook(bookName, bookAuthor, bookNumber, bookOverdue));
            }

            cursor.close();
            db.close();
        }
        else {

        }

        MyAdapter myAdapter = new MyAdapter(this,data);
        listView.setAdapter(myAdapter);

    }

}
