package com.cookandroid.volunteer;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
public class SignUp extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Button StdSignupBtn;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        StdSignupBtn = (Button)findViewById(R.id.StdSignupBtn);
        StdSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.StudentSignUp");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });
    }
}
