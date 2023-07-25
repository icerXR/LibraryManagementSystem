package com.example.librarymanagementsystem.app;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.librarymanagementsystem.Adapter.bookAdapter;
import com.example.librarymanagementsystem.BaseActivity;
import com.example.librarymanagementsystem.DB.myDBHelper;
import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.R;

import java.util.ArrayList;
import java.util.List;

public class BookUpdateActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_update);
    }

    public void search_by_id_or_name(View view) {
        SQLiteOpenHelper helper = myDBHelper.getmInstance(this);
        SQLiteDatabase DB = helper.getWritableDatabase();
        TextView queryText_et = (TextView) findViewById(R.id.book_update_search_et);
        String queryText =queryText_et.getText().toString();
        String sql = "SELECT * FROM Book WHERE _book_id LIKE '%" + queryText  +"%' OR book_name LIKE'%" + queryText  +"%';";
        Cursor cursor =DB.rawQuery(sql,null);//模糊匹配
        ListView listView = (ListView)findViewById(R.id.book_info_update_lv);

        List<Book> bookList = new ArrayList<>();//显示
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
        }
        if(FLAG == 0){
            Toast.makeText(this, "未查询到相关图书", Toast.LENGTH_SHORT).show();
        }
        listView.setAdapter(new bookAdapter(bookList,this));


        //更新修改后的数据
       listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               Intent intent = new Intent(BookUpdateActivity.this,BookInfoUpdateActivity.class);
               Book book = new Book();
               book = bookList.get(position);
               //Log.d("DB000001","ID" + book.get_book_id());
               intent.putExtra("BOOK",book);//传递到修改页面，进行修改
               startActivity(intent);
           }
       });

        cursor.close();
        DB.close();

    }
}