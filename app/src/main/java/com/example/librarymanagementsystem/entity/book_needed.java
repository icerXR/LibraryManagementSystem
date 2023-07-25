package com.example.librarymanagementsystem.entity;

public class book_needed {

    String book_name;
    int book_number;


    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setBook_number(int book_number) {
        this.book_number = book_number;
    }



    public String getBook_name() {
        return book_name;
    }

    public int getBook_number() {
        return book_number;
    }


    public book_needed(){};
    public book_needed(String book_name, int book_number) {
        this.book_name = book_name;
        this.book_number = book_number;
    }

}
