package com.example.librarymanagementsystem.app;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.librarymanagementsystem.BaseActivity;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.R;

public class BookDetailsActivity extends BaseActivity {
    Book book;
    Context contextnow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);
        book =getIntent().getParcelableExtra("BOOK");
        TextView id ,name,author,price,publisher,brief,lib,total,loc;
        id =(TextView)findViewById(R.id.search_book_info_id_ed);
        name = (TextView)findViewById(R.id.search_book_info_name_ed);
        author = (TextView)findViewById(R.id.search_book_info_author_ed);
        price  = (TextView)findViewById(R.id.search_book_info_price_ed);
        publisher = (TextView)findViewById(R.id.search_book_info_publisher_ed);
        brief = (TextView)findViewById(R.id.search_book_info_brief_introduction_ed);
        lib = (TextView)findViewById(R.id.search_book_info_number_in_lib_ed);
        total  = (TextView)findViewById(R.id.search_book_info_number_in_total_ed);
        loc = (TextView)findViewById(R.id.search_book_info_location_ed);

        id.setText(book.get_book_id()+"");
        name.setText(book.getBook_name()+"");
        author.setText(book.getBook_author()+"");
        price.setText(book.getBook_price()+"");
        publisher.setText(book.getBook_publisher()+"");
        brief.setText(book.getBook_brief_introduction()+"");
        lib.setText(book.getBook_number_in_lib()+"");
        total.setText(book.getBook_number_in_total()+"");
        loc.setText(book.getBook_location()+"");
    }
}