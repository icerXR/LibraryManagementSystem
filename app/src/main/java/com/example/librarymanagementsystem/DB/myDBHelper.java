package com.example.librarymanagementsystem.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class myDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME="lab_manage_system.db";
    private static final int DB_VERSION=1;
    private static final String USER_TABLE="User";
    private static final String BOOK_NEEDED_TABLE="Book_needed";
    private static final String ORDER_TABLE="Order_List";
    private static final String BOOK_INFO = "Book";

    //2.对外提供函数
    private static SQLiteOpenHelper mInstance;
    public static synchronized SQLiteOpenHelper getmInstance(Context context){
        if(mInstance==null){
            mInstance = new myDBHelper(context,DB_NAME,null,DB_VERSION);

        }
        return mInstance;
    }

    //1.私有化
    private myDBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        String create_Book_needed =
                             "CREATE TABLE IF NOT EXISTS " + BOOK_NEEDED_TABLE + "(" +
                             "_book_name TEXT PRIMARY KEY," +
                             " book_number INTEGER" +
                             ");";
        String create_Order  ="CREATE TABLE IF NOT EXISTS " + ORDER_TABLE + "(" +
                             "_book_name TEXT," +
                             " _time TEXT," +
                             " book_number INTEFER," +
                             "PRIMARY KEY (_book_name,_time)"+
                             ");";
        String create_Book = "CREATE TABLE IF NOT EXISTS " + BOOK_INFO + "(" +
                             "_book_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                             " book_name TEXT,"+
                             " book_author TEXT," +
                             " book_price TEXT," +
                             " book_publisher TEXT," +
                             " book_brief_introduction TEXT," +
                             " book_number_in_lib INTEGER," +
                             " book_number_in_total INTEGER," +
                             " book_location TEXT" +
                             ");";
        String create_Order_Receive  ="CREATE TABLE IF NOT EXISTS Order_Receive"  + "(" +
                            "_book_name TEXT," +
                            " _time TEXT," +
                            " book_number INTEFER," +
                            "PRIMARY KEY (_book_name,_time)"+
                            ");";
        String create_Order_Over  ="CREATE TABLE IF NOT EXISTS Order_Over"  + "(" +
                            "_book_name TEXT," +
                            " _time TEXT," +
                            " book_number INTEFER," +
                            "PRIMARY KEY (_book_name,_time)"+
                            ");";
        String create_Order_History  ="CREATE TABLE IF NOT EXISTS Order_History"  + "(" +
                            "_book_name TEXT," +
                            " _time TEXT," +
                            " book_number INTEFER," +
                            "PRIMARY KEY (_book_name,_time)"+
                            ");";
        String create_User_books = "create table User_books(_id integer primary key autoincrement, bookName text, bookAuthor text, bookNumber text, bookOverdue, text)";

        DB.execSQL(create_Book_needed);
        DB.execSQL(create_Order);
        DB.execSQL(create_Book);
        DB.execSQL(create_Order_Receive);
        DB.execSQL(create_Order_Over);
        DB.execSQL(create_Order_History);
        DB.execSQL(create_User_books);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
