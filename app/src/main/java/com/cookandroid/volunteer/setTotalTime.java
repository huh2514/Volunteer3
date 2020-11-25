package com.cookandroid.volunteer;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class setTotalTime extends AppCompatActivity {
    EditText totalTime;
    Button setTotalTimeBtn;
    SignUpVo vo = ((StudentSignUp)StudentSignUp.context_main).vo;
    public final String PREFERENCE = "com.volunteerLogin.samplesharepreference";
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_total_time);


        totalTime = (EditText)findViewById(R.id.setTotalTime);
        setTotalTimeBtn = (Button) findViewById(R.id.setTotalTimeBtn);

        setTotalTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("customer").child("targetTime");
                myRef.child(vo.getName() + vo.getEmail()).setValue(totalTime.getText().toString());

                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.StdLogin");
                intent.setComponent(cmpName);
                startActivity(intent);

            }
        });
    }
}