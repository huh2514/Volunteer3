package com.cookandroid.volunteer;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MyInformation extends AppCompatActivity {
    MyInformation.DBHelper myHelper;
    SQLiteDatabase sqlDB;
    String getEmail;
    TextView myName, myEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myinformation_layout);

        getEmail = ((StdLogin)StdLogin.context_main).sendEmail;

        myHelper = new MyInformation.DBHelper(this);

        myName = (TextView)findViewById(R.id.myName);
        myEmail = (TextView)findViewById(R.id.myEmail);
    }

    @Override
    protected void onStart() {
        super.onStart();

        sqlDB = myHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT * FROM StdAccountTBL;", null);
        while(cursor.moveToNext()){
            if(cursor.getString(2).equals(getEmail)){
                myEmail.setText(cursor.getString(2));
                myName.setText(cursor.getString(0));
            }
        }
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
