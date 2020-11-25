package com.cookandroid.volunteer;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class StudentSignUp extends AppCompatActivity {
    public static Context context_main;
    public SignUpVo vo = new SignUpVo();
    int count;
    boolean isSignUp;
    private static String IP_ADDRESS = "192.168.211.241";
    private static String TAG = "phptest";

    EditText StdSignUpName, StdSignUpPW, StdSignUpEmail;
    Button StdSignUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentsignup_layout);

        context_main = this;
        StdSignUpName = (EditText)findViewById(R.id.StdSignUpName);
        StdSignUpPW = (EditText)findViewById(R.id.StdSignUpPW);
        StdSignUpEmail = (EditText)findViewById(R.id.StdSignUpEmail);
        StdSignUpBtn = (Button)findViewById(R.id.StdSignUpBtn);

        StdSignUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isSignUp = false;
                String name = StdSignUpName.getText().toString();
                String pw = StdSignUpPW.getText().toString();
                String email = StdSignUpEmail.getText().toString();

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("customer").child("student");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(!isSignUp) {
                           /* String counts = snapshot.child("count").getValue().toString();
                            Log.d("파이어베이스 받아온값", counts);
                            count = Integer.parseInt(counts);
                            count++;
                            isSignUp=true;*/
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                myRef.child(name).child("name").setValue(name);
                myRef.child(name).child("pw").setValue(pw);
                myRef.child(name).child("email").setValue(email);

                vo.setEmail(email);
                vo.setName(name);
                vo.setPW(pw);
/*
                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/insertDatas.php", name,pw,email);*/

                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.setTotalTime");
                intent.setComponent(cmpName);
                startActivity(intent);

                Toast.makeText(getApplicationContext(), "입력됨",0).show();
            }
        });
    }




}
