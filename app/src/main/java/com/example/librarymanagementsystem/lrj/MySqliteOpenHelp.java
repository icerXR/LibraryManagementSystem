package com.example.librarymanagementsystem.lrj;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySqliteOpenHelp extends SQLiteOpenHelper {

    private static MySqliteOpenHelp Instance;
    public static synchronized SQLiteOpenHelper getInstance(Context context){
        if(Instance==null){
            Instance = new MySqliteOpenHelp(context, "overdue.db", null, 1);
        }
        return Instance;
    }

    public MySqliteOpenHelp(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //数据库初始化 只执行一次
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table User_books(_id integer primary key autoincrement, user_account text, bookName text, bookAuthor text, bookNumber text, bookOverdue text)";

        db.execSQL(sql);
    }

    //数据库升级
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
