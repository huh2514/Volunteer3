package com.cookandroid.volunteer;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FacilityMain extends AppCompatActivity {
    Button VolunteerSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facilitymain_layout);

        VolunteerSignUp = (Button)findViewById(R.id.VolunteerSignUp);
        VolunteerSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.AddVolunteer");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });
    }
}
