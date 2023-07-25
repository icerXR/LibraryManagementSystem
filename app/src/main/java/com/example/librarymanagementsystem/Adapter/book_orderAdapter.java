package com.example.librarymanagementsystem.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.librarymanagementsystem.entity.order;
import com.example.librarymanagementsystem.R;

import java.util.List;

public class book_orderAdapter extends BaseAdapter {
    private List<order> orders;
    private Context context;

    public book_orderAdapter(List<order> orders, Context context) {
        this.orders = orders;
        this.context = context;
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.book_needed_item,parent,false);
        TextView textView1 = convertView.findViewById(R.id.book_needed_book_name_item);
        TextView textView2 = convertView.findViewById(R.id.book_needed_book_number_item);
        textView1.setText(orders.get(position).getBook_name().toString()+"");
        Log.d("DB000","NAME"+ orders.get(position).getBook_name().toString());
        textView2.setText(orders.get(position).getBook_number()+"");

        return convertView;
    }
}
