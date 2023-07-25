package com.example.librarymanagementsystem.lrj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;

import com.example.librarymanagementsystem.R;

import java.util.ArrayList;
import java.util.HashMap;

public class Librarian_lendbook extends AppCompatActivity {
    //intentget 传入 account；
    private ArrayList<HashMap<String,String>> data = new ArrayList<>();
    private  ListView listView;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminitor_lendbook);
        ListView listView = (ListView) findViewById(R.id.lv);

        MyAdapter myAdapter = new MyAdapter(this,data);
        listView.setAdapter(myAdapter);

    }
}