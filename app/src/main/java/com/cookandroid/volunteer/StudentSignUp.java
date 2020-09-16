package com.cookandroid.volunteer;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class StudentSignUp extends AppCompatActivity {
    EditText StdSignUpName, StdSignUpPW, StdSignUpEmail;
    Button StdSignUpBtn;
    DBHelper myHelper;
    SQLiteDatabase sqlDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentsignup_layout);

        StdSignUpName = (EditText)findViewById(R.id.StdSignUpName);
        StdSignUpPW = (EditText)findViewById(R.id.StdSignUpPW);
        StdSignUpEmail = (EditText)findViewById(R.id.StdSignUpEmail);
        StdSignUpBtn = (Button)findViewById(R.id.StdSignUpBtn);
        myHelper = new DBHelper(this);

        StdSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myHelper.getWritableDatabase();
                try {
                    sqlDB.execSQL("INSERT INTO StdAccountTBL VALUES('" + StdSignUpName.getText().toString() + "','" + StdSignUpPW.getText().toString() + "','" + StdSignUpEmail.getText().toString() + "');");
                }catch (SQLiteConstraintException e){
                    Toast.makeText(getApplicationContext(), "해당 이름은 이미 있습니다.",0).show();
                }
                sqlDB.close();
                Toast.makeText(getApplicationContext(), "입력됨",0).show();
            }
        });
    }



    public static class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context){
            super(context, "volunteerDB", null, 1);
        }
        @Override
        public void onCreate(SQLiteDatabase db){
            db.execSQL("CREATE TABLE StdAccountTBL(Name CHAR(20), Passwd CHAR(20), Email CHAR(20) PRIMARY KEY);");
            db.execSQL("CREATE TABLE volunteerTBL(ID INTEGER PRIMARY KEY AUTOINCREMENT,vtitle CHAR(20) NOT NULL, vaddr1 CHAR(20), vaddr2 CHAR(20), startmonth INTEGER, startday INTEGER, endmonth INTEGER, endday INTEGER);");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldverson, int newVerson) {
            db.execSQL("DROP TABLE IF EXISTS StdAccountTBL");
            onCreate(db);
        }

    }
}
