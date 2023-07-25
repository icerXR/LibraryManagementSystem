package com.example.librarymanagementsystem.anotherAPP;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.librarymanagementsystem.Adapter.orderAdapter;
import com.example.librarymanagementsystem.DB.myDBHelper;
import com.example.librarymanagementsystem.Tools.DataTransport;
import com.example.librarymanagementsystem.Tools.order_query;

import com.example.librarymanagementsystem.R;

public class AnotherApp extends AppCompatActivity {
    Context context =this;
    Activity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another_app);
        Button button1 =(Button) findViewById(R.id.another_order_visit_Btn);
        Button button2 = (Button) findViewById(R.id.another_order_over_Btn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteOpenHelper helper = myDBHelper.getmInstance(context);
                SQLiteDatabase DB = helper.getReadableDatabase();
                ListView listView = (ListView) findViewById(R.id.another_order_listview);
                listView.setAdapter(new orderAdapter(new order_query(activity,DB,"Order_Receive").order_query_to(),context));
                //调用order_query工具类进行查询及显示
                DB.close();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteOpenHelper helper = myDBHelper.getmInstance(context);
                SQLiteDatabase DB = helper.getReadableDatabase();
                DataTransport dataTransport = new DataTransport(DB,"Order_Receive","Order_Over");
                dataTransport.data_tansport();
            }
        });
    }
}