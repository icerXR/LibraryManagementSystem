package com.example.librarymanagementsystem.entity;

import java.sql.Time;

public class order {
    String book_name;
    int book_number;
    Time time;

    public order(String book_name, int book_number, Time time) {
        this.book_name = book_name;
        this.book_number = book_number;
        this.time = time;
    }
    public order(){}

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public int getBook_number() {
        return book_number;
    }

    public void setBook_number(int book_number) {
        this.book_number = book_number;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }
}
