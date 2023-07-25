package com.example.librarymanagementsystem.app;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.librarymanagementsystem.BaseActivity;
import com.example.librarymanagementsystem.DB.myDBHelper;
import com.example.librarymanagementsystem.Tools.book_info_insert;
import com.example.librarymanagementsystem.R;

public class NewBookActivity extends BaseActivity {
    private static final String BOOK_INFO = "Book";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_book);
    }

    public int new_book_input(View view) {

        SQLiteOpenHelper helper = myDBHelper.getmInstance(this);
        SQLiteDatabase DB = helper.getWritableDatabase();
        new book_info_insert(this,DB);
        //单独使用了工具类实现
        DB.close();
        Toast.makeText(this, "录入成功", Toast.LENGTH_SHORT).show();
        return  0;
    }

}