package com.example.librarymanagementsystem.Tools;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.widget.EditText;

import com.example.librarymanagementsystem.R;

public class book_info_insert {
    private static final String BOOK_INFO = "Book";
    public book_info_insert(Activity activity, SQLiteDatabase DB) {
        EditText book_name_et = (EditText) activity.findViewById(R.id.new_book_name_ed);
        EditText book_author_et = (EditText) activity.findViewById(R.id.new_book_author_ed);
        EditText book_price_et = (EditText) activity.findViewById(R.id.new_book_price_ed);
        EditText book_publisher_et = (EditText) activity.findViewById(R.id.new_book_publisher_ed);
        EditText book_brief_et = (EditText) activity.findViewById(R.id.new_book_brief_introduction_ed);
        EditText number_et = (EditText) activity.findViewById(R.id.new_book_number_ed);
        EditText location_et = (EditText) activity.findViewById(R.id.new_book_location_ed);

        String book_name = book_name_et.getText().toString();
        String book_author = book_author_et.getText().toString();
        String book_price = book_price_et.getText().toString();
        String book_publisher = book_publisher_et.getText().toString();
        String book_brief = book_brief_et.getText().toString();
        int number = Integer.parseInt(number_et.getText().toString());
        String location = location_et.getText().toString();

        if (DB.isOpen()) {
            String sql = "insert into " + BOOK_INFO + "(book_name,book_author,book_price," +
                    "book_publisher,book_brief_introduction,book_number_in_lib,book_number_in_total,book_location) values(" +
                    "'"+ book_name +
                    "','"+ book_author +
                    "','" +book_price +
                    "','"+ book_publisher +
                    "','" +book_brief +
                    "','" +number +
                    "','" +number +
                    "','" +location +"');";


            DB.execSQL(sql);
        }
    }
}
