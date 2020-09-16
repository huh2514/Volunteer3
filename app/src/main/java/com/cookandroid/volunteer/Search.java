package com.cookandroid.volunteer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.AndroidException;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Search extends AppCompatActivity {
    EditText title;
    String sel1Save, sel2Save;
    int smon, sday, emon, eday;
    Spinner seladdr1, seladdr2, StartMonth, StartDay, EndMonth, EndDay;
    Button btnSearch;
    public static Context context_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchvolunteerlayout);
        setTitle("봉사활동 조회");

        title = (EditText)findViewById(R.id.title);
        seladdr1 = (Spinner) findViewById(R.id.seladdr1);
        seladdr2 = (Spinner) findViewById(R.id.seladdr2);
        StartMonth = (Spinner)findViewById(R.id.StartMonths);
        StartDay = (Spinner)findViewById(R.id.StartDays);
        EndMonth = (Spinner)findViewById(R.id.EndMonths);
        EndDay = (Spinner)findViewById(R.id.EndDays);
        btnSearch = (Button)findViewById(R.id.btnSearch);

        context_main = this;

        final String[] addr = {"남동구","동구","부평","미추홀","" +
                "서구","중구","연수구","계양구"};
        final String[] addr1 = {"종로구","중구","용산구","성동구","" +
                "광진구","동대문구","중랑구","성북구","강북구","도봉구","노원구","은평구",
                "서대문구","마포구","양천구","강서구","구로구","금천구","영등포구","동작구"
                ,"관악구","서초구","강남구","송파구","강동구"};
        final String[] addr2 = {"가평군","고양시 덕양구","고양시 일산동구","고양시 일산서구","" +
                "과천시","광명시","광주시","구리시","군포시","김포시","남양주시","동두천시",
                "부천시","성남시 분당구","성남시 수정구","성남시 중원구","수원시 권선구","안성시","안양시 동안구","안양시 만안구",
                "양주시","양평군","여주시","연천군","오산시","용인시 기흥구","용인시 수지구","용인시 처인구",
                "의왕시","의정부시","이천시","파주시","평택시","포천시","하남시","화성시"};
        final ArrayAdapter adapter = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,addr);
        final ArrayAdapter adapter1 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,addr1);
        final ArrayAdapter adapter2 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,addr2);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(smon > emon){
                    Toast.makeText(getApplicationContext(), "시작일이 종료일보다 큽니다.",0).show();
                    return;
                }else if(smon == emon){
                    if(sday>eday) {
                        Toast.makeText(getApplicationContext(), "시작일이 종료일보다 큽니다.", 0).show();
                        return;
                    }
                }
                Intent intent = new Intent();
                /*ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.SearchResult");
                intent.setComponent(cmpName);*/
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.FindVolunteer");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });
//something changed

        seladdr1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sel1Save = adapterView.getItemAtPosition(i).toString();
                switch (sel1Save){
                    case "인천" :
                        seladdr2.setAdapter(adapter);
                        break;
                    case "서울" :
                        seladdr2.setAdapter(adapter1);
                        break;
                    case "경기" :
                        seladdr2.setAdapter(adapter2);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        seladdr2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                sel2Save = adapterView.getItemAtPosition(i).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        StartMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (adapterView.getItemAtPosition(i).toString()){
                    case "1월" :
                        smon = 1;
                        break;
                    case "2월" :
                        smon = 2;
                        break;
                    case "3월" :
                        smon = 3;
                        break;
                    case "4월" :
                        smon = 4;
                        break;
                    case "5월" :
                        smon = 5;
                        break;
                    case "6월" :
                        smon = 6;
                        break;
                    case "7월" :
                        smon = 7;
                        break;
                    case "8월" :
                        smon = 8;
                        break;
                    case "9월" :
                        smon = 9;
                        break;
                    case "10월" :
                        smon = 10;
                        break;
                    case "11월" :
                        smon = 11;
                        break;
                    case "12월" :
                        smon = 12;
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        StartDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (adapterView.getItemAtPosition(i).toString()){
                    case "1일" :
                        sday = 1;
                        break;
                    case "2일" :
                        sday = 2;
                        break;
                    case "3일" :
                        sday = 3;
                        break;
                    case "4일" :
                        sday = 4;
                        break;
                    case "5일" :
                        sday = 5;
                        break;
                    case "6일" :
                        sday = 6;
                        break;
                    case "7일" :
                        sday = 7;
                        break;
                    case "8일" :
                        sday = 8;
                        break;
                    case "9일" :
                        sday = 9;
                        break;
                    case "10일" :
                        sday = 10;
                        break;
                    case "11일" :
                        sday = 11;
                        break;
                    case "12일" :
                        sday = 12;
                        break;
                    case "13일" :
                        sday = 12;
                        break;
                    case "14일" :
                        sday = 12;
                        break;
                    case "15일" :
                        sday = 12;
                        break;
                    case "16일" :
                        sday = 12;
                        break;
                    case "17일" :
                        sday = 12;
                        break;
                    case "18일" :
                        sday = 12;
                        break;
                    case "19일" :
                        sday = 12;
                        break;
                    case "20일" :
                        sday = 12;
                        break;
                    case "21일" :
                        sday = 12;
                        break;
                    case "22일" :
                        sday = 12;
                        break;
                    case "23일" :
                        sday = 12;
                        break;
                    case "24일" :
                        sday = 12;
                        break;
                    case "25일" :
                        sday = 12;
                        break;
                    case "26일" :
                        sday = 12;
                        break;
                    case "27일" :
                        sday = 12;
                        break;
                    case "28일" :
                        sday = 12;
                        break;
                    case "29일" :
                        sday = 12;
                        break;
                    case "30일" :
                        sday = 12;
                        break;
                    case "31일" :
                        sday = 12;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        EndMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (adapterView.getItemAtPosition(i).toString()){
                    case "1월" :
                        emon = 1;
                        break;
                    case "2월" :
                        emon = 2;
                        break;
                    case "3월" :
                        emon = 3;
                        break;
                    case "4월" :
                        emon = 4;
                        break;
                    case "5월" :
                        emon = 5;
                        break;
                    case "6월" :
                        emon = 6;
                        break;
                    case "7월" :
                        emon = 7;
                        break;
                    case "8월" :
                        emon = 8;
                        break;
                    case "9월" :
                        emon = 9;
                        break;
                    case "10월" :
                        emon = 10;
                        break;
                    case "11월" :
                        emon = 11;
                        break;
                    case "12월" :
                        emon = 12;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        EndDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (adapterView.getItemAtPosition(i).toString()){
                    case "1일" :
                        eday = 1;
                        break;
                    case "2일" :
                        eday = 2;
                        break;
                    case "3일" :
                        eday = 3;
                        break;
                    case "4일" :
                        eday = 4;
                        break;
                    case "5일" :
                        eday = 5;
                        break;
                    case "6일" :
                        eday = 6;
                        break;
                    case "7일" :
                        eday = 7;
                        break;
                    case "8일" :
                        eday = 8;
                        break;
                    case "9일" :
                        eday = 9;
                        break;
                    case "10일" :
                        eday = 10;
                        break;
                    case "11일" :
                        eday = 11;
                        break;
                    case "12일" :
                        eday = 12;
                        break;
                    case "13일" :
                        eday = 12;
                        break;
                    case "14일" :
                        eday = 12;
                        break;
                    case "15일" :
                        eday = 12;
                        break;
                    case "16일" :
                        eday = 12;
                        break;
                    case "17일" :
                        eday = 12;
                        break;
                    case "18일" :
                        eday = 12;
                        break;
                    case "19일" :
                        eday = 12;
                        break;
                    case "20일" :
                        eday = 12;
                        break;
                    case "21일" :
                        eday = 12;
                        break;
                    case "22일" :
                        eday = 12;
                        break;
                    case "23일" :
                        eday = 12;
                        break;
                    case "24일" :
                        eday = 12;
                        break;
                    case "25일" :
                        eday = 12;
                        break;
                    case "26일" :
                        eday = 12;
                        break;
                    case "27일" :
                        eday = 12;
                        break;
                    case "28일" :
                        eday = 12;
                        break;
                    case "29일" :
                        eday = 12;
                        break;
                    case "30일" :
                        eday = 12;
                        break;
                    case "31일" :
                        eday = 12;
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
