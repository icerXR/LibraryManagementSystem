package com.example.librarymanagementsystem.Manager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.librarymanagementsystem.BaseActivity;
import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.app.BookUpdateActivity;
import com.example.librarymanagementsystem.app.LendAndBackActivity;
import com.example.librarymanagementsystem.app.ManagerListControl;
import com.example.librarymanagementsystem.app.NewBookActivity;

public class ManagerMainPlatform extends BaseActivity {

    private Button btMyInfo;
    private ImageButton btBorrowAndReturn;
    private ImageButton BookUpdate;
    private ImageButton NewBook;
    private ImageButton BookList;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_main_platform);

        Intent intent=getIntent();
        String account=intent.getStringExtra("account");

        btBorrowAndReturn=findViewById(R.id.btBorrowAndReturn);
        btBorrowAndReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ManagerMainPlatform.this, LendAndBackActivity.class);
                intent1.putExtra("account",account);
                startActivity(intent1);
            }
        });

        BookUpdate=findViewById(R.id.BookUpdate);
        BookUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ManagerMainPlatform.this, BookUpdateActivity.class);
                intent1.putExtra("account",account);
                startActivity(intent1);
            }
        });

        NewBook=findViewById(R.id.NewBook);
        NewBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ManagerMainPlatform.this, NewBookActivity.class);
                intent1.putExtra("account",account);
                startActivity(intent1);
            }
        });

        BookList=findViewById(R.id.BookList);
        BookList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ManagerMainPlatform.this, ManagerListControl.class);
                intent1.putExtra("account",account);
                startActivity(intent1);
            }
        });

        btMyInfo=(Button) findViewById(R.id.btMyInfo);
        btMyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ManagerMainPlatform.this, ManagerInfoPlatform.class);
                intent1.putExtra("account",account);
                startActivity(intent1);
            }
        });
    }
}