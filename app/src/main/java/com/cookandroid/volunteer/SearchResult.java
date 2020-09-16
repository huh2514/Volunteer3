package com.cookandroid.volunteer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class SearchResult extends AppCompatActivity {
    public static Context mContext;

    DBHelper myHelper;
    SQLiteDatabase sqlDB;
    EditText edtTitle;
    String addr1, addr2;
    int smon, sday;

    int ID;
    String title, addr;

    private ArrayList<Dictionary> mArrayList;
    private CustomAdapter mAdapter;
    private int count = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchresult);
        setTitle("조회결과");
        myHelper = new DBHelper(this);

        edtTitle = ((Search)Search.context_main).title;
        addr1 = ((Search)Search.context_main).sel1Save;
        addr2 = ((Search)Search.context_main).sel2Save;
        smon = ((Search)Search.context_main).smon;
        sday = ((Search)Search.context_main).sday;

        mContext = this;

        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview_main_list);
        LinearLayoutManager mLinearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);


        mArrayList = new ArrayList<>();

        mAdapter = new CustomAdapter( mArrayList);
        mRecyclerView.setAdapter(mAdapter);


        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(),
                mLinearLayoutManager.getOrientation());
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        sqlDB = myHelper.getReadableDatabase();
        Cursor cursor;
        cursor = sqlDB.rawQuery("SELECT * FROM volunteerTBL;",null);

        while(cursor.moveToNext()){
            count++;
            if(cursor.getString(1).contains(edtTitle.getText()) && cursor.getString(2).equals(addr1) && cursor.getString(3).equals(addr2)){
                Dictionary data = new Dictionary(new Integer(cursor.getString(0)),count+"", cursor.getString(1), cursor.getString(2) + cursor.getString(3));

                Log.d("found","찾았습니다");

                //mArrayList.add(0, dict); //RecyclerView의 첫 줄에 삽입
                mArrayList.add(data); // RecyclerView의 마지막 줄에 삽입

                mAdapter.notifyDataSetChanged();
            }
            else{

                Log.d("found", String.valueOf(sday));
                Log.d("found", String.valueOf(new Integer(cursor.getString(5))));
                Log.d("found","못찾음");
            }
        }

    }
    public void newIntent(Intent intent, CustomAdapter.CustomViewHolder viewHolder){
        ID = (int) viewHolder.ID;

        Log.d("found", String.valueOf(ID));

        startActivity(intent);
    }


    public static class DBHelper extends SQLiteOpenHelper{
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
