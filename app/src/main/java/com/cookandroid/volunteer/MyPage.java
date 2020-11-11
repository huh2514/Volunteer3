package com.cookandroid.volunteer;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MyPage extends AppCompatActivity {
    Button logoutBtn;
    public final String PREFERENCE = "com.volunteerLogin.samplesharepreference";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypage);

        logoutBtn = (Button)findViewById(R.id.Logout);

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
    }
    // 모든 데이터 삭제
    public void setPreferenceClear(){
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}
