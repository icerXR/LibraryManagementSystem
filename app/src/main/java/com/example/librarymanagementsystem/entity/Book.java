package com.example.librarymanagementsystem.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Book implements Parcelable {
    int _book_id;
    String book_name;
    String book_author;
    String book_price;
    String book_publisher;
    String book_brief_introduction;
    int book_number_in_lib;
    int book_number_in_total;
    String book_location;

    public Book() {
    }

    protected Book(Parcel in) {
        _book_id = in.readInt();
        book_name = in.readString();
        book_author = in.readString();
        book_price = in.readString();
        book_publisher = in.readString();
        book_brief_introduction = in.readString();
        book_number_in_lib = in.readInt();
        book_number_in_total = in.readInt();
        book_location = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public int get_book_id() {
        return _book_id;
    }

    public void set_book_id(int _book_id) {
        this._book_id = _book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getBook_author() {
        return book_author;
    }

    public void setBook_author(String book_author) {
        this.book_author = book_author;
    }

    public String getBook_price() {
        return book_price;
    }

    public void setBook_price(String book_price) {
        this.book_price = book_price;
    }

    public String getBook_publisher() {
        return book_publisher;
    }

    public void setBook_publisher(String book_publisher) {
        this.book_publisher = book_publisher;
    }

    public String getBook_brief_introduction() {
        return book_brief_introduction;
    }

    public void setBook_brief_introduction(String book_brief_introduction) {
        this.book_brief_introduction = book_brief_introduction;
    }

    public int getBook_number_in_lib() {
        return book_number_in_lib;
    }

    public void setBook_number_in_lib(int book_number_in_lib) {
        this.book_number_in_lib = book_number_in_lib;
    }

    public int getBook_number_in_total() {
        return book_number_in_total;
    }

    public void setBook_number_in_total(int book_number_in_total) {
        this.book_number_in_total = book_number_in_total;
    }

    public String getBook_location() {
        return book_location;
    }

    public void setBook_location(String book_location) {
        this.book_location = book_location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(_book_id);
        parcel.writeString(book_name);
        parcel.writeString(book_author);
        parcel.writeString(book_price);
        parcel.writeString(book_publisher);
        parcel.writeString(book_brief_introduction);
        parcel.writeInt(book_number_in_lib);
        parcel.writeInt(book_number_in_total);
        parcel.writeString(book_location);
    }
}
