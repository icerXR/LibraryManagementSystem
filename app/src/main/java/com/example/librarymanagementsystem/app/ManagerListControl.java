package com.example.librarymanagementsystem.app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.librarymanagementsystem.R;

public class ManagerListControl extends AppCompatActivity {

    private Button btBookNeededList;
    private Button btBookSendList;
    private Button btBookReceiveList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_list_control);

        btBookNeededList=findViewById(R.id.btBookNeededList);
        btBookNeededList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerListControl.this,BOOK_NEEDED.class));
            }
        });

        btBookSendList=findViewById(R.id.btBookSendList);
        btBookSendList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerListControl.this, OrderActivity.class));
            }
        });

        btBookReceiveList=findViewById(R.id.btBookReceiveList);
        btBookReceiveList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerListControl.this, OrderManageActivity.class));
            }
        });
    }
}