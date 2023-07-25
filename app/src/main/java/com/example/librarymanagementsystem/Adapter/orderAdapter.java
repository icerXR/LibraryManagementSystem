package com.example.librarymanagementsystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.librarymanagementsystem.entity.order;
import com.example.librarymanagementsystem.R;

import java.util.List;

public class orderAdapter extends BaseAdapter {
    List<order> orders;
    Context context;

    public orderAdapter(List<order> orders, Context context) {
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
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView= LayoutInflater.from(context).inflate(R.layout.book_needed_item,parent,false);
        TextView name,number;
        name=convertView.findViewById(R.id.book_needed_book_name_item);
        number=convertView.findViewById(R.id.book_needed_book_number_item);
        name.setText(orders.get(position).getBook_name()+"");
        number.setText(orders.get(position).getBook_number()+"");
        return convertView;
    }
}
