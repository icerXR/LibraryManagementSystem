package com.example.librarymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegisterComplete extends AppCompatActivity {

    private TextView tvRegisterComplete;
    private Button btGoToLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_complete);
        //显示上一界面中注册完成的账号
        tvRegisterComplete=(TextView) findViewById(R.id.tvRegisterComplete);
        Intent intent=getIntent();
        String account=intent.getStringExtra("account");
        tvRegisterComplete.setText(account);

        btGoToLogin=(Button) findViewById(R.id.btGoToLogin);
        btGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(RegisterComplete.this,Login.class);
                intent1.putExtra("account1",account);
                startActivity(intent1);
            }
        });
    }
}