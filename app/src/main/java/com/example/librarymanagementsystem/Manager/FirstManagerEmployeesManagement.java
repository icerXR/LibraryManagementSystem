package com.example.librarymanagementsystem.Manager;

import android.annotation.SuppressLint;
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

public class FirstManagerEmployeesManagement extends BaseActivity {
    private ManagerSQL managerSQL;
    public SQLiteDatabase managerDb;
    private Button btSearchSummit;
    private Button btSearchBack;
    private EditText etEmployeesSearch;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_manager_employees_management);

        Intent intent=getIntent();
        String account=intent.getStringExtra("account");

        etEmployeesSearch=findViewById(R.id.etEmployeesSearch);

        managerSQL=new ManagerSQL(FirstManagerEmployeesManagement.this,"manager.db",null,1);
        managerDb=managerSQL.getReadableDatabase();
        //馆长输入管理员手机或姓名查询到该管理员
        btSearchSummit=findViewById(R.id.btSearchSummit);
        btSearchSummit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str=etEmployeesSearch.getText().toString();
                if(str.length()==11){
                    String sql="select * from manager where phone=?";
                    Cursor c=managerDb.rawQuery(sql,new String[]{str});
                    if(c.moveToFirst()){
                        Intent intent1=new Intent(FirstManagerEmployeesManagement.this, ManagerJobFix.class);
                        intent1.putExtra("str",str);
                        intent1.putExtra("account",account);
                        startActivity(intent1);
                    }else{
                        Toast.makeText(FirstManagerEmployeesManagement.this, "没有找到该员工，请重新输入", Toast.LENGTH_LONG).show();
                    }
                }else{
                    String sql="select * from manager where name=?";
                    Cursor c=managerDb.rawQuery(sql,new String[]{str});
                    if(c.moveToFirst()){
                        Intent intent1=new Intent(FirstManagerEmployeesManagement.this, ManagerJobFix.class);
                        intent1.putExtra("str",str);
                        intent1.putExtra("account",account);
                        startActivity(intent1);
                    }else{
                        Toast.makeText(FirstManagerEmployeesManagement.this, "没有找到该员工，请重新输入", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        btSearchBack=findViewById(R.id.btSearchBack);
        btSearchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(FirstManagerEmployeesManagement.this, FirstManagerMainPlatform.class);
                intent1.putExtra("account",account);
                startActivity(intent1);
            }
        });
    }
}