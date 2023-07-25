package com.example.librarymanagementsystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.librarymanagementsystem.entity.book_needed;
import com.example.librarymanagementsystem.R;

import java.util.List;

public class book_neededAdapter extends BaseAdapter {
    private List<book_needed> book_neededs;
    private Context context;

    public book_neededAdapter(List<book_needed> book_neededs, Context context) {
        this.book_neededs = book_neededs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return book_neededs.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.book_needed_item,parent,false);
        TextView textView1 = convertView.findViewById(R.id.book_needed_book_name_item);
        TextView textView2 = convertView.findViewById(R.id.book_needed_book_number_item);
        textView1.setText(book_neededs.get(position).getBook_name()+"");
        textView2.setText(book_neededs.get(position).getBook_number()+"");


        return convertView;
    }
}
