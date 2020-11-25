package com.cookandroid.volunteer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditTotalTime extends AppCompatActivity {
    EditText editTotalTime;
    Button editTotalTimeBtn;

    //셰어드프리퍼런스 로그인 정보 저장
    public final String PREFERENCE = "com.volunteerLogin.samplesharepreference";

    public final String key01 = "Email";
    public final String key02 = "pwd";
    public final String key03 = "Name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_total_time);
        editTotalTime = (EditText)findViewById(R.id.editTotalTime) ;
        editTotalTimeBtn = (Button)findViewById(R.id.editTotalTimeBtn) ;

        editTotalTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
                String email = pref.getString(key01,"");
                String name = pref.getString(key03,"");
                String savePoint = name + email;

                Log.d("시발 왜 안돼냐고",email + name);
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("customer").child("targetTime");
                myRef.child(savePoint).setValue(editTotalTime.getText().toString());

                finish();

            }
        });
    }
}