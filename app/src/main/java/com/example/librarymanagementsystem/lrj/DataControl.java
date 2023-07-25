package com.example.librarymanagementsystem.lrj;

import java.util.HashMap;

public class DataControl {
//控制listview的数据
    public static HashMap<String,String> getBook(String bookName,String bookAuthor,String bookNumber,String bookOverdue){
        HashMap<String,String> map = new HashMap<>();
        map.put("bookName",bookName);
        map.put("bookAuthor",bookAuthor);
        map.put("bookNumber",bookNumber);
        map.put("bookOverdue",bookOverdue);
        return map;
    }
}
