package com.example.librarymanagementsystem.User;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.core.app.NotificationCompat;

import com.example.librarymanagementsystem.BaseActivity;
import com.example.librarymanagementsystem.R;
import com.example.librarymanagementsystem.app.BookNeededPostActivity;
import com.example.librarymanagementsystem.app.BookSearchActivity;
import com.example.librarymanagementsystem.lrj.MySqliteOpenHelp;
import com.example.librarymanagementsystem.lrj.overdue1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserMainPlatform extends BaseActivity {

    private Button btMyInfo;
    private ImageButton BookNeeded;
    private ImageButton BookSearch;
    private ImageButton BorrowList;
    private Context context=this;
    public int channel_id = 0;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main_platform);

        Intent intent=getIntent();
        String account=intent.getStringExtra("account");

        BookNeeded=findViewById(R.id.BookNeeded);
        BookNeeded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(UserMainPlatform.this, BookNeededPostActivity.class);
                intent1.putExtra("account",account);
                startActivity(intent1);
            }
        });

        BookSearch=findViewById(R.id.BookSearch);
        BookSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(UserMainPlatform.this, BookSearchActivity.class);
                intent1.putExtra("account",account);
                startActivity(intent1);
            }
        });

        BorrowList=findViewById(R.id.BorrowList);
        BorrowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

                SQLiteOpenHelper helper = MySqliteOpenHelp.getInstance(context);
                SQLiteDatabase db = helper.getReadableDatabase();
                if (db.isOpen()) {
                    //返回游标
                    Cursor cursor = db.rawQuery("select * from User_books where user_account = '"+account+"';" , null);
                    //迭代游标
                    while (cursor.moveToNext()) {
                        String endDate = cursor.getString(cursor.getColumnIndex("bookOverdue"));
                        String bookName = cursor.getString(cursor.getColumnIndex("bookName"));

                        try {
                            //当前系统时间
                            Date curDate = new Date(System.currentTimeMillis());
                            Date end = simpleDateFormat.parse(endDate);
                            long diff = end.getTime() - curDate.getTime();
                            if (diff <= 0) {
                                //逾期了
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                    String id = "channel_1";
                                    String description = "143";
                                    int importance = NotificationManager.IMPORTANCE_HIGH;
                                    NotificationChannel channel = new NotificationChannel(id, description, importance);
                                    channel.enableLights(true);
                                    channel.enableVibration(true);
                                    manager.createNotificationChannel(channel);
                                    NotificationCompat.Builder builder = new NotificationCompat.Builder(context, id)
                                            .setContentTitle("书籍逾期通知")
                                            .setContentText("您借阅的《" + bookName + "》已经逾期，请尽快归还")
                                            .setAutoCancel(true)
                                            .setSmallIcon(R.drawable.ic_launcher_background);
                                    manager.notify(channel_id++, builder.build());
                                }

                            } else if (diff > 0 && diff / 1000 <= 259200) {
                                //剩余三天
                                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                                    String id = "channel_1";
                                    String description = "143";
                                    int importance = NotificationManager.IMPORTANCE_HIGH;
                                    NotificationChannel channel = new NotificationChannel(id, description, importance);
                                    channel.enableLights(true);
                                    channel.enableVibration(true);
                                    manager.createNotificationChannel(channel);
                                    NotificationCompat.Builder builder = new NotificationCompat.Builder(context, id)
                                            .setContentTitle("书籍逾期通知")
                                            .setContentText("您借阅的《" + bookName + "》剩余时间不足三天，请尽快归还")
                                            .setAutoCancel(true)
                                            .setSmallIcon(R.drawable.ic_launcher_background);
                                    manager.notify(channel_id++, builder.build());

                                }
                            }
                        }catch (ParseException e){
                            e.printStackTrace();
                        }
                    }
                    cursor.close();
                    db.close();
                }
                Intent intent1=new Intent(UserMainPlatform.this, overdue1.class);
                intent1.putExtra("account",account);
                startActivity(intent1);
            }
        });

        btMyInfo=findViewById(R.id.btMyInfo);
        btMyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(UserMainPlatform.this,UserInfoPlatform.class);
                intent1.putExtra("account",account);
                startActivity(intent1);
            }
        });
    }
}