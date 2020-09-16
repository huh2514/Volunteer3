package com.cookandroid.volunteer;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SelectedVolunteer extends AppCompatActivity {

    TextView selV, selT, selP;
    SearchResult.DBHelper myHelper;
    SQLiteDatabase sqlDB;

    int ID = ((SearchResult)SearchResult.mContext).ID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectedvolunteer);
        myHelper = new SearchResult.DBHelper(this);

        sqlDB = myHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT * FROM volunteerTBL;",null);

        selV = (TextView) findViewById(R.id.seldTItle);
        selT = (TextView) findViewById(R.id.seldTime);
        selP = (TextView) findViewById(R.id.seldPlace);

        while(cursor.moveToNext()){
            if(new Integer(cursor.getString(0)) == ID){
                selV.setText(cursor.getString(1));
                selP.setText(cursor.getString(2)+ cursor.getString(3));
                selT.setText(cursor.getString(4)+"월 " +cursor.getString(5)+"일부터 ~ " + cursor.getString(6)+"월 " +cursor.getString(7) + "일까지");
                break;
            }
            else{

                Log.d("found","못찾음");
                Log.d("found", String.valueOf(ID));
                Log.d("found", String.valueOf(new Integer(cursor.getString(0))));
            }
        }

        Log.d("found","실행됨");
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
