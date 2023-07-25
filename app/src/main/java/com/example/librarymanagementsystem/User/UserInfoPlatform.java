package com.example.librarymanagementsystem.User;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.librarymanagementsystem.BaseActivity;
import com.example.librarymanagementsystem.LoginAndRegister.Login;
import com.example.librarymanagementsystem.R;

public class UserInfoPlatform extends BaseActivity {

    private UserMySQL userMySQL;
    public SQLiteDatabase userDb;
    private Button btLibrary;
    private Button btReturnToLogin;
    private Button btGoToUserInfoFix;
    private TextView tvUserName;
    private TextView tvUserAccount;
    private TextView tvUserCanBorrow;
    private TextView tvUserBorrowing;

    @SuppressLint({"Range", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info_platform);

        tvUserName=findViewById(R.id.tvUserName);
        tvUserAccount=findViewById(R.id.tvUserAccount);
        tvUserCanBorrow=findViewById(R.id.tvUserCanBorrow);
        tvUserBorrowing=findViewById(R.id.tvUserBorrowing);

        Intent intent=getIntent();
        String account=intent.getStringExtra("account");

        userMySQL = new UserMySQL(UserInfoPlatform.this,"userdb.db",null,1);
        userDb=userMySQL.getReadableDatabase();
        String sql="select * from users where account=?";
        Cursor c=userDb.rawQuery(sql,new String[]{account});

        if(c.moveToFirst()){
            tvUserName.setText(c.getString(c.getColumnIndex("name")));
            tvUserAccount.setText(c.getString(c.getColumnIndex("account")));
            tvUserCanBorrow.setText("可借册数："+c.getString(c.getColumnIndex("couldborrowbooks")));
            tvUserBorrowing.setText("在借册数："+c.getString(c.getColumnIndex("borrowing")));
        }

        btLibrary=findViewById(R.id.btLibrary);
        btLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserInfoPlatform.this,UserMainPlatform.class));
            }
        });

        btReturnToLogin=findViewById(R.id.btReturnToLogin);
        btReturnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserInfoPlatform.this, Login.class));
            }
        });

        btGoToUserInfoFix=findViewById(R.id.btGoToUserInfoFix);
        btGoToUserInfoFix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(UserInfoPlatform.this,UserInfoFix.class);
                intent1.putExtra("account",account);
                startActivity(intent1);
            }
        });
    }
}