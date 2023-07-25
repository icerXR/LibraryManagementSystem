package com.example.librarymanagementsystem.app;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.librarymanagementsystem.BaseActivity;
import com.example.librarymanagementsystem.DB.myDBHelper;
import com.example.librarymanagementsystem.Manager.ManagerMainPlatform;
import com.example.librarymanagementsystem.Tools.book_info_upudate;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.R;

public class BookInfoUpdateActivity extends BaseActivity {
    Book book;
    Context contextnow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_info_update);
        book =getIntent().getParcelableExtra("BOOK");
        TextView id ,name,author,price,publisher,brief,lib,total,loc;
        id =(TextView)findViewById(R.id.update_book_info_id_ed);
        name = (TextView)findViewById(R.id.update_book_info_name_ed);
        author = (TextView)findViewById(R.id.update_book_info_author_ed);
        price  = (TextView)findViewById(R.id.update_book_info_price_ed);
        publisher = (TextView)findViewById(R.id.update_book_info_publisher_ed);
        brief = (TextView)findViewById(R.id.update_book_info_brief_introduction_ed);
        lib = (TextView)findViewById(R.id.update_book_info_number_in_lib_ed);
        total  = (TextView)findViewById(R.id.update_book_info_number_in_total_ed);
        loc = (TextView)findViewById(R.id.update_book_info_location_ed);

        id.setText(book.get_book_id()+"");
        name.setText(book.getBook_name()+"");
        author.setText(book.getBook_author()+"");
        price.setText(book.getBook_price()+"");
        publisher.setText(book.getBook_publisher()+"");
        brief.setText(book.getBook_brief_introduction()+"");
        lib.setText(book.getBook_number_in_lib()+"");
        total.setText(book.getBook_number_in_total()+"");
        loc.setText(book.getBook_location()+"");
        Button button = (Button) findViewById(R.id.update_book_info_Btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                book.setBook_name(name.getText().toString());
                book.setBook_author(author.getText().toString());
                book.setBook_price(price.getText().toString());
                book.setBook_publisher(publisher.getText().toString());
                book.setBook_brief_introduction(brief.getText().toString());
                book.setBook_number_in_lib(Integer.parseInt(lib.getText().toString()));
                book.setBook_number_in_total(Integer.parseInt(total.getText().toString()));
                book.setBook_location(loc.getText().toString());
                SQLiteOpenHelper helper = myDBHelper.getmInstance(contextnow);
                SQLiteDatabase DB = helper.getReadableDatabase();
               book_info_upudate book_info_upudate = new book_info_upudate(DB,book);
               book_info_upudate.book_info_upudate_to();//调用工具，执行修改
               // Toast.makeText(context, "修改成功", Toast.LENGTH_SHORT).show();
                Toast.makeText(BookInfoUpdateActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BookInfoUpdateActivity.this, ManagerMainPlatform.class));
            }
        });
    }






}