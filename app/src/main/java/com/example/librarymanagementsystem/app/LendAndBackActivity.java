package com.example.librarymanagementsystem.app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.librarymanagementsystem.DB.myDBHelper;
import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.lrj.MySqliteOpenHelp;

public class LendAndBackActivity extends AppCompatActivity {
        Context context = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lend_and_back);

        Button lend_Btn,back_Btn;
        lend_Btn = (Button) findViewById(R.id.book_lend_Btn);
        back_Btn = (Button) findViewById(R.id.book_back_Btn);
        lend_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteOpenHelper helper = myDBHelper.getmInstance(context);
                SQLiteDatabase DB = helper.getWritableDatabase();
                /*打开李瑞杰的数据库*/
                SQLiteOpenHelper lrjhelper =  MySqliteOpenHelp.getInstance(context);
                SQLiteDatabase lrjdb = lrjhelper.getWritableDatabase();

                EditText account,name,date,author,book_no;//获取组件
                String account1,name1,date1,author1,book_no1;
                account = (EditText) findViewById(R.id.book_lend_account_et);
                name = (EditText) findViewById(R.id.book_lend_book_name_et);
                date=(EditText) findViewById(R.id.book_lend_date_et);
                author = (EditText) findViewById(R.id.book_lend_author_et);
                book_no = (EditText)findViewById(R.id.book_lend_id_et);

                account1 = account.getText().toString();//更新图书表
                name1 = name.getText().toString();
                date1 = date.getText().toString();
                author1 = author.getText().toString();
                book_no1 =book_no.getText().toString();
                 String sql_update = "UPDATE Book SET " +
                         "book_number_in_lib = book_number_in_lib -1 WHERE book_name ='"+ name1 +"';";//图书数量-1

                DB.execSQL(sql_update);
                DB.close();

                //更新User_books
                String sqlt_insert = "INSERT INTO User_books(user_account,bookName,bookAuthor,bookNumber,bookOverdue) VALUES(" +
                        "'"+ account1 +
                        "','"+ name1 +
                        "','"+  author1 +
                        "','"+  book_no1 +
                        "','" +date1 +"');";
                //String SQL = "INSERT INTO User_books(user_account,bookName,bookAuthor,bookNumber,bookOverdue) VALUES ('1','1','1','1','1');";
                lrjdb.execSQL(sqlt_insert);
                lrjdb.close();
                Toast.makeText(context, "借阅成功", Toast.LENGTH_SHORT).show();
            }
        });
        back_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteOpenHelper helper = myDBHelper.getmInstance(context);
                SQLiteDatabase DB = helper.getWritableDatabase();
                /*打开李瑞杰的数据库*/
                SQLiteOpenHelper lrjhelper =  MySqliteOpenHelp.getInstance(context);
                SQLiteDatabase lrjdb = lrjhelper.getWritableDatabase();

                EditText account,name,date,author,book_no;//获取组件
                String account1,name1,date1,author1,book_no1;
                account = (EditText) findViewById(R.id.book_lend_account_et);
                name = (EditText) findViewById(R.id.book_lend_book_name_et);
                date=(EditText) findViewById(R.id.book_lend_date_et);
                author = (EditText) findViewById(R.id.book_lend_author_et);
                book_no = (EditText)findViewById(R.id.book_lend_id_et);

                account1 = account.getText().toString();//更新图书表
                name1 = name.getText().toString();
                date1 = date.getText().toString();
                author1 = author.getText().toString();
                book_no1 =book_no.getText().toString();
                String sql_update = "UPDATE Book SET " +
                        "book_number_in_lib = book_number_in_lib +1 WHERE book_name ='"+ name1 +"';";//图书数量+1

                DB.execSQL(sql_update);
                DB.close();

                //更新Uｓｅｒ＿ｂｏｏｋｓ；
                String sqlt_delete = "DELETE FROM User_books WHERE bookName ='"+ name1 + "' AND user_account ='"+account1 +"';";
                //String SQL = "INSERT INTO User_books(user_account,bookName,bookAuthor,bookNumber,bookOverdue) VALUES ('1','1','1','1','1');";
                lrjdb.execSQL(sqlt_delete);
                lrjdb.close();
                Toast.makeText(context, "归还", Toast.LENGTH_SHORT).show();
            }
        });



    }
}