package com.example.librarymanagementsystem.Tools;

import android.database.sqlite.SQLiteDatabase;

import com.example.librarymanagementsystem.entity.Book;

public class book_info_upudate {
    //Activity activity;
    SQLiteDatabase DB;
    Book book;

    public book_info_upudate( SQLiteDatabase DB,Book book) {
       // this.activity=activity;
        this.DB=DB;
        this.book=book;

    }
    public void book_info_upudate_to(){
        if(DB.isOpen()){
            String sql = "UPDATE Book SET "+
                    "book_name = '" + book.getBook_name() +"'" +
                    ",book_author = '" + book.getBook_author() +"'" +
                    ",book_price = '" + book.getBook_price() +"'" +
                    ",book_publisher = '" + book.getBook_publisher() +"'" +
                    ",book_brief_introduction = '" + book.getBook_brief_introduction() +"'" +
                    ",book_number_in_lib = '" + book.getBook_number_in_lib() +"'" +
                    ",book_number_in_total = '" + book.getBook_number_in_total() +"'" +
                    ",book_location = '" + book.getBook_location() +"' " +
                    "WHERE _book_id = "+book.get_book_id() +";";
            DB.execSQL(sql);

        }
    }
}
