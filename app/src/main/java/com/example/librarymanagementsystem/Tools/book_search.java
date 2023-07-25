package com.example.librarymanagementsystem.Tools;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.librarymanagementsystem.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class book_search {
    Context context1;
    String queryText;
    SQLiteDatabase DB;

    public book_search(String queryText,SQLiteDatabase DB,Context context1) {
        this.queryText = queryText;
        this.DB =DB;
        this.context1=context1;
    }
    public List<Book>book_search_to(){
       // String sql = "SELECT * FROM Book WHERE _book_id LIKE '%" + queryText  +"%' OR book_name LIKE'%" + queryText  +"%';";
        String sql = "SELECT * FROM Book WHERE _book_id LIKE '%" + queryText  +"%' OR book_name LIKE'%" + queryText  +
                    "%' OR book_author LIKE'%" + queryText + "';";
        Cursor cursor =DB.rawQuery(sql,null);//模糊匹配，返回bookList


        List<Book> bookList = new ArrayList<>();
        int FLAG = 0;
        while (cursor.moveToNext()){
            Book book = new Book();
            book.set_book_id(cursor.getInt(0));
            Log.d("DB0001","        "+cursor.getInt(0));
            book.setBook_name(cursor.getString(1));
            book.setBook_author(cursor.getString(2));
            book.setBook_price(cursor.getString(3));
            book.setBook_publisher(cursor.getString(4));
            book.setBook_brief_introduction(cursor.getString(5));
            book.setBook_number_in_lib(cursor.getInt(6));
            book.setBook_number_in_total(cursor.getInt(7));
            book.setBook_location(cursor.getString(8));
            bookList.add(book);
            FLAG=1;
            //Toast.makeText(context1, "查询完成", Toast.LENGTH_SHORT).show();

        }
        if(FLAG == 0){

            Toast.makeText(context1, "未查询到相关图书", Toast.LENGTH_SHORT).show();
        }
        return bookList;
    }
}
