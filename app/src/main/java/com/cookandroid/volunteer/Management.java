package com.cookandroid.volunteer;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Management extends AppCompatActivity {
    Button ShowStdAccountBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.management_layout);
        ShowStdAccountBtn = (Button)findViewById(R.id.ShowStdAccountBtn);
        ShowStdAccountBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.ShowStdAccount");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });
    }
}
