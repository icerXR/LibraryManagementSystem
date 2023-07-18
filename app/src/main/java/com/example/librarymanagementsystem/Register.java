package com.example.librarymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Register extends AppCompatActivity {
    private Button btRegister;
    private Button btBack;
    private EditText etName;
    private EditText etPhone;
    private EditText etPwd;
    private EditText etPwdAgain;
    private EditText etRegisterCode;
    private UserMySQL userMySQL;
    private ManagerSQL managerSQL;
    public SQLiteDatabase userDb;
    public SQLiteDatabase managerDb;
    public int userid=1;
    public int managerid=1;
    public String Account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName=(EditText)findViewById(R.id.etName);
        etPhone=(EditText) findViewById(R.id.etPhone);
        etPwd=(EditText) findViewById(R.id.etPwd);
        etPwdAgain=(EditText) findViewById(R.id.etPwdAgain);
        etRegisterCode=(EditText) findViewById(R.id.etRegisterCode);
        //创建两个表，由于在登录界面中已创建这两个表，故在这里创建时，保证数据库名字等与之前相同，即可保证操作同一个数据库
        managerSQL=new ManagerSQL(Register.this,"manager.db",null,1);
        managerDb=managerSQL.getWritableDatabase();

        userMySQL = new UserMySQL(Register.this,"userdb.db",null,1);
        userDb=userMySQL.getWritableDatabase();
        //读取当前系统时间
        Date date=new Date();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String currentTime=sdf.format(date);
        //取出两位随机数，稍后加在账号后两位
        Random random=new Random();
        int randomNum=random.nextInt(50)+49;
        String randomString=Integer.toString(randomNum);
        //点击按钮后，根据账号长度写入对应数据库中
        btRegister=(Button) findViewById(R.id.btRegister);
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name=etName.getText().toString();
                String Phone=etPhone.getText().toString();
                String Pwd=etPwd.getText().toString();
                String PwdAgain=etPwdAgain.getText().toString();
                String RegisterCode=etRegisterCode.getText().toString();
                //系统自动生成账号
                if(currentTime.length()>0&&Phone.length()==11){
                    if(!(RegisterCode.length()>0)){
                        //用户账号组成：4位注册月日+手机尾号后四位+2位随机数
                        Account=currentTime.substring(4,8)+Phone.substring(Phone.length()-4, Phone.length())+randomString;
                    }else{
                        //管理员账号组成：4位注册月日+手机尾号后四位+2位注册码+2位随机数
                        Account=currentTime.substring(4,8)+Phone.substring(Phone.length()-4, Phone.length())+RegisterCode+randomString;
                    }
                }
                //如两次输入密码一致，则写入对应数据库
                if(Pwd.equals(PwdAgain)&&Pwd.length()>0&&Account!=null){
                    if(Account.length()==10){
                        //写入用户信息表
                        userDb.execSQL("insert into users(userid,phone,name,couldborrowbooks,borrowing,account,password,remembered) values(?,?,?,?,?,?,?,0)",
                                new String[]{Integer.toString(userid),Phone,Name,"3","0",Account,Pwd});
                        userid+=1;
                    }else{
                        //写入管理员信息表
                        managerDb.execSQL("insert into manager(managerid,phone,name,account,password,remembered) values(?,?,?,?,?,0)",
                                new String[]{Integer.toString(managerid),Phone,Name,Account,Pwd});
                        managerid+=1;
                    }
                    //启动新界面，通过Intent使得账号显示在下一界面中
                    Intent intent=new Intent(Register.this,RegisterComplete.class);
                    intent.putExtra("account",Account);
                    startActivity(intent);
                }else{
                    Toast.makeText(Register.this, "信息输入有误，请检查", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //返回按钮：返回到上一界面
        btBack=(Button) findViewById(R.id.btBack);
        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}