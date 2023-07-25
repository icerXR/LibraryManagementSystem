package com.example.librarymanagementsystem.Manager;

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

public class ManagerInfoPlatform extends BaseActivity {

    private ManagerSQL managerSQL;
    public SQLiteDatabase managerDb;
    private TextView tvManagerName;
    private TextView tvManagerAccount;
    private TextView tvJob;
    private Button btLibrary;
    private Button btReturnToLogin;
    private Button btGoToManagerInfoFix;

    @SuppressLint({"MissingInflatedId", "Range"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_info_platform);

        tvManagerName=findViewById(R.id.tvManagerName);
        tvManagerAccount=findViewById(R.id.tvManagerAccount);
        tvJob=findViewById(R.id.tvJob);

        Intent intent=getIntent();
        String account=intent.getStringExtra("account");

        managerSQL=new ManagerSQL(ManagerInfoPlatform.this,"manager.db",null,1);
        managerDb=managerSQL.getReadableDatabase();
        String sql="select * from manager where account=?";
        Cursor c=managerDb.rawQuery(sql,new String[]{account});

        if(c.moveToFirst()){
            tvManagerName.setText(c.getString(c.getColumnIndex("name")));
            tvManagerAccount.setText(c.getString(c.getColumnIndex("account")));
            String job=c.getString(c.getColumnIndex("registercode"));
            switch (job){
                case "01":tvJob.setText("职务：馆长");break;
                case "02":tvJob.setText("职务：管理员");break;
                case "03":tvJob.setText("职务：采购员");break;
            }
        }

        btLibrary=findViewById(R.id.btLibrary);
        btLibrary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(account.substring(8,10).equals("01")){
                    Intent intent1=new Intent(ManagerInfoPlatform.this,FirstManagerMainPlatform.class);
                    intent1.putExtra("account",account);
                    startActivity(intent1);
                }else{
                    Intent intent1=new Intent(ManagerInfoPlatform.this,ManagerMainPlatform.class);
                    intent1.putExtra("account",account);
                    startActivity(intent1);
                }
            }
        });

        btReturnToLogin=findViewById(R.id.btReturnToLogin);
        btReturnToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ManagerInfoPlatform.this, Login.class));
            }
        });

        btGoToManagerInfoFix=findViewById(R.id.btGoToManagerInfoFix);
        btGoToManagerInfoFix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(ManagerInfoPlatform.this,ManagerInfoFix.class);
                intent1.putExtra("account",account);
                startActivity(intent1);
            }
        });
    }
}