package com.example.librarymanagementsystem.LoginAndRegister;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.librarymanagementsystem.ActivityManager;
import com.example.librarymanagementsystem.BaseActivity;
import com.example.librarymanagementsystem.Manager.FirstManagerMainPlatform;
import com.example.librarymanagementsystem.Manager.ManagerMainPlatform;
import com.example.librarymanagementsystem.Manager.ManagerSQL;
import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.User.UserMainPlatform;
import com.example.librarymanagementsystem.User.UserMySQL;
import com.example.librarymanagementsystem.lrj.MySqliteOpenHelp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Login extends BaseActivity {
    Context context = this;
    public int channel_id = 0;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private EditText etAccount;
    private EditText etPwd;
    private CheckBox cbRemember;
    private Button btLogin;
    private Button btRegister;
    private Button btFinish;
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
        //接收注册界面的账号信息
        Intent intent=getIntent();
        String accountRegister=intent.getStringExtra("account1");

        //应用启动后，创建管理员信息表和用户信息表
        managerSQL=new ManagerSQL(Login.this,"manager.db",null,1);
        managerDb=managerSQL.getWritableDatabase();

        userMySQL = new UserMySQL(Login.this,"userdb.db",null,1);
        userDb=userMySQL.getWritableDatabase();
        //通过共享内存，判断是否记住密码来初始化登录界面
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        editor=pref.edit();
        boolean isRemembered=pref.getBoolean("remembered",false);
        if(isRemembered){
            //执行查询获得当前记住密码的用户信息
            Cursor c=userDb.rawQuery("select * from users where remembered=1",null);
            if(c.moveToFirst()){
                //用查询结果初始化登录界面
                etAccount.setText(c.getString(c.getColumnIndex("account")));
                etPwd.setText(c.getString(c.getColumnIndex("password")));
                cbRemember.setChecked(true);
            }
            Cursor c2=managerDb.rawQuery("select * from manager where remembered=1",null);
            if(c2.moveToFirst()){
                //用查询结果初始化登录界面
                etAccount.setText(c2.getString(c2.getColumnIndex("account")));
                etPwd.setText(c2.getString(c2.getColumnIndex("password")));
                cbRemember.setChecked(true);
            }
        }
        //如果执行了注册操作，清空共享内存，将登录界面初始化，将注册界面中注册完毕的账号填入登录界面的对应位置
        if(accountRegister!=null){
            editor.clear();
            etAccount.setText(accountRegister);
            etPwd.setText(null);
            cbRemember.setChecked(false);
        }
        //点击登录按钮时，根据账号密码验证结果和复选框是否点选返回相应结果
        btLogin=(Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(CheckLog()) {
                    String account= etAccount.getText().toString();
                    if(account != null && account.length() == 10){
                        //若点选复选框，则将该用户在表中的remembered数据改为1，其他改为0
                        if (cbRemember.isChecked()) {
                            userDb.execSQL("update users set remembered=1 where account=?", new String[]{account});
                            userDb.execSQL("update users set remembered=0 where account<>?", new String[]{account});
                            managerDb.execSQL("update manager set remembered=0 where account<>?", new String[]{account});
                            editor.putBoolean("remembered", true);

                        } else {
                            userDb.execSQL("update users set remembered=0");
                            editor.clear();
                        }
                        Intent intent1=new Intent(Login.this, UserMainPlatform.class);
                        intent1.putExtra("account",account);
                        startActivity(intent1);
                    }else {
                        //若点选复选框，则将该管理员在表中的remembered数据改为1，其他改为0
                        if (cbRemember.isChecked()) {
                            managerDb.execSQL("update manager set remembered=1 where account=?", new String[]{account});
                            managerDb.execSQL("update manager set remembered=0 where account<>?", new String[]{account});
                            userDb.execSQL("update users set remembered=0 where account<>?", new String[]{account});
                            editor.putBoolean("remembered", true);
                        } else {
                            managerDb.execSQL("update manager set remembered=0");
                            editor.clear();
                        }
                        //在表中找到该管理员，提取注册码
                        Cursor c3 = managerDb.rawQuery("select * from manager where account=?", new String[]{account});
                        String registercode = null;
                        if (c3.moveToFirst()) {
                            registercode = c3.getString(c3.getColumnIndex("registercode"));
                        }
                        //判断注册码类型，转入相应界面，01表示馆长，02表示管理员
                        if (registercode.equals("01")) {
                            Intent intent1 = new Intent(Login.this, FirstManagerMainPlatform.class);
                            intent1.putExtra("account", account);
                            startActivity(intent1);
                        } else {
                            Intent intent1 = new Intent(Login.this, ManagerMainPlatform.class);
                            intent1.putExtra("account", account);
                            startActivity(intent1);
                        }
                    }
                    editor.apply();
                    Toast.makeText(Login.this, "登录成功", Toast.LENGTH_SHORT).show();
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
                startActivity(new Intent(Login.this, Register.class));
            }
        }));
        btFinish=findViewById(R.id.btFinish);
        btFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityManager.finishAll();
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });
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