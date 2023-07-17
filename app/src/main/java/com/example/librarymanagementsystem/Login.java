package com.example.librarymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    private EditText etAccount;
    private EditText etPwd;
    private CheckBox cbRemember;
    private Button btLogin;
    private Button btRegister;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private UserMySQL userMySQL;
    private ManagerSQL managerSQL;
    public SQLiteDatabase userDb;
    public SQLiteDatabase managerDb;
    @SuppressLint({"Range", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etAccount=(EditText) findViewById(R.id.etAccount);
        etPwd=(EditText) findViewById(R.id.etPwd);
        cbRemember=(CheckBox) findViewById(R.id.cbRemember);
        Intent intent=getIntent();
        String account=intent.getStringExtra("account1");
        etAccount.setText(account);
        //应用启动后，创建管理员信息表和用户信息表
        managerSQL=new ManagerSQL(Login.this,"manager.db",null,1);
        managerDb=managerSQL.getWritableDatabase();

        userMySQL = new UserMySQL(Login.this,"userdb.db",null,1);
        userDb=userMySQL.getWritableDatabase();
        //通过共享内存，设置“记住密码”复选框
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        editor=pref.edit();
        boolean isRemembered=pref.getBoolean("remembered",false);
        if(isRemembered){
            Cursor c=userDb.rawQuery("select * from users where remembered=1",null);
            if(c.moveToFirst()){
                etAccount.setText(c.getString(c.getColumnIndex("account")));
                etPwd.setText(c.getString(c.getColumnIndex("password")));
                cbRemember.setChecked(true);
            }
        }
        //点击登录按钮时，根据账号密码验证结果和复选框是否点选返回相应结果
        btLogin=(Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(CheckLog()){
                    if(cbRemember.isChecked()){
                        String account=etAccount.getText().toString();
                        userDb.execSQL("update users set remembered=1 where account=?",new String[]{account});
                        userDb.execSQL("update users set remembered=0 where account<>?",new String[]{account});
                        editor.putBoolean("remembered",true);
                    }else{
                        userDb.execSQL("update users set remembered=0");
                        editor.clear();
                    }
                    editor.apply();
                    Toast.makeText(Login.this,"登录成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Login.this,"账号或密码错误",Toast.LENGTH_SHORT).show();
                }
            }
        });
        //注册跳转
        btRegister=(Button) findViewById(R.id.btRegister);
        btRegister.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Register.class));
            }
        }));
    }
    //验证账号密码是否相符
    private boolean CheckLog(){
        String account=etAccount.getText().toString();
        String pwd=etPwd.getText().toString();
        //对用户和管理员账号密码分别验证
        if(account.length()==10){
            Cursor c=userDb.query("users",new String[]{"account"},"account=? and password=?",new String[]{account,pwd},null,null,null);
            if(c.moveToFirst()){
                return true;
            }else{
                return false;
            }
        }else{
            Cursor c=managerDb.query("manager",new String[]{"account"},"account=? and password=?",new String[]{account,pwd},null,null,null);
            if(c.moveToFirst()){
                return true;
            }else{
                return false;
            }
        }
    }
}