package com.cookandroid.volunteer;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;

public class SelectedVolunteer extends AppCompatActivity {



    //스케쥴 데이터 가져올떄
    String myJSON;
    JSONArray peoples = null;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_vDate = "vDate";
    private static final String TAG_vPlace = "vPlace";
    private static final String TAG_vCode = "vCode";
    private static final String TAG_Email = "Email";

    public final String schedulePreference = "com.volunteerLogin.schedulePreference";

    //로그인 정보
    public final String key02 = "pwd";

    //봉사활동 스케쥴 정보
    public final String scheduleKey01 = "Email";
    public String schedulegetEmail="em";


    TextView seldTItle, seldTime, seldPlace, seldHour, seldNTime, seldhoumany, seldApplyd, seldBoon, seldRecPeriod, seldRegist, seldProgrmCn;
    SearchData searchVo = ((ResultView)ResultView.context_main).data;

    //셰어드 프리퍼런스 변수
    public final String PREFERENCE = "com.volunteerLogin.samplesharepreference";
    public final String key01 = "Email";
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectedvolunteer);

        seldTItle = (TextView) findViewById(R.id.seldTItle);
        seldTime = (TextView) findViewById(R.id.seldTime);
        seldPlace = (TextView) findViewById(R.id.seldPlace);
        seldHour = (TextView) findViewById(R.id.seldHour);
        seldNTime = (TextView) findViewById(R.id.seldNTime);
        seldhoumany = (TextView) findViewById(R.id.seldhoumany);
        seldApplyd = (TextView) findViewById(R.id.seldApplyd);
        seldBoon = (TextView) findViewById(R.id.seldBoon);
        seldRecPeriod = (TextView) findViewById(R.id.seldRecPeriod);
        seldRegist = (TextView) findViewById(R.id.seldRegist);
        seldProgrmCn= (TextView) findViewById(R.id.seldContents);

        seldTItle.setText(searchVo.progrmSj);
        seldTime.setText(searchVo.progrmBgnde + "~" + searchVo.progrmEndde);
        seldPlace.setText(searchVo.actPlace);
        seldHour.setText(searchVo.actBeginTm + "~" + searchVo.actEndTm);
        seldNTime.setText(searchVo.noticeBgnde + "~" + searchVo.noticeEndde);
        seldhoumany.setText(searchVo.rcritNmpr);
        seldApplyd.setText(searchVo.appTotal);
        seldBoon.setText(searchVo.srvcClCode);
        seldRecPeriod.setText(searchVo.mnnstNm);
        seldRegist.setText(searchVo.nanmmbyNm);
        seldProgrmCn.setText(searchVo.progrmCn);
    }
    public void onBtnClicked(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://1365.go.kr/vols/P9210/partcptn/timeCptn.do?type=show&progrmRegistNo=" + searchVo.progrmRegistNo));
        startActivity(intent);

        //빌더 선언
        AlertDialog.Builder builder = new AlertDialog.Builder(SelectedVolunteer.this);
        // 제목 설정
        builder.setTitle("봉사활동을 신청하셨습니까?");
        // 요소 설정
        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String vDate = searchVo.progrmBgnde + searchVo.progrmEndde;
                String vTitle = searchVo.progrmSj;
                String vCode = searchVo.progrmRegistNo;
                String vPlace = searchVo.actPlace;
                String vTime = searchVo.actBeginTm + searchVo.actEndTm;
                String Email = getEmailFromDevice(key01);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("schedule").child("student");
                String inputString = Email + "," + vDate + "," + vTitle + "," + vCode + "," + vTime + "," + vPlace;
                myRef.push().setValue(inputString);

                Toast.makeText(getApplicationContext(),"봉사활동이 추가되었습니다",Toast.LENGTH_LONG);

                checkoutSchedule(key01);
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertD = builder.create();
        alertD.show();
    }


    public String getEmailFromDevice(String emailkey){
        Log.d("이메일 가져오기","메서드 실행됨");
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        email = pref.getString(emailkey,"");
        Log.d("이메일 가져오기",email);
        return email;
    }


    public void checkoutSchedule(String emailkey){
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        schedulegetEmail = pref.getString(emailkey,"");
        Log.d("스케쥴 체크아웃",schedulegetEmail);

        /*getData("http://192.168.142.97/PHP_connection_schedules.php");*/
    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            SharedPreferences pref = getSharedPreferences(schedulePreference, MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            int count = 0;
            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                String date = c.getString(TAG_vDate);
                /*Log.d("봉사활동정보 가져오기",date);*/
                String code = c.getString(TAG_vCode);
                /*Log.d("봉사활동정보 가져오기",code);*/
                String id = c.getString(TAG_Email);
                /*Log.d("봉사활동정보 가져오기",id);*/

                if(schedulegetEmail.equals(id)) {
                    editor.putString("date" + count, date);
                    editor.putString("code" + count, code);
                    editor.commit();
                    count++;
                }
            }
            Log.d("봉사활동정보","실행");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

/*
    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldverson, int newVerson) {
            db.execSQL("DROP TABLE IF EXISTS volunteerTBL");
            onCreate(db);
        }

    }
*/

}
