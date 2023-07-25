package com.example.librarymanagementsystem.User;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.librarymanagementsystem.BaseActivity;
import com.example.librarymanagementsystem.R;

public class UserInfoFix extends BaseActivity {
    private UserMySQL userMySQL;
    public SQLiteDatabase userDb;
    private EditText etName;
    private EditText etPhone;
    private EditText etPwd;
    private EditText etPwdAgain;
    private Button btUserSummit;
    private Button btUserBack;
    @SuppressLint({"Range", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_fix);

        etName=findViewById(R.id.etName);
        etPhone=findViewById(R.id.etPhone);
        etPwd=findViewById(R.id.etPwd);
        etPwdAgain=findViewById(R.id.etPwdAgain);

        Intent intent=getIntent();
        String account=intent.getStringExtra("account");
        //在表中找到该用户
        userMySQL = new UserMySQL(UserInfoFix.this,"userdb.db",null,1);
        userDb=userMySQL.getReadableDatabase();
        String sql="select * from users where account=?";
        Cursor c=userDb.rawQuery(sql,new String[]{account});
        //将用户信息预填入EditText中
        if(c.moveToFirst()){
            etName.setText(c.getString(c.getColumnIndex("name")));
            etPhone.setText(c.getString(c.getColumnIndex("phone")));
            etPwd.setText(c.getString(c.getColumnIndex("password")));
            etPwdAgain.setText(c.getString(c.getColumnIndex("password")));
        }
        //点击提交时，将新数据写入表中
        btUserSummit=findViewById(R.id.btUserSummit);
        btUserSummit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name=etName.getText().toString();
                String Phone=etPhone.getText().toString();
                String Pwd=etPwd.getText().toString();
                String PwdAgain=etPwdAgain.getText().toString();

                ContentValues cv=new ContentValues();
                cv.put("name",Name);
                userDb.update("users",cv,"account=?",new String[]{account});
                cv.put("phone",Phone);
                userDb.update("users",cv,"account=?",new String[]{account});
                if(Pwd.length()>0&&Pwd.equals(PwdAgain)){
                    cv.put("password",Pwd);
                    userDb.update("users",cv,"account=?",new String[]{account});
                }
                Toast.makeText(UserInfoFix.this, "修改完成", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(UserInfoFix.this,UserInfoPlatform.class);
                intent1.putExtra("account",account);
                startActivity(intent1);
            }
        });

        btUserBack=findViewById(R.id.btUserBack);
        btUserBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserInfoFix.this,UserInfoPlatform.class));
            }
        });
    }
}