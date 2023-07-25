package com.example.librarymanagementsystem.lrj;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.librarymanagementsystem.R;

public class testdb extends AppCompatActivity {
//数据库练习用

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overdue1);
    }

    public void creatDB(View view) {

        SQLiteOpenHelper helper = MySqliteOpenHelp.getInstance(this);
        SQLiteDatabase readableDatabase = helper.getReadableDatabase();
    }

    public void query(View view) {
        SQLiteOpenHelper helper = MySqliteOpenHelp.getInstance(this);
        SQLiteDatabase db = helper.getReadableDatabase();

        if(db.isOpen()){
            //返回游标
            Cursor cursor = db.rawQuery("select * from books",null);
            //迭代游标
            while (cursor.moveToNext()){
                cursor.getString(1);
                int _id = cursor.getInt(cursor.getColumnIndex("_id"));
                String bookName = cursor.getString(cursor.getColumnIndex("bookName"));
                String bookAuthor = cursor.getString(cursor.getColumnIndex("bookAuthor"));
                String bookNumber = cursor.getString(cursor.getColumnIndex("bookNumber"));
                String bookOverdue = cursor.getString(cursor.getColumnIndex("bookOverdue"));

                Log.d("overdue", "query: _id"+ _id + " name:" + bookName);
            }

            cursor.close();
            db.close();
        }

    }

    public void insert(View view) {
        SQLiteOpenHelper helper = MySqliteOpenHelp.getInstance(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        if(db.isOpen()){
            db.execSQL("insert into books(bookName) values('如来')");
            db.execSQL("insert into books(bookAuthor) values('郭继承')");
            db.execSQL("insert into books(bookNumber) values('1')");
            db.execSQL("insert into books(bookOverdue) values('2023年7月31日')");
        }
        db.close();
    }

    public void update(View view) {
        SQLiteOpenHelper helper = MySqliteOpenHelp.getInstance(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        if(db.isOpen()){
            String sql = "update  persons set name =? where _id = ?";
            db.execSQL(sql, new Object[]{"李连杰", 5});
        }
        db.close();
    }

    public void delete(View view) {
        SQLiteOpenHelper helper = MySqliteOpenHelp.getInstance(this);
        SQLiteDatabase db = helper.getWritableDatabase();

        if(db.isOpen()){
            String sql = "delete from persons where _id =?";
            db.execSQL(sql, new Object[]{4});
        }
        db.close();
    }

}
