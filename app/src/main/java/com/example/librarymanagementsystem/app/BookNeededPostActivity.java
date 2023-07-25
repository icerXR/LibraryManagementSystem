package com.example.librarymanagementsystem.app;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.librarymanagementsystem.BaseActivity;
import com.example.librarymanagementsystem.DB.myDBHelper;
import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.User.UserMainPlatform;

public class BookNeededPostActivity extends BaseActivity {
    Context context1 = this;

    private static final String BOOK_NEEDED_TABLE="Book_needed";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_needed_post);
        EditText ED1,ED2 ;
        ED1 = (EditText) findViewById(R.id.book_needed_post_name);
        ED2 = (EditText)findViewById(R.id.book_needed_post_number);

        Intent intent=getIntent();
        String account=intent.getStringExtra("account");

        Button button = (Button)findViewById(R.id.book_needed_post_Btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteOpenHelper helper = myDBHelper.getmInstance(context1);
                SQLiteDatabase DB = helper.getWritableDatabase();
                String name = ED1.getText().toString();
                int number = Integer.parseInt(ED2.getText().toString());
                String sql = "INSERT INTO Book_needed(_book_name,book_number) VALUES('"+name +"'," + number + ");";



                DB.execSQL(sql);
                DB.close();
                Toast.makeText(context1, "缺书提交成功", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(context1, UserMainPlatform.class);
                startActivity(intent1);
            }
        });




    }
}