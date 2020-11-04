package com.cookandroid.volunteer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StdLogin extends AppCompatActivity {
    StdLogin.DBHelper myHelper;
    SQLiteDatabase sqlDB;
    EditText inputEmail, inputPW;
    Button loginBtn, SignUp;
    String sendEmail;

    public static Context context_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stdlogin_layout);
        setTitle("학생 로그인");
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);
        inputEmail = (EditText)findViewById(R.id.inputEmail);
        inputPW = (EditText)findViewById(R.id.inputPW);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        SignUp = (Button) findViewById(R.id.SignupBtn);

                context_main = this;

        myHelper = new StdLogin.DBHelper(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sqlDB = myHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM StdAccountTBL;", null);

                boolean isLogin = false;

                while(cursor.moveToNext()){
                    if(cursor.getString(2).equals(inputEmail.getText().toString()) && cursor.getString(1).equals(inputPW.getText().toString())) {
                        sendEmail = cursor.getString(2);
                        Intent intent = new Intent();
                        ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                                "com.cookandroid.volunteer.StudentMain");
                        intent.setComponent(cmpName);
                        startActivity(intent);
                        isLogin = true;
                        break;
                    }

                }
                if(!isLogin)
                    Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 맞지않습니다!",0).show();
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.SignUp");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });
    }

    public static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context){
            super(context, "volunteerDB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL("CREATE TABLE volunteerTBL(ID INTEGER PRIMARY KEY AUTOINCREMENT,vtitle CHAR(20) NOT NULL, vaddr1 CHAR(20), vaddr2 CHAR(20), startmonth INTEGER, startday INTEGER, endmonth INTEGER, endday INTEGER);");
            db.execSQL("CREATE TABLE StdAccountTBL(Name CHAR(20), Passwd CHAR(20), Email CHAR(20) PRIMARY KEY);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldverson, int newVerson) {
            db.execSQL("DROP TABLE IF EXISTS volunteerTBL");
            onCreate(db);
        }

    }
}
