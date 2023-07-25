package com.example.librarymanagementsystem.lrj;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.librarymanagementsystem.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyAdapter extends BaseAdapter {
//listview的调试器

    Context context;
    ArrayList<HashMap<String,String>> data;

    public MyAdapter(Context context, ArrayList<HashMap<String, String>> data) {
        this.context = context;
        this.data = data;
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.list_item, null);
        TextView bookName = (TextView) convertView.findViewById(R.id.bookName);
        TextView bookAuthor = (TextView) convertView.findViewById(R.id.bookAuthor);
        TextView bookNumber = (TextView)  convertView.findViewById(R.id.bookNumber);
        TextView bookOverdue = (TextView) convertView.findViewById(R.id.bookOverdue);
        //Button btnRenew = (Button) convertView.findViewById(R.id.btnRenew);

        bookName.setText(data.get(position).get("bookName"));
        bookAuthor.setText(data.get(position).get("bookAuthor"));
        bookNumber.setText(data.get(position).get("bookNumber"));
        bookOverdue.setText(data.get(position).get("bookOverdue"));


        return convertView;
    }
}
