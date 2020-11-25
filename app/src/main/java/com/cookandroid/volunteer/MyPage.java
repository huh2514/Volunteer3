package com.cookandroid.volunteer;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MyPage extends AppCompatActivity {
    Button logoutBtn, edtTargetTime, showAppliedVolunteerbtn;
    TextView targetTime, NameTotalTime;
    String name, email;
    public final String PREFERENCE = "com.volunteerLogin.samplesharepreference";

    public final String schedulePreference = "com.volunteerLogin.schedulePreference";


    //로그인 정보
    public final String emailkey = "Email";
    public final String namekey = "Name";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        targetTime = (TextView)findViewById(R.id.targetTimes);
        logoutBtn = (Button)findViewById(R.id.Logout);
        NameTotalTime = (TextView)findViewById(R.id.NameTotalTime);
        edtTargetTime = (Button)findViewById(R.id.editTargetTime);
        showAppliedVolunteerbtn = (Button)findViewById(R.id.checkVolunteers) ;

        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        name = pref.getString(namekey,"");
        email = pref.getString(emailkey,"");
        NameTotalTime.setText(name + "님의 총 봉사시간");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference targetRef = database.getReference("customer").child("targetTime");

        targetRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot data : snapshot.getChildren()) {
                    if(data.getKey().equals(name + email))
                    targetTime.setText(String.valueOf(data.getValue()) + "시간");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //로그아웃
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setPreferenceClear();
                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.StdLogin");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });

        edtTargetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.EditTotalTime");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });

        showAppliedVolunteerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.ShowAppliedVolunteer");
                intent.setComponent(cmpName);
                startActivity(intent);

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
}
