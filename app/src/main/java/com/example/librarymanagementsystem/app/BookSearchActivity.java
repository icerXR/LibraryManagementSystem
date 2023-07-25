package com.example.librarymanagementsystem.app;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.librarymanagementsystem.Adapter.bookAdapter;
import com.example.librarymanagementsystem.BaseActivity;
import com.example.librarymanagementsystem.DB.myDBHelper;
import com.example.librarymanagementsystem.Tools.book_search;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.R;

import java.util.List;

public class BookSearchActivity extends BaseActivity {

    Context context1=this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_search);

        Intent intent=getIntent();
        String account=intent.getStringExtra("account");

        Button button =(Button) findViewById(R.id.book_search_search_Btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteOpenHelper helper = myDBHelper.getmInstance(context1);
                SQLiteDatabase DB = helper.getWritableDatabase();
                TextView queryText_et = (TextView) findViewById(R.id.book_search_search_et);
                String queryText =queryText_et.getText().toString();//获取各种组件


                ListView listView = (ListView)findViewById(R.id.book_info_search_lv);
                List<Book> bookList =new book_search(queryText,DB,context1).book_search_to();
                listView.setAdapter(new bookAdapter(bookList,context1));//调用book_search;
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        Intent intent = new Intent(BookSearchActivity.this,BookDetailsActivity.class);
                        Book book = new Book();
                        book = bookList.get(position);
                        //Log.d("DB000001","ID" + book.get_book_id());
                        intent.putExtra("BOOK",book);//传递到书籍详情页面，进行查看
                        startActivity(intent);
                    }
                });
            }
        });
    }



}