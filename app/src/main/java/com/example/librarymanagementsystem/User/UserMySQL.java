package com.example.librarymanagementsystem.User;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class UserMySQL extends SQLiteOpenHelper{
    private static String CREATE_TABLE_USER="create table users("+
            "userid integer primary key autoincrement,"+
            "phone text,name text,couldborrowbooks text,borrowing text,account text,password text,remembered integer)";
    private Context sContext;
    public UserMySQL(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context, name, factory, version);
        sContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_USER);//创建表
        db.execSQL("insert into users(phone,name,couldborrowbooks,borrowing,account,password,remembered) values(?,?,?,?,?,?,0)",
                new String[]{"15141430168","Alan","3","0","0716016801","123456"});
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
