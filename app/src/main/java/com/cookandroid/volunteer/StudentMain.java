package com.cookandroid.volunteer;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.annotation.Target;
import java.net.HttpURLConnection;
import java.net.URL;

public class StudentMain extends AppCompatActivity {
    Button SearchVolunteerBtn, mypageBtn, openCalendarBtn, timeTableBtn;
    TextView targetTime;

    //php 스케쥴 데이터 가져올때
    String myJSON;
    String email;
    String name;
    JSONArray peoples = null;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_vDate = "vDate";
    private static final String TAG_vPlace = "vPlace";
    private static final String TAG_vCode = "vCode";
    private static final String TAG_Email = "Email";

    //백그라운드서비스 실행확인

    // 마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    private long backKeyPressedTime = 0;
    // 첫 번째 뒤로가기 버튼을 누를때 표시
    private Toast toast;


    public final String PREFERENCE = "com.volunteerLogin.samplesharepreference";
    public final String schedulePreference = "com.volunteerLogin.schedulePreference";


    //로그인 정보
    public final String emailkey = "Email";
    public final String namekey = "Name";
    public final String key02 = "pwd";

    //봉사활동 스케쥴 정보
    public final String scheduleKey01 = "Email";
    public String schedulegetEmail="em";
    public final String dateKey = "Date";
    public final String titleKey = "Title";
    public final String codeKey = "Code";
    public final String timeKey = "Time";
    public final String placeKey = "Place";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentmain_layout);

        //background 서비스 시작
        Intent serviceintent = new Intent(StudentMain.this, MyService.class);
        startService(serviceintent);
        /*Intent serviceintent1 = new Intent(StudentMain.this, MyServicegetSchedule.class);*/
        /*startService(serviceintent1);*/

        targetTime = (TextView)findViewById(R.id.yourTarget);
        SearchVolunteerBtn = (Button)findViewById(R.id.SearchVolunteerBtn);
        timeTableBtn = (Button)findViewById((R.id.timeTableBtn));
        openCalendarBtn = (Button)findViewById(R.id.openCalendarBtn);
        mypageBtn = (Button)findViewById(R.id.mypageBtn);
        SearchVolunteerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.SearchInfo");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });
        timeTableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.TimeTable");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });
        mypageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.MyPage");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });
        openCalendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.Calendar");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("schedule").child("student");
        final DatabaseReference targetRef = database.getReference("customer").child("targetTime");

        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        email = pref.getString(emailkey,"");
        name = pref.getString(namekey,"");

        targetRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot data : snapshot.getChildren()) {
                    if(data.getKey().equals(name + email))
                        targetTime.setText(String.valueOf(data.getValue()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                SharedPreferences sPref = getSharedPreferences(schedulePreference, MODE_PRIVATE);
                SharedPreferences.Editor editor = sPref.edit();
                editor.clear();
                editor.commit();
                int count = 1;
                for (DataSnapshot data : snapshot.getChildren()) {
                    String getData[] = data.getValue().toString().split(",");
                    String getEmail = getData[0];
                    String getDate = getData[1];
                    String getTitle = getData[2];
                    String getCode = getData[3];
                    String getTime = getData[4];
                    String getPlace = getData[5];
                    if(getEmail.equals(email)) {
                        editor.putString(emailkey + count, getEmail);
                        editor.commit();
                        editor.putString(dateKey + count, getDate);
                        editor.commit();
                        editor.putString(titleKey + count, getTitle);
                        editor.commit();
                        editor.putString(codeKey + count, getCode);
                        editor.commit();
                        editor.putString(timeKey + count, getTime);
                        editor.commit();
                        editor.putString(placeKey + count, getPlace);
                        editor.commit();
                        count++;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

            // 모든 데이터 삭제
    public void setPreferenceClear(){
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    @Override
    public void onBackPressed() {
        // 기존 뒤로가기 버튼의 기능을 막기위해 주석처리 또는 삭제
        //super.onBackPressed();

        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지났으면 Toast Show
        // 2000 milliseconds = 2 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지나지 않았으면 종료
        // 현재 표시된 Toast 취소
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {

            finishAffinity();
            System.runFinalization();
            System.exit(0);
            finish();
            toast.cancel();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu); //메뉴 XML파일 인플레이션
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_setting:
                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.MyPage");
                intent.setComponent(cmpName);
                startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }
}
