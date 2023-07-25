package com.example.librarymanagementsystem.app;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.librarymanagementsystem.Adapter.book_neededAdapter;
import com.example.librarymanagementsystem.Adapter.book_orderAdapter;
import com.example.librarymanagementsystem.DB.myDBHelper;
import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.Tools.*;
import com.example.librarymanagementsystem.entity.order;
import com.example.librarymanagementsystem.BaseActivity;

import java.util.ArrayList;
import java.util.List;


public class BOOK_NEEDED extends BaseActivity {

    private static final String DB_NAME="lab_manage_system.db";
    private static final int DB_VERSION=1;
    private static final String USER_TABLE="User";
    private static final String BOOK_NEEDED_TABLE="Book_needed";
    private static final String ORDER_TABLE="Order_List";
    List<order> orders = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_needeed);
        String book_name_order_edit = findViewById(R.id.book_order_name_edit).toString();

    }
    public void create_DB(View view){
        SQLiteOpenHelper helper = myDBHelper.getmInstance(this);
        helper.getReadableDatabase();

    }


    public void insert_DB(View view) {
        SQLiteOpenHelper helper = myDBHelper.getmInstance(this);
        SQLiteDatabase DB = helper.getWritableDatabase();
        //插入语句,
        if (DB.isOpen()) {
            for(int i = 2;i<=20;i++){
           // String sql = "insert into " + BOOK_NEEDED_TABLE + "(_book_name) values('天工开物');";
                String name = "天工开物" + i;
            String sql = "insert into " + BOOK_NEEDED_TABLE + "(_book_name) values('"+ name +"');";

            DB.execSQL(sql);
            }
        }
        DB.close();
    }//调试用

    public void visit_DB(View view) {
        SQLiteOpenHelper helper = myDBHelper.getmInstance(this);
        SQLiteDatabase DB = helper.getReadableDatabase();
        ListView listView = (ListView) findViewById(R.id.book_needed_listview);

        listView.setAdapter(new book_neededAdapter(new book_needed_query(this,DB).book_needed_query_to(),this));
        //调用工具类直接执行对数据库操作

    }

    public void order_edit(View view) {
        EditText book_order_name_edit=findViewById(R.id.book_order_name_edit);//获取控件
        EditText book_order_number_edit=findViewById(R.id.book_order_number_edit);
        if(book_order_name_edit.getText().toString().isEmpty() || book_order_number_edit.getText().toString().isEmpty()){
            Toast.makeText(this, "图书名称或数量不可为空", Toast.LENGTH_SHORT).show();
        }
        else{
            order order = new order();//建立order对象的实例
            TextView textView1 = (TextView)findViewById(R.id.book_order_name_edit) ;
            TextView textView2 = (TextView)findViewById(R.id.book_order_number_edit);

            int i =Integer.parseInt(textView2.getText().toString());
            order.setBook_name(textView1.getText().toString());
            order.setBook_number(i);

            orders.add(order);//添加到List<order>中。但没有写入数据库


        }

    }

    public void order_generate(View view) {
        //显示数据内容
        SQLiteOpenHelper helper = myDBHelper.getmInstance(this);
        SQLiteDatabase DB = helper.getWritableDatabase();
        ListView lv = (ListView) findViewById(R.id.book_order_listview);
        lv.setAdapter(new book_orderAdapter(orders,this));
        //将数据内容插入到数据库
        for(int i = 0;i<orders.size();i++){
           String name= orders.get(i).getBook_name();
            int number =orders.get(i).getBook_number();
            Log.d("DB000","name number"+ name + number);
            String sql = "insert into " + ORDER_TABLE + "(_book_name,book_number) values('"+ name + "'," + number + ");";
            DB.execSQL(sql);
        }
        DB.close();

    }
}