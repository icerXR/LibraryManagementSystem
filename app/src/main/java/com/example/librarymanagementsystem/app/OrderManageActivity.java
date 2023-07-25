package com.example.librarymanagementsystem.app;

import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.librarymanagementsystem.Adapter.orderAdapter;
import com.example.librarymanagementsystem.BaseActivity;
import com.example.librarymanagementsystem.DB.myDBHelper;
import com.example.librarymanagementsystem.Tools.DataTransport;
import com.example.librarymanagementsystem.Tools.order_query;
import com.example.librarymanagementsystem.R;

public class OrderManageActivity extends BaseActivity {
    Context context =this;
    Activity activity = this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_manage);

        Button button1 = (Button) findViewById(R.id.order_manage_activity_send_Btn);
        Button button2 = (Button) findViewById(R.id.order_manage_activity_sent_Btn);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteOpenHelper helper = myDBHelper.getmInstance(context);
                SQLiteDatabase DB = helper.getReadableDatabase();
                ListView listView = (ListView) findViewById(R.id.order_manage_activity_send_listview);
                listView.setAdapter(new orderAdapter(new order_query(activity,DB,"Order_Over").order_query_to(),context));
                //调用order_query工具类进行查询及显示
                DB.close();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteOpenHelper helper = myDBHelper.getmInstance(context);
                SQLiteDatabase DB = helper.getReadableDatabase();
                DataTransport dataTransport = new DataTransport(DB,"Order_Over","Order_History");
                dataTransport.data_tansport();//将数据转移到Order_History表中以完成订单并留存历史数据
                Toast.makeText(context, "订单已完成", Toast.LENGTH_SHORT).show();
            }
        });
    }
}