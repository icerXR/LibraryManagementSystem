package com.example.librarymanagementsystem.Manager;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.librarymanagementsystem.BaseActivity;
import com.example.librarymanagementsystem.R;

public class FirstManagerMainPlatform extends BaseActivity {
    private Button btMyInfo;
    private ImageButton btEmployeesManagement;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_manager_main_platform);

        Intent intent=getIntent();
        String account=intent.getStringExtra("account");

        btMyInfo=(Button) findViewById(R.id.btMyInfo);
        btMyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(FirstManagerMainPlatform.this, ManagerInfoPlatform.class);
                intent1.putExtra("account",account);
                startActivity(intent1);
            }
        });

        btEmployeesManagement=findViewById(R.id.btEmployeesManagement);
        btEmployeesManagement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(FirstManagerMainPlatform.this, FirstManagerEmployeesManagement.class);
                intent1.putExtra("account",account);
                startActivity(intent1);
            }
        });
    }
}