package com.example.librarymanagementsystem.Manager;

import androidx.appcompat.app.AppCompatActivity;

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

public class ManagerJobFix extends BaseActivity {
    private EditText etName;
    private EditText etPhone;
    private EditText etRegisterCode;
    private Button btFirstManagerSummit;
    private Button btFirstManagerBack;
    private ManagerSQL managerSQL;
    public SQLiteDatabase managerDb;

    @SuppressLint({"Range", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_job_fix);

        Intent intent=getIntent();
        String str=intent.getStringExtra("str");
        String account=intent.getStringExtra("account");

        etName=findViewById(R.id.etName);
        etPhone=findViewById(R.id.etPhone);
        etRegisterCode=findViewById(R.id.etRegisterCode);

        managerSQL=new ManagerSQL(ManagerJobFix.this,"manager.db",null,1);
        managerDb=managerSQL.getReadableDatabase();

        if(str.length()==11){
            String sql="select * from manager where phone=?";
            Cursor c=managerDb.rawQuery(sql,new String[]{str});
            if(c.moveToFirst()){
                etName.setText(c.getString(c.getColumnIndex("name")));
                etPhone.setText(c.getString(c.getColumnIndex("phone")));
                etRegisterCode.setText(c.getString(c.getColumnIndex("registercode")));
            }
        }else{
            String sql="select * from manager where name=?";
            Cursor c=managerDb.rawQuery(sql,new String[]{str});
            if(c.moveToFirst()){
                etName.setText(c.getString(c.getColumnIndex("name")));
                etPhone.setText(c.getString(c.getColumnIndex("phone")));
                etRegisterCode.setText(c.getString(c.getColumnIndex("registercode")));
            }
        }

        btFirstManagerSummit=findViewById(R.id.btFirstManagerSummit);
        btFirstManagerSummit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=etName.getText().toString();
                String phone=etPhone.getText().toString();
                String registercode=etRegisterCode.getText().toString();
                if(str.length()==11){
                    ContentValues cv=new ContentValues();
                    cv.put("name",name);
                    managerDb.update("manager",cv,"phone=?",new String[]{str});
                    cv.put("phone",phone);
                    managerDb.update("manager",cv,"phone=?",new String[]{str});
                    cv.put("registercode",registercode);
                    managerDb.update("manager",cv,"phone=?",new String[]{str});
                }else{
                    ContentValues cv=new ContentValues();
                    cv.put("name",name);
                    managerDb.update("manager",cv,"name=?",new String[]{str});
                    cv.put("phone",phone);
                    managerDb.update("manager",cv,"name=?",new String[]{str});
                    cv.put("registercode",registercode);
                    managerDb.update("manager",cv,"name=?",new String[]{str});
                }
                Toast.makeText(ManagerJobFix.this, "修改完成", Toast.LENGTH_SHORT).show();
                Intent intent2=new Intent(ManagerJobFix.this,FirstManagerEmployeesManagement.class);
                intent2.putExtra("account",account);
                startActivity(intent2);
            }
        });

        btFirstManagerBack=findViewById(R.id.btFirstManagerBack);
        btFirstManagerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2=new Intent(ManagerJobFix.this,FirstManagerEmployeesManagement.class);
                intent2.putExtra("account",account);
                startActivity(intent2);
            }
        });
    }
}