package com.cookandroid.volunteer;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    Button StdLoginBtn, FacilityLoginButton, Login, SignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        StdLoginBtn = (Button) findViewById(R.id.StdLoginBtn);
        FacilityLoginButton = (Button) findViewById(R.id.FacilityLoginButton);
        Login = (Button) findViewById(R.id.Login);
        SignUp = (Button) findViewById(R.id.SignupBtn);

        StdLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.StdLogin");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });
        FacilityLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.FacilityMain");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.Management");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.SignUp");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });


    }




}
