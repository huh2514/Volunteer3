package com.cookandroid.volunteer;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
public class StudentMain extends AppCompatActivity {
    Button SearchVolunteerBtn, mypageBtn, openCalendarBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentmain_layout);

        SearchVolunteerBtn = (Button)findViewById(R.id.SearchVolunteerBtn);
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
        mypageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.MyInformation");
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
    }

}
