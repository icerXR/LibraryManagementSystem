package com.example.librarymanagementsystem.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.librarymanagementsystem.entity.Book;
import com.example.librarymanagementsystem.R;

import java.util.List;

public class bookAdapter extends BaseAdapter {
    List<Book> books;
    Context context;

    public bookAdapter(List<Book> books, Context context) {
        this.books = books;
        this.context = context;
    }

    @Override
    public int getCount() {
        return books.size();
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
        convertView = LayoutInflater.from(context).inflate(R.layout.book_item,parent,false);
        TextView id ,name,author,price,publisher,brief,lib,total,loc;
        id =convertView.findViewById(R.id.update_book_id_ed);
        name = convertView.findViewById(R.id.update_book_name_ed);
       /* author= convertView.findViewById(R.id.update_book_author_ed);
        price= convertView.findViewById(R.id.update_book_price_ed);
        publisher= convertView.findViewById(R.id.update_book_publisher_ed);
        brief= convertView.findViewById(R.id.update_book_brief_introduction_ed);
        lib= convertView.findViewById(R.id.update_book_number_in_lib_ed);
        total= convertView.findViewById(R.id.update_book_number_in_total_ed);
        loc= convertView.findViewById(R.id.update_book_location_ed);*/

        id.setText(books.get(position).get_book_id()+"");
        name.setText(books.get(position).getBook_name()+"");
       /* author.setText(books.get(position).getBook_author()+"");
        price.setText(books.get(position).getBook_price()+"");
        publisher.setText(books.get(position).getBook_publisher()+"");
        brief.setText(books.get(position).getBook_brief_introduction()+"");
        lib.setText(books.get(position).getBook_number_in_lib()+"");
        total.setText(books.get(position).getBook_number_in_total()+"");
        loc.setText(books.get(position).getBook_location()+"");*/

        return convertView;
    }
}
