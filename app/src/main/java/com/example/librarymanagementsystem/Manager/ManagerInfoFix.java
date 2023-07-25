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

public class ManagerInfoFix extends BaseActivity {
    private ManagerSQL managerSQL;
    public SQLiteDatabase managerDb;
    private EditText etName;
    private EditText etPhone;
    private EditText etPwd;
    private EditText etPwdAgain;
    private Button btManagerSummit;
    private Button btManagerBack;
    @SuppressLint({"Range"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_info_fix);

        etName=findViewById(R.id.etName);
        etPhone=findViewById(R.id.etPhone);
        etPwd=findViewById(R.id.etPwd);
        etPwdAgain=findViewById(R.id.etPwdAgain);

        Intent intent=getIntent();
        String account=intent.getStringExtra("account");

        managerSQL=new ManagerSQL(ManagerInfoFix.this,"manager.db",null,1);
        managerDb=managerSQL.getReadableDatabase();
        String sql="select * from manager where account=?";
        Cursor c=managerDb.rawQuery(sql,new String[]{account});

        if(c.moveToFirst()){
            etName.setText(c.getString(c.getColumnIndex("name")));
            etPhone.setText(c.getString(c.getColumnIndex("phone")));
            etPwd.setText(c.getString(c.getColumnIndex("password")));
            etPwdAgain.setText(c.getString(c.getColumnIndex("password")));
        }

        btManagerSummit=findViewById(R.id.btManagerSummit);
        btManagerSummit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Name=etName.getText().toString();
                String Phone=etPhone.getText().toString();
                String Pwd=etPwd.getText().toString();
                String PwdAgain=etPwdAgain.getText().toString();

                ContentValues cv=new ContentValues();
                cv.put("name",Name);
                managerDb.update("manager",cv,"account=?",new String[]{account});
                cv.put("phone",Phone);
                managerDb.update("manager",cv,"account=?",new String[]{account});
                if(Pwd.length()>0&&Pwd.equals(PwdAgain)){
                    cv.put("password",Pwd);
                    managerDb.update("manager",cv,"account=?",new String[]{account});
                }
                Toast.makeText(ManagerInfoFix.this, "修改完成", Toast.LENGTH_SHORT).show();
                Intent intent1=new Intent(ManagerInfoFix.this,ManagerInfoPlatform.class);
                intent1.putExtra("account",account);
                startActivity(intent1);
            }
        });

        btManagerBack=findViewById(R.id.btManagerBack);
        btManagerBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerInfoFix.this,ManagerInfoPlatform.class));
            }
        });
    }
}