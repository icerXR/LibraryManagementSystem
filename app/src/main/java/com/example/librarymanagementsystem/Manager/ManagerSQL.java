package com.example.librarymanagementsystem.Manager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class ManagerSQL extends SQLiteOpenHelper{
    private static String CREATE_TABLE_MANAGER="create table manager("+
            "managerid integer primary key autoincrement,"+
            "phone text,name text,account text,password text,registercode text,remembered integer)";
    private Context sContext;
    public ManagerSQL(Context context,String name,SQLiteDatabase.CursorFactory factory,int version){
        super(context, name, factory, version);
        sContext=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_MANAGER);//创建表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}
