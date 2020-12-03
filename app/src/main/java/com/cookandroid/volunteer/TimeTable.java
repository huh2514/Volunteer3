package com.cookandroid.volunteer;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TimeTable extends AppCompatActivity {

    Button mon1,mon2,mon3,mon4,mon5,mon6,mon7,mon8,mon9,mon10,mon11,mon12,mon13;
    Button tue1,tue2,tue3,tue4,tue5,tue6,tue7,tue8,tue9,tue10,tue11,tue12,tue13;
    Button wed1,wed2,wed3,wed4,wed5,wed6,wed7,wed8,wed9,wed10,wed11,wed12,wed13;
    Button thu1,thu2,thu3,thu4,thu5,thu6,thu7,thu8,thu9,thu10,thu11,thu12,thu13;
    Button fri1,fri2,fri3,fri4,fri5,fri6,fri7,fri8,fri9,fri10,fri11,fri12,fri13;
    Button sat1,sat2,sat3,sat4,sat5,sat6,sat7,sat8,sat9,sat10,sat11,sat12,sat13;
    Button sun1,sun2,sun3,sun4,sun5,sun6,sun7,sun8,sun9,sun10,sun11,sun12,sun13;
    boolean mon1_b, mon2_b=false, mon3_b=false, mon4_b=false, mon5_b=false, mon6_b=false, mon7_b=false, mon8_b=false, mon9_b=false, mon10_b=false, mon11_b=false, mon12_b=false, mon13_b=false;
    boolean tue1_b=false, tue2_b=false, tue3_b=false, tue4_b=false, tue5_b=false, tue6_b=false, tue7_b=false, tue8_b=false, tue9_b=false, tue10_b=false, tue11_b=false, tue12_b=false, tue13_b=false;
    boolean wed1_b=false, wed2_b=false, wed3_b=false, wed4_b=false, wed5_b=false, wed6_b=false, wed7_b=false, wed8_b=false, wed9_b=false, wed10_b=false, wed11_b=false, wed12_b=false, wed13_b=false;
    boolean thu1_b=false, thu2_b=false, thu3_b=false, thu4_b=false, thu5_b=false, thu6_b=false, thu7_b=false, thu8_b=false, thu9_b=false, thu10_b=false, thu11_b=false, thu12_b=false, thu13_b=false;
    boolean fri1_b=false, fri2_b=false, fri3_b=false, fri4_b=false, fri5_b=false, fri6_b=false, fri7_b=false, fri8_b=false, fri9_b=false, fri10_b=false, fri11_b=false, fri12_b=false, fri13_b=false;
    boolean sat1_b=false, sat2_b=false, sat3_b=false, sat4_b=false, sat5_b=false, sat6_b=false, sat7_b=false, sat8_b=false, sat9_b=false, sat10_b=false, sat11_b=false, sat12_b=false, sat13_b=false;
    boolean sun1_b=false, sun2_b=false, sun3_b=false, sun4_b=false, sun5_b=false, sun6_b=false, sun7_b=false, sun8_b=false, sun9_b=false, sun10_b=false, sun11_b=false, sun12_b=false, sun13_b=false;

    Button timeTableSaveBtn, tableResetBtn;
    public final String PREFERENCE = "com.volunteerLogin.samplesharepreference";


    public final String emailkey = "Email";
    public final String namekey = "Name";
    String name, email;
    String NameEmail;
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.time_table);

        mon1 = (Button)findViewById(R.id.mon1);mon2 = (Button)findViewById(R.id.mon2);mon3 = (Button)findViewById(R.id.mon3);mon4 = (Button)findViewById(R.id.mon4);mon5 = (Button)findViewById(R.id.mon5);mon6 = (Button)findViewById(R.id.mon6);mon7 = (Button)findViewById(R.id.mon7);mon8 = (Button)findViewById(R.id.mon8);mon9 = (Button)findViewById(R.id.mon9);mon10 = (Button)findViewById(R.id.mon10);mon11 = (Button)findViewById(R.id.mon11);mon12 = (Button)findViewById(R.id.mon12);mon13 = (Button)findViewById(R.id.mon13);
        tue1 = (Button)findViewById(R.id.tue1);tue2 = (Button)findViewById(R.id.tue2);tue3 = (Button)findViewById(R.id.tue3);tue4 = (Button)findViewById(R.id.tue4);tue5 = (Button)findViewById(R.id.tue5);tue6 = (Button)findViewById(R.id.tue6);tue7 = (Button)findViewById(R.id.tue7);tue8 = (Button)findViewById(R.id.tue8);tue9 = (Button)findViewById(R.id.tue9);tue10 = (Button)findViewById(R.id.tue10);tue11 = (Button)findViewById(R.id.tue11);tue12 = (Button)findViewById(R.id.tue12);tue13 = (Button)findViewById(R.id.tue13);
        wed1 = (Button)findViewById(R.id.wed1);wed2 = (Button)findViewById(R.id.wed2);wed3 = (Button)findViewById(R.id.wed3);wed4 = (Button)findViewById(R.id.wed4);wed5 = (Button)findViewById(R.id.wed5);wed6 = (Button)findViewById(R.id.wed6);wed7 = (Button)findViewById(R.id.wed7);wed8 = (Button)findViewById(R.id.wed8);wed9 = (Button)findViewById(R.id.wed9);wed10 = (Button)findViewById(R.id.wed10);wed11 = (Button)findViewById(R.id.wed11);wed12 = (Button)findViewById(R.id.wed12);wed13 = (Button)findViewById(R.id.wed13);
        thu1 = (Button)findViewById(R.id.thu1);thu2 = (Button)findViewById(R.id.thu2);thu3 = (Button)findViewById(R.id.thu3);thu4 = (Button)findViewById(R.id.thu4);thu5 = (Button)findViewById(R.id.thu5);thu6 = (Button)findViewById(R.id.thu6);thu7 = (Button)findViewById(R.id.thu7);thu8 = (Button)findViewById(R.id.thu8);thu9 = (Button)findViewById(R.id.thu9);thu10 = (Button)findViewById(R.id.thu10);thu11 = (Button)findViewById(R.id.thu11);thu12 = (Button)findViewById(R.id.thu12);thu13 = (Button)findViewById(R.id.thu13);
        fri1 = (Button)findViewById(R.id.fri1);fri2 = (Button)findViewById(R.id.fri2);fri3 = (Button)findViewById(R.id.fri3);fri4 = (Button)findViewById(R.id.fri4);fri5 = (Button)findViewById(R.id.fri5);fri6 = (Button)findViewById(R.id.fri6);fri7 = (Button)findViewById(R.id.fri7);fri8 = (Button)findViewById(R.id.fri8);fri9 = (Button)findViewById(R.id.fri9);fri10 = (Button)findViewById(R.id.fri10);fri11 = (Button)findViewById(R.id.fri11);fri12 = (Button)findViewById(R.id.fri12);fri13 = (Button)findViewById(R.id.fri13);
        sat1 = (Button)findViewById(R.id.sat1);sat2 = (Button)findViewById(R.id.sat2);sat3 = (Button)findViewById(R.id.sat3);sat4 = (Button)findViewById(R.id.sat4);sat5 = (Button)findViewById(R.id.sat5);sat6 = (Button)findViewById(R.id.sat6);sat7 = (Button)findViewById(R.id.sat7);sat8 = (Button)findViewById(R.id.sat8);sat9 = (Button)findViewById(R.id.sat9);sat10 = (Button)findViewById(R.id.sat10);sat11 = (Button)findViewById(R.id.sat11);sat12 = (Button)findViewById(R.id.sat12);sat13 = (Button)findViewById(R.id.sat13);
        sun1 = (Button)findViewById(R.id.sun1);sun2 = (Button)findViewById(R.id.sun2);sun3 = (Button)findViewById(R.id.sun3);sun4 = (Button)findViewById(R.id.sun4);sun5 = (Button)findViewById(R.id.sun5);sun6 = (Button)findViewById(R.id.sun6);sun7 = (Button)findViewById(R.id.sun7);sun8 = (Button)findViewById(R.id.sun8);sun9 = (Button)findViewById(R.id.sun9);sun10 = (Button)findViewById(R.id.sun10);sun11 = (Button)findViewById(R.id.sun11);sun12 = (Button)findViewById(R.id.sun12);sun13 = (Button)findViewById(R.id.sun13);

        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        email = pref.getString(emailkey,"");
        name = pref.getString(namekey,"");
        NameEmail = name+email;
        Log.d("",name+email);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference timeTableRef = database.getReference("customer").child("timeTable").child(NameEmail);


        timeTableRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot weekData : snapshot.getChildren()) {

                    if(weekData.getKey().equals("mon1")) { mon1_b = Boolean.parseBoolean(weekData.getValue().toString()); mon1.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("mon2")) { mon2_b = Boolean.parseBoolean(weekData.getValue().toString()); mon2.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("mon3")) { mon3_b = Boolean.parseBoolean(weekData.getValue().toString()); mon3.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("mon4")) { mon4_b = Boolean.parseBoolean(weekData.getValue().toString()); mon4.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("mon5")) { mon5_b = Boolean.parseBoolean(weekData.getValue().toString()); mon5.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("mon6")) { mon6_b = Boolean.parseBoolean(weekData.getValue().toString()); mon6.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("mon7")) { mon7_b = Boolean.parseBoolean(weekData.getValue().toString()); mon7.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("mon8")) { mon8_b = Boolean.parseBoolean(weekData.getValue().toString()); mon8.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("mon9")) { mon9_b = Boolean.parseBoolean(weekData.getValue().toString()); mon9.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("mon10")) { mon10_b = Boolean.parseBoolean(weekData.getValue().toString()); mon10.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("mon11")) { mon11_b = Boolean.parseBoolean(weekData.getValue().toString()); mon11.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("mon12")) { mon12_b = Boolean.parseBoolean(weekData.getValue().toString()); mon12.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("mon13")) { mon13_b = Boolean.parseBoolean(weekData.getValue().toString()); mon13.setBackgroundColor(Color.LTGRAY); }

                    if(weekData.getKey().equals("tue1")) { tue1_b = Boolean.parseBoolean(weekData.getValue().toString()); tue1.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("tue2")) { tue2_b = Boolean.parseBoolean(weekData.getValue().toString()); tue2.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("tue3")) { tue3_b = Boolean.parseBoolean(weekData.getValue().toString()); tue3.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("tue4")) { tue4_b = Boolean.parseBoolean(weekData.getValue().toString()); tue4.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("tue5")) { tue5_b = Boolean.parseBoolean(weekData.getValue().toString()); tue5.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("tue6")) { tue6_b = Boolean.parseBoolean(weekData.getValue().toString()); tue6.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("tue7")) { tue7_b = Boolean.parseBoolean(weekData.getValue().toString()); tue7.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("tue8")) { tue8_b = Boolean.parseBoolean(weekData.getValue().toString()); tue8.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("tue9")) { tue9_b = Boolean.parseBoolean(weekData.getValue().toString()); tue9.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("tue10")) { tue10_b = Boolean.parseBoolean(weekData.getValue().toString()); tue10.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("tue11")) { tue11_b = Boolean.parseBoolean(weekData.getValue().toString()); tue11.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("tue12")) { tue12_b = Boolean.parseBoolean(weekData.getValue().toString()); tue12.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("tue13")) { tue13_b = Boolean.parseBoolean(weekData.getValue().toString()); tue13.setBackgroundColor(Color.LTGRAY); }

                    if(weekData.getKey().equals("wed1")) { wed1_b = Boolean.parseBoolean(weekData.getValue().toString()); wed1.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("wed2")) { wed2_b = Boolean.parseBoolean(weekData.getValue().toString()); wed2.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("wed3")) { wed3_b = Boolean.parseBoolean(weekData.getValue().toString()); wed3.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("wed4")) { wed4_b = Boolean.parseBoolean(weekData.getValue().toString()); wed4.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("wed5")) { wed5_b = Boolean.parseBoolean(weekData.getValue().toString()); wed5.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("wed6")) { wed6_b = Boolean.parseBoolean(weekData.getValue().toString()); wed6.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("wed7")) { wed7_b = Boolean.parseBoolean(weekData.getValue().toString()); wed7.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("wed8")) { wed8_b = Boolean.parseBoolean(weekData.getValue().toString()); wed8.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("wed9")) { wed9_b = Boolean.parseBoolean(weekData.getValue().toString()); wed9.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("wed10")) { wed10_b = Boolean.parseBoolean(weekData.getValue().toString()); wed10.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("wed11")) { wed11_b = Boolean.parseBoolean(weekData.getValue().toString()); wed11.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("wed12")) { wed12_b = Boolean.parseBoolean(weekData.getValue().toString()); wed12.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("wed13")) { wed13_b = Boolean.parseBoolean(weekData.getValue().toString()); wed13.setBackgroundColor(Color.LTGRAY); }

                    if(weekData.getKey().equals("thu1")) { thu1_b = Boolean.parseBoolean(weekData.getValue().toString()); thu1.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("thu2")) { thu2_b = Boolean.parseBoolean(weekData.getValue().toString()); thu2.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("thu3")) { thu3_b = Boolean.parseBoolean(weekData.getValue().toString()); thu3.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("thu4")) { thu4_b = Boolean.parseBoolean(weekData.getValue().toString()); thu4.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("thu5")) { thu5_b = Boolean.parseBoolean(weekData.getValue().toString()); thu5.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("thu6")) { thu6_b = Boolean.parseBoolean(weekData.getValue().toString()); thu6.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("thu7")) { thu7_b = Boolean.parseBoolean(weekData.getValue().toString()); thu7.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("thu8")) { thu8_b = Boolean.parseBoolean(weekData.getValue().toString()); thu8.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("thu9")) { thu9_b = Boolean.parseBoolean(weekData.getValue().toString()); thu9.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("thu10")) { thu10_b = Boolean.parseBoolean(weekData.getValue().toString()); thu10.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("thu11")) { thu11_b = Boolean.parseBoolean(weekData.getValue().toString()); thu11.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("thu12")) { thu12_b = Boolean.parseBoolean(weekData.getValue().toString()); thu12.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("thu13")) { thu13_b = Boolean.parseBoolean(weekData.getValue().toString()); thu13.setBackgroundColor(Color.LTGRAY); }

                    if(weekData.getKey().equals("fri1")) { fri1_b = Boolean.parseBoolean(weekData.getValue().toString()); fri1.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("fri2")) { fri2_b = Boolean.parseBoolean(weekData.getValue().toString()); fri2.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("fri3")) { fri3_b = Boolean.parseBoolean(weekData.getValue().toString()); fri3.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("fri4")) { fri4_b = Boolean.parseBoolean(weekData.getValue().toString()); fri4.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("fri5")) { fri5_b = Boolean.parseBoolean(weekData.getValue().toString()); fri5.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("fri6")) { fri6_b = Boolean.parseBoolean(weekData.getValue().toString()); fri6.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("fri7")) { fri7_b = Boolean.parseBoolean(weekData.getValue().toString()); fri7.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("fri8")) { fri8_b = Boolean.parseBoolean(weekData.getValue().toString()); fri8.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("fri9")) { fri9_b = Boolean.parseBoolean(weekData.getValue().toString()); fri9.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("fri10")) { fri10_b = Boolean.parseBoolean(weekData.getValue().toString()); fri10.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("fri11")) { fri11_b = Boolean.parseBoolean(weekData.getValue().toString()); fri11.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("fri12")) { fri12_b = Boolean.parseBoolean(weekData.getValue().toString()); fri12.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("fri13")) { fri13_b = Boolean.parseBoolean(weekData.getValue().toString()); fri13.setBackgroundColor(Color.LTGRAY); }

                    if(weekData.getKey().equals("sat1")) { sat1_b = Boolean.parseBoolean(weekData.getValue().toString()); sat1.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sat2")) { sat2_b = Boolean.parseBoolean(weekData.getValue().toString()); sat2.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sat3")) { sat3_b = Boolean.parseBoolean(weekData.getValue().toString()); sat3.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sat4")) { sat4_b = Boolean.parseBoolean(weekData.getValue().toString()); sat4.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sat5")) { sat5_b = Boolean.parseBoolean(weekData.getValue().toString()); sat5.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sat6")) { sat6_b = Boolean.parseBoolean(weekData.getValue().toString()); sat6.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sat7")) { sat7_b = Boolean.parseBoolean(weekData.getValue().toString()); sat7.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sat8")) { sat8_b = Boolean.parseBoolean(weekData.getValue().toString()); sat8.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sat9")) { sat9_b = Boolean.parseBoolean(weekData.getValue().toString()); sat9.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sat10")) { sat10_b = Boolean.parseBoolean(weekData.getValue().toString()); sat10.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sat11")) { sat11_b = Boolean.parseBoolean(weekData.getValue().toString()); sat11.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sat12")) { sat12_b = Boolean.parseBoolean(weekData.getValue().toString()); sat12.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sat13")) { sat13_b = Boolean.parseBoolean(weekData.getValue().toString()); sat13.setBackgroundColor(Color.LTGRAY); }

                    if(weekData.getKey().equals("sun1")) { sun1_b = Boolean.parseBoolean(weekData.getValue().toString()); sun1.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sun2")) { sun2_b = Boolean.parseBoolean(weekData.getValue().toString()); sun2.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sun3")) { sun3_b = Boolean.parseBoolean(weekData.getValue().toString()); sun3.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sun4")) { sun4_b = Boolean.parseBoolean(weekData.getValue().toString()); sun4.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sun5")) { sun5_b = Boolean.parseBoolean(weekData.getValue().toString()); sun5.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sun6")) { sun6_b = Boolean.parseBoolean(weekData.getValue().toString()); sun6.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sun7")) { sun7_b = Boolean.parseBoolean(weekData.getValue().toString()); sun7.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sun8")) { sun8_b = Boolean.parseBoolean(weekData.getValue().toString()); sun8.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sun9")) { sun9_b = Boolean.parseBoolean(weekData.getValue().toString()); sun9.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sun10")) { sun10_b = Boolean.parseBoolean(weekData.getValue().toString()); sun10.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sun11")) { sun11_b = Boolean.parseBoolean(weekData.getValue().toString()); sun11.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sun12")) { sun12_b = Boolean.parseBoolean(weekData.getValue().toString()); sun12.setBackgroundColor(Color.LTGRAY); }
                    if(weekData.getKey().equals("sun13")) { sun13_b = Boolean.parseBoolean(weekData.getValue().toString()); sun13.setBackgroundColor(Color.LTGRAY); }


                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        String a = String.valueOf(mon1_b);
        //Log.d("",a);




        timeTableSaveBtn = (Button)findViewById(R.id.timeTableSaveBtn);
        tableResetBtn = (Button)findViewById(R.id.tableResetBtn);

        tableResetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mon1_b=false; mon1.setBackgroundColor(Color.WHITE);
                mon2_b=false; mon2.setBackgroundColor(Color.WHITE);
                mon3_b=false; mon3.setBackgroundColor(Color.WHITE);
                mon4_b=false; mon4.setBackgroundColor(Color.WHITE);
                mon5_b=false; mon5.setBackgroundColor(Color.WHITE);
                mon6_b=false; mon6.setBackgroundColor(Color.WHITE);
                mon7_b=false; mon7.setBackgroundColor(Color.WHITE);
                mon8_b=false; mon8.setBackgroundColor(Color.WHITE);
                mon9_b=false; mon9.setBackgroundColor(Color.WHITE);
                mon10_b=false; mon10.setBackgroundColor(Color.WHITE);
                mon11_b=false; mon11.setBackgroundColor(Color.WHITE);
                mon12_b=false; mon12.setBackgroundColor(Color.WHITE);
                mon13_b=false; mon13.setBackgroundColor(Color.WHITE);

                tue1_b=false; tue1.setBackgroundColor(Color.WHITE);
                tue2_b=false; tue2.setBackgroundColor(Color.WHITE);
                tue3_b=false; tue3.setBackgroundColor(Color.WHITE);
                tue4_b=false; tue4.setBackgroundColor(Color.WHITE);
                tue5_b=false; tue5.setBackgroundColor(Color.WHITE);
                tue6_b=false; tue6.setBackgroundColor(Color.WHITE);
                tue7_b=false; tue7.setBackgroundColor(Color.WHITE);
                tue8_b=false; tue8.setBackgroundColor(Color.WHITE);
                tue9_b=false; tue9.setBackgroundColor(Color.WHITE);
                tue10_b=false; tue10.setBackgroundColor(Color.WHITE);
                tue11_b=false; tue11.setBackgroundColor(Color.WHITE);
                tue12_b=false; tue12.setBackgroundColor(Color.WHITE);
                tue13_b=false; tue13.setBackgroundColor(Color.WHITE);

                wed1_b=false; wed1.setBackgroundColor(Color.WHITE);
                wed2_b=false; wed2.setBackgroundColor(Color.WHITE);
                wed3_b=false; wed3.setBackgroundColor(Color.WHITE);
                wed4_b=false; wed4.setBackgroundColor(Color.WHITE);
                wed5_b=false; wed5.setBackgroundColor(Color.WHITE);
                wed6_b=false; wed6.setBackgroundColor(Color.WHITE);
                wed7_b=false; wed7.setBackgroundColor(Color.WHITE);
                wed8_b=false; wed8.setBackgroundColor(Color.WHITE);
                wed9_b=false; wed9.setBackgroundColor(Color.WHITE);
                wed10_b=false; wed10.setBackgroundColor(Color.WHITE);
                wed11_b=false; wed11.setBackgroundColor(Color.WHITE);
                wed12_b=false; wed12.setBackgroundColor(Color.WHITE);
                wed13_b=false; wed13.setBackgroundColor(Color.WHITE);

                thu1_b=false; thu1.setBackgroundColor(Color.WHITE);
                thu2_b=false; thu2.setBackgroundColor(Color.WHITE);
                thu3_b=false; thu3.setBackgroundColor(Color.WHITE);
                thu4_b=false; thu4.setBackgroundColor(Color.WHITE);
                thu5_b=false; thu5.setBackgroundColor(Color.WHITE);
                thu6_b=false; thu6.setBackgroundColor(Color.WHITE);
                thu7_b=false; thu7.setBackgroundColor(Color.WHITE);
                thu8_b=false; thu8.setBackgroundColor(Color.WHITE);
                thu9_b=false; thu9.setBackgroundColor(Color.WHITE);
                thu10_b=false; thu10.setBackgroundColor(Color.WHITE);
                thu11_b=false; thu11.setBackgroundColor(Color.WHITE);
                thu12_b=false; thu12.setBackgroundColor(Color.WHITE);
                thu13_b=false; thu13.setBackgroundColor(Color.WHITE);

                fri1_b=false; fri1.setBackgroundColor(Color.WHITE);
                fri2_b=false; fri2.setBackgroundColor(Color.WHITE);
                fri3_b=false; fri3.setBackgroundColor(Color.WHITE);
                fri4_b=false; fri4.setBackgroundColor(Color.WHITE);
                fri5_b=false; fri5.setBackgroundColor(Color.WHITE);
                fri6_b=false; fri6.setBackgroundColor(Color.WHITE);
                fri7_b=false; fri7.setBackgroundColor(Color.WHITE);
                fri8_b=false; fri8.setBackgroundColor(Color.WHITE);
                fri9_b=false; fri9.setBackgroundColor(Color.WHITE);
                fri10_b=false; fri10.setBackgroundColor(Color.WHITE);
                fri11_b=false; fri11.setBackgroundColor(Color.WHITE);
                fri12_b=false; fri12.setBackgroundColor(Color.WHITE);
                fri13_b=false; fri13.setBackgroundColor(Color.WHITE);

                sat1_b=false; sat1.setBackgroundColor(Color.WHITE);
                sat2_b=false; sat2.setBackgroundColor(Color.WHITE);
                sat3_b=false; sat3.setBackgroundColor(Color.WHITE);
                sat4_b=false; sat4.setBackgroundColor(Color.WHITE);
                sat5_b=false; sat5.setBackgroundColor(Color.WHITE);
                sat6_b=false; sat6.setBackgroundColor(Color.WHITE);
                sat7_b=false; sat7.setBackgroundColor(Color.WHITE);
                sat8_b=false; sat8.setBackgroundColor(Color.WHITE);
                sat9_b=false; sat9.setBackgroundColor(Color.WHITE);
                sat10_b=false; sat10.setBackgroundColor(Color.WHITE);
                sat11_b=false; sat11.setBackgroundColor(Color.WHITE);
                sat12_b=false; sat12.setBackgroundColor(Color.WHITE);
                sat13_b=false; sat13.setBackgroundColor(Color.WHITE);

                sun1_b=false; sun1.setBackgroundColor(Color.WHITE);
                sun2_b=false; sun2.setBackgroundColor(Color.WHITE);
                sun3_b=false; sun3.setBackgroundColor(Color.WHITE);
                sun4_b=false; sun4.setBackgroundColor(Color.WHITE);
                sun5_b=false; sun5.setBackgroundColor(Color.WHITE);
                sun6_b=false; sun6.setBackgroundColor(Color.WHITE);
                sun7_b=false; sun7.setBackgroundColor(Color.WHITE);
                sun8_b=false; sun8.setBackgroundColor(Color.WHITE);
                sun9_b=false; sun9.setBackgroundColor(Color.WHITE);
                sun10_b=false; sun10.setBackgroundColor(Color.WHITE);
                sun11_b=false; sun11.setBackgroundColor(Color.WHITE);
                sun12_b=false; sun12.setBackgroundColor(Color.WHITE);
                sun13_b=false; sun13.setBackgroundColor(Color.WHITE);
            }
        });
        timeTableSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("customer").child("timeTable").child(NameEmail);
                if(mon1_b==true) myRef.child("mon1").setValue(true);else myRef.child("mon1").removeValue();
                if(mon2_b==true) myRef.child("mon2").setValue(true);else myRef.child("mon2").removeValue();
                if(mon3_b==true) myRef.child("mon3").setValue(true);else myRef.child("mon3").removeValue();
                if(mon4_b==true) myRef.child("mon4").setValue(true);else myRef.child("mon4").removeValue();
                if(mon5_b==true) myRef.child("mon5").setValue(true);else myRef.child("mon5").removeValue();
                if(mon6_b==true) myRef.child("mon6").setValue(true);else myRef.child("mon6").removeValue();
                if(mon7_b==true) myRef.child("mon7").setValue(true);else myRef.child("mon7").removeValue();
                if(mon8_b==true) myRef.child("mon8").setValue(true);else myRef.child("mon8").removeValue();
                if(mon9_b==true) myRef.child("mon9").setValue(true);else myRef.child("mon9").removeValue();
                if(mon10_b==true) myRef.child("mon10").setValue(true);else myRef.child("mon10").removeValue();
                if(mon11_b==true) myRef.child("mon11").setValue(true);else myRef.child("mon11").removeValue();
                if(mon12_b==true) myRef.child("mon12").setValue(true);else myRef.child("mon12").removeValue();
                if(mon13_b==true) myRef.child("mon13").setValue(true);else myRef.child("mon13").removeValue();

                if(tue1_b==true) myRef.child("tue1").setValue(true);else myRef.child("tue1").removeValue();
                if(tue2_b==true) myRef.child("tue2").setValue(true);else myRef.child("tue2").removeValue();
                if(tue3_b==true) myRef.child("tue3").setValue(true);else myRef.child("tue3").removeValue();
                if(tue4_b==true) myRef.child("tue4").setValue(true);else myRef.child("tue4").removeValue();
                if(tue5_b==true) myRef.child("tue5").setValue(true);else myRef.child("tue5").removeValue();
                if(tue6_b==true) myRef.child("tue6").setValue(true);else myRef.child("tue6").removeValue();
                if(tue7_b==true) myRef.child("tue7").setValue(true);else myRef.child("tue7").removeValue();
                if(tue8_b==true) myRef.child("tue8").setValue(true);else myRef.child("tue8").removeValue();
                if(tue9_b==true) myRef.child("tue9").setValue(true);else myRef.child("tue9").removeValue();
                if(tue10_b==true) myRef.child("tue10").setValue(true);else myRef.child("tue10").removeValue();
                if(tue11_b==true) myRef.child("tue11").setValue(true);else myRef.child("tue11").removeValue();
                if(tue12_b==true) myRef.child("tue12").setValue(true);else myRef.child("tue12").removeValue();
                if(tue13_b==true) myRef.child("tue13").setValue(true);else myRef.child("tue13").removeValue();

                if(wed1_b==true) myRef.child("wed1").setValue(true);else myRef.child("wed1").removeValue();
                if(wed2_b==true) myRef.child("wed2").setValue(true);else myRef.child("wed2").removeValue();
                if(wed3_b==true) myRef.child("wed3").setValue(true);else myRef.child("wed3").removeValue();
                if(wed4_b==true) myRef.child("wed4").setValue(true);else myRef.child("wed4").removeValue();
                if(wed5_b==true) myRef.child("wed5").setValue(true);else myRef.child("wed5").removeValue();
                if(wed6_b==true) myRef.child("wed6").setValue(true);else myRef.child("wed6").removeValue();
                if(wed7_b==true) myRef.child("wed7").setValue(true);else myRef.child("wed7").removeValue();
                if(wed8_b==true) myRef.child("wed8").setValue(true);else myRef.child("wed8").removeValue();
                if(wed9_b==true) myRef.child("wed9").setValue(true);else myRef.child("wed9").removeValue();
                if(wed10_b==true) myRef.child("wed10").setValue(true);else myRef.child("wed10").removeValue();
                if(wed11_b==true) myRef.child("wed11").setValue(true);else myRef.child("wed11").removeValue();
                if(wed12_b==true) myRef.child("wed12").setValue(true);else myRef.child("wed12").removeValue();
                if(wed13_b==true) myRef.child("wed13").setValue(true);else myRef.child("wed13").removeValue();

                if(thu1_b==true) myRef.child("thu1").setValue(true);else myRef.child("thu1").removeValue();
                if(thu2_b==true) myRef.child("thu2").setValue(true);else myRef.child("thu2").removeValue();
                if(thu3_b==true) myRef.child("thu3").setValue(true);else myRef.child("thu3").removeValue();
                if(thu4_b==true) myRef.child("thu4").setValue(true);else myRef.child("thu4").removeValue();
                if(thu5_b==true) myRef.child("thu5").setValue(true);else myRef.child("thu5").removeValue();
                if(thu6_b==true) myRef.child("thu6").setValue(true);else myRef.child("thu6").removeValue();
                if(thu7_b==true) myRef.child("thu7").setValue(true);else myRef.child("thu7").removeValue();
                if(thu8_b==true) myRef.child("thu8").setValue(true);else myRef.child("thu8").removeValue();
                if(thu9_b==true) myRef.child("thu9").setValue(true);else myRef.child("thu9").removeValue();
                if(thu10_b==true) myRef.child("thu10").setValue(true);else myRef.child("thu10").removeValue();
                if(thu11_b==true) myRef.child("thu11").setValue(true);else myRef.child("thu11").removeValue();
                if(thu12_b==true) myRef.child("thu12").setValue(true);else myRef.child("thu12").removeValue();
                if(thu13_b==true) myRef.child("thu13").setValue(true);else myRef.child("thu13").removeValue();

                if(fri1_b==true) myRef.child("fri1").setValue(true);else myRef.child("fri1").removeValue();
                if(fri2_b==true) myRef.child("fri2").setValue(true);else myRef.child("fri2").removeValue();
                if(fri3_b==true) myRef.child("fri3").setValue(true);else myRef.child("fri3").removeValue();
                if(fri4_b==true) myRef.child("fri4").setValue(true);else myRef.child("fri4").removeValue();
                if(fri5_b==true) myRef.child("fri5").setValue(true);else myRef.child("fri5").removeValue();
                if(fri6_b==true) myRef.child("fri6").setValue(true);else myRef.child("fri6").removeValue();
                if(fri7_b==true) myRef.child("fri7").setValue(true);else myRef.child("fri7").removeValue();
                if(fri8_b==true) myRef.child("fri8").setValue(true);else myRef.child("fri8").removeValue();
                if(fri9_b==true) myRef.child("fri9").setValue(true);else myRef.child("fri9").removeValue();
                if(fri10_b==true) myRef.child("fri10").setValue(true);else myRef.child("fri10").removeValue();
                if(fri11_b==true) myRef.child("fri11").setValue(true);else myRef.child("fri11").removeValue();
                if(fri12_b==true) myRef.child("fri12").setValue(true);else myRef.child("fri12").removeValue();
                if(fri13_b==true) myRef.child("fri13").setValue(true);else myRef.child("fri13").removeValue();

                if(sat1_b==true) myRef.child("sat1").setValue(true);else myRef.child("sat1").removeValue();
                if(sat2_b==true) myRef.child("sat2").setValue(true);else myRef.child("sat2").removeValue();
                if(sat3_b==true) myRef.child("sat3").setValue(true);else myRef.child("sat3").removeValue();
                if(sat4_b==true) myRef.child("sat4").setValue(true);else myRef.child("sat4").removeValue();
                if(sat5_b==true) myRef.child("sat5").setValue(true);else myRef.child("sat5").removeValue();
                if(sat6_b==true) myRef.child("sat6").setValue(true);else myRef.child("sat6").removeValue();
                if(sat7_b==true) myRef.child("sat7").setValue(true);else myRef.child("sat7").removeValue();
                if(sat8_b==true) myRef.child("sat8").setValue(true);else myRef.child("sat8").removeValue();
                if(sat9_b==true) myRef.child("sat9").setValue(true);else myRef.child("sat9").removeValue();
                if(sat10_b==true) myRef.child("sat10").setValue(true);else myRef.child("sat10").removeValue();
                if(sat11_b==true) myRef.child("sat11").setValue(true);else myRef.child("sat11").removeValue();
                if(sat12_b==true) myRef.child("sat12").setValue(true);else myRef.child("sat12").removeValue();
                if(sat13_b==true) myRef.child("sat13").setValue(true);else myRef.child("sat13").removeValue();

                if(sun1_b==true) myRef.child("sun1").setValue(true);else myRef.child("sun1").removeValue();
                if(sun2_b==true) myRef.child("sun2").setValue(true);else myRef.child("sun2").removeValue();
                if(sun3_b==true) myRef.child("sun3").setValue(true);else myRef.child("sun3").removeValue();
                if(sun4_b==true) myRef.child("sun4").setValue(true);else myRef.child("sun4").removeValue();
                if(sun5_b==true) myRef.child("sun5").setValue(true);else myRef.child("sun5").removeValue();
                if(sun6_b==true) myRef.child("sun6").setValue(true);else myRef.child("sun6").removeValue();
                if(sun7_b==true) myRef.child("sun7").setValue(true);else myRef.child("sun7").removeValue();
                if(sun8_b==true) myRef.child("sun8").setValue(true);else myRef.child("sun8").removeValue();
                if(sun9_b==true) myRef.child("sun9").setValue(true);else myRef.child("sun9").removeValue();
                if(sun10_b==true) myRef.child("sun10").setValue(true);else myRef.child("sun10").removeValue();
                if(sun11_b==true) myRef.child("sun11").setValue(true);else myRef.child("sun11").removeValue();
                if(sun12_b==true) myRef.child("sun12").setValue(true);else myRef.child("sun12").removeValue();
                if(sun13_b==true) myRef.child("sun13").setValue(true);else myRef.child("sun13").removeValue();


                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.StudentMain");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });

        if(mon1_b==false){ mon1.setBackgroundColor(Color.WHITE); }else { mon1.setBackgroundColor(Color.LTGRAY);}
        if(mon2_b==false){ mon2.setBackgroundColor(Color.WHITE); }else { mon2.setBackgroundColor(Color.LTGRAY);}
        if(mon3_b==false){ mon3.setBackgroundColor(Color.WHITE); }else { mon3.setBackgroundColor(Color.LTGRAY);}
        if(mon4_b==false){ mon4.setBackgroundColor(Color.WHITE); }else { mon4.setBackgroundColor(Color.LTGRAY);}
        if(mon5_b==false){ mon5.setBackgroundColor(Color.WHITE); }else { mon5.setBackgroundColor(Color.LTGRAY);}
        if(mon6_b==false){ mon6.setBackgroundColor(Color.WHITE); }else { mon6.setBackgroundColor(Color.LTGRAY);}
        if(mon7_b==false){ mon7.setBackgroundColor(Color.WHITE); }else { mon7.setBackgroundColor(Color.LTGRAY);}
        if(mon8_b==false){ mon8.setBackgroundColor(Color.WHITE); }else { mon8.setBackgroundColor(Color.LTGRAY);}
        if(mon9_b==false){ mon9.setBackgroundColor(Color.WHITE); }else { mon9.setBackgroundColor(Color.LTGRAY);}
        if(mon10_b==false){ mon10.setBackgroundColor(Color.WHITE); }else { mon10.setBackgroundColor(Color.LTGRAY);}
        if(mon11_b==false){ mon11.setBackgroundColor(Color.WHITE); }else { mon11.setBackgroundColor(Color.LTGRAY);}
        if(mon12_b==false){ mon12.setBackgroundColor(Color.WHITE); }else { mon12.setBackgroundColor(Color.LTGRAY);}
        if(mon13_b==false){ mon13.setBackgroundColor(Color.WHITE); }else { mon13.setBackgroundColor(Color.LTGRAY);}

        if(tue1_b==false){ tue1.setBackgroundColor(Color.WHITE); }else { tue1.setBackgroundColor(Color.LTGRAY);}
        if(tue2_b==false){ tue2.setBackgroundColor(Color.WHITE); }else { tue2.setBackgroundColor(Color.LTGRAY);}
        if(tue3_b==false){ tue3.setBackgroundColor(Color.WHITE); }else { tue3.setBackgroundColor(Color.LTGRAY);}
        if(tue4_b==false){ tue4.setBackgroundColor(Color.WHITE); }else { tue4.setBackgroundColor(Color.LTGRAY);}
        if(tue5_b==false){ tue5.setBackgroundColor(Color.WHITE); }else { tue5.setBackgroundColor(Color.LTGRAY);}
        if(tue6_b==false){ tue6.setBackgroundColor(Color.WHITE); }else { tue6.setBackgroundColor(Color.LTGRAY);}
        if(tue7_b==false){ tue7.setBackgroundColor(Color.WHITE); }else { tue7.setBackgroundColor(Color.LTGRAY);}
        if(tue8_b==false){ tue8.setBackgroundColor(Color.WHITE); }else { tue8.setBackgroundColor(Color.LTGRAY);}
        if(tue9_b==false){ tue9.setBackgroundColor(Color.WHITE); }else { tue9.setBackgroundColor(Color.LTGRAY);}
        if(tue10_b==false){ tue10.setBackgroundColor(Color.WHITE); }else { tue10.setBackgroundColor(Color.LTGRAY);}
        if(tue11_b==false){ tue11.setBackgroundColor(Color.WHITE); }else { tue11.setBackgroundColor(Color.LTGRAY);}
        if(tue12_b==false){ tue12.setBackgroundColor(Color.WHITE); }else { tue12.setBackgroundColor(Color.LTGRAY);}
        if(tue13_b==false){ tue13.setBackgroundColor(Color.WHITE); }else { tue13.setBackgroundColor(Color.LTGRAY);}

        if(wed1_b==false){ wed1.setBackgroundColor(Color.WHITE); }else { wed1.setBackgroundColor(Color.LTGRAY);}
        if(wed2_b==false){ wed2.setBackgroundColor(Color.WHITE); }else { wed2.setBackgroundColor(Color.LTGRAY);}
        if(wed3_b==false){ wed3.setBackgroundColor(Color.WHITE); }else { wed3.setBackgroundColor(Color.LTGRAY);}
        if(wed4_b==false){ wed4.setBackgroundColor(Color.WHITE); }else { wed4.setBackgroundColor(Color.LTGRAY);}
        if(wed5_b==false){ wed5.setBackgroundColor(Color.WHITE); }else { wed5.setBackgroundColor(Color.LTGRAY);}
        if(wed6_b==false){ wed6.setBackgroundColor(Color.WHITE); }else { wed6.setBackgroundColor(Color.LTGRAY);}
        if(wed7_b==false){ wed7.setBackgroundColor(Color.WHITE); }else { wed7.setBackgroundColor(Color.LTGRAY);}
        if(wed8_b==false){ wed8.setBackgroundColor(Color.WHITE); }else { wed8.setBackgroundColor(Color.LTGRAY);}
        if(wed9_b==false){ wed9.setBackgroundColor(Color.WHITE); }else { wed9.setBackgroundColor(Color.LTGRAY);}
        if(wed10_b==false){ wed10.setBackgroundColor(Color.WHITE); }else { wed10.setBackgroundColor(Color.LTGRAY);}
        if(wed11_b==false){ wed11.setBackgroundColor(Color.WHITE); }else { wed11.setBackgroundColor(Color.LTGRAY);}
        if(wed12_b==false){ wed12.setBackgroundColor(Color.WHITE); }else { wed12.setBackgroundColor(Color.LTGRAY);}
        if(wed13_b==false){ wed13.setBackgroundColor(Color.WHITE); }else { wed13.setBackgroundColor(Color.LTGRAY);}

        if(thu1_b==false){ thu1.setBackgroundColor(Color.WHITE); }else { thu1.setBackgroundColor(Color.LTGRAY);}
        if(thu2_b==false){ thu2.setBackgroundColor(Color.WHITE); }else { thu2.setBackgroundColor(Color.LTGRAY);}
        if(thu3_b==false){ thu3.setBackgroundColor(Color.WHITE); }else { thu3.setBackgroundColor(Color.LTGRAY);}
        if(thu4_b==false){ thu4.setBackgroundColor(Color.WHITE); }else { thu4.setBackgroundColor(Color.LTGRAY);}
        if(thu5_b==false){ thu5.setBackgroundColor(Color.WHITE); }else { thu5.setBackgroundColor(Color.LTGRAY);}
        if(thu6_b==false){ thu6.setBackgroundColor(Color.WHITE); }else { thu6.setBackgroundColor(Color.LTGRAY);}
        if(thu7_b==false){ thu7.setBackgroundColor(Color.WHITE); }else { thu7.setBackgroundColor(Color.LTGRAY);}
        if(thu8_b==false){ thu8.setBackgroundColor(Color.WHITE); }else { thu8.setBackgroundColor(Color.LTGRAY);}
        if(thu9_b==false){ thu9.setBackgroundColor(Color.WHITE); }else { thu9.setBackgroundColor(Color.LTGRAY);}
        if(thu10_b==false){ thu10.setBackgroundColor(Color.WHITE); }else { thu10.setBackgroundColor(Color.LTGRAY);}
        if(thu11_b==false){ thu11.setBackgroundColor(Color.WHITE); }else { thu11.setBackgroundColor(Color.LTGRAY);}
        if(thu12_b==false){ thu12.setBackgroundColor(Color.WHITE); }else { thu12.setBackgroundColor(Color.LTGRAY);}
        if(thu13_b==false){ thu13.setBackgroundColor(Color.WHITE); }else { thu13.setBackgroundColor(Color.LTGRAY);}

        if(fri1_b==false){ fri1.setBackgroundColor(Color.WHITE); }else { fri1.setBackgroundColor(Color.LTGRAY);}
        if(fri2_b==false){ fri2.setBackgroundColor(Color.WHITE); }else { fri2.setBackgroundColor(Color.LTGRAY);}
        if(fri3_b==false){ fri3.setBackgroundColor(Color.WHITE); }else { fri3.setBackgroundColor(Color.LTGRAY);}
        if(fri4_b==false){ fri4.setBackgroundColor(Color.WHITE); }else { fri4.setBackgroundColor(Color.LTGRAY);}
        if(fri5_b==false){ fri5.setBackgroundColor(Color.WHITE); }else { fri5.setBackgroundColor(Color.LTGRAY);}
        if(fri6_b==false){ fri6.setBackgroundColor(Color.WHITE); }else { fri6.setBackgroundColor(Color.LTGRAY);}
        if(fri7_b==false){ fri7.setBackgroundColor(Color.WHITE); }else { fri7.setBackgroundColor(Color.LTGRAY);}
        if(fri8_b==false){ fri8.setBackgroundColor(Color.WHITE); }else { fri8.setBackgroundColor(Color.LTGRAY);}
        if(fri9_b==false){ fri9.setBackgroundColor(Color.WHITE); }else { fri9.setBackgroundColor(Color.LTGRAY);}
        if(fri10_b==false){ fri10.setBackgroundColor(Color.WHITE); }else { fri10.setBackgroundColor(Color.LTGRAY);}
        if(fri11_b==false){ fri11.setBackgroundColor(Color.WHITE); }else { fri11.setBackgroundColor(Color.LTGRAY);}
        if(fri12_b==false){ fri12.setBackgroundColor(Color.WHITE); }else { fri12.setBackgroundColor(Color.LTGRAY);}
        if(fri13_b==false){ fri13.setBackgroundColor(Color.WHITE); }else { fri13.setBackgroundColor(Color.LTGRAY);}

        if(sat1_b==false){ sat1.setBackgroundColor(Color.WHITE); }else { sat1.setBackgroundColor(Color.LTGRAY);}
        if(sat2_b==false){ sat2.setBackgroundColor(Color.WHITE); }else { sat2.setBackgroundColor(Color.LTGRAY);}
        if(sat3_b==false){ sat3.setBackgroundColor(Color.WHITE); }else { sat3.setBackgroundColor(Color.LTGRAY);}
        if(sat4_b==false){ sat4.setBackgroundColor(Color.WHITE); }else { sat4.setBackgroundColor(Color.LTGRAY);}
        if(sat5_b==false){ sat5.setBackgroundColor(Color.WHITE); }else { sat5.setBackgroundColor(Color.LTGRAY);}
        if(sat6_b==false){ sat6.setBackgroundColor(Color.WHITE); }else { sat6.setBackgroundColor(Color.LTGRAY);}
        if(sat7_b==false){ sat7.setBackgroundColor(Color.WHITE); }else { sat7.setBackgroundColor(Color.LTGRAY);}
        if(sat8_b==false){ sat8.setBackgroundColor(Color.WHITE); }else { sat8.setBackgroundColor(Color.LTGRAY);}
        if(sat9_b==false){ sat9.setBackgroundColor(Color.WHITE); }else { sat9.setBackgroundColor(Color.LTGRAY);}
        if(sat10_b==false){ sat10.setBackgroundColor(Color.WHITE); }else { sat10.setBackgroundColor(Color.LTGRAY);}
        if(sat11_b==false){ sat11.setBackgroundColor(Color.WHITE); }else { sat11.setBackgroundColor(Color.LTGRAY);}
        if(sat12_b==false){ sat12.setBackgroundColor(Color.WHITE); }else { sat12.setBackgroundColor(Color.LTGRAY);}
        if(sat13_b==false){ sat13.setBackgroundColor(Color.WHITE); }else { sat13.setBackgroundColor(Color.LTGRAY);}

        if(sun1_b==false){ sun1.setBackgroundColor(Color.WHITE); }else { sun1.setBackgroundColor(Color.LTGRAY);}
        if(sun2_b==false){ sun2.setBackgroundColor(Color.WHITE); }else { sun2.setBackgroundColor(Color.LTGRAY);}
        if(sun3_b==false){ sun3.setBackgroundColor(Color.WHITE); }else { sun3.setBackgroundColor(Color.LTGRAY);}
        if(sun4_b==false){ sun4.setBackgroundColor(Color.WHITE); }else { sun4.setBackgroundColor(Color.LTGRAY);}
        if(sun5_b==false){ sun5.setBackgroundColor(Color.WHITE); }else { sun5.setBackgroundColor(Color.LTGRAY);}
        if(sun6_b==false){ sun6.setBackgroundColor(Color.WHITE); }else { sun6.setBackgroundColor(Color.LTGRAY);}
        if(sun7_b==false){ sun7.setBackgroundColor(Color.WHITE); }else { sun7.setBackgroundColor(Color.LTGRAY);}
        if(sun8_b==false){ sun8.setBackgroundColor(Color.WHITE); }else { sun8.setBackgroundColor(Color.LTGRAY);}
        if(sun9_b==false){ sun9.setBackgroundColor(Color.WHITE); }else { sun9.setBackgroundColor(Color.LTGRAY);}
        if(sun10_b==false){ sun10.setBackgroundColor(Color.WHITE); }else { sun10.setBackgroundColor(Color.LTGRAY);}
        if(sun11_b==false){ sun11.setBackgroundColor(Color.WHITE); }else { sun11.setBackgroundColor(Color.LTGRAY);}
        if(sun12_b==false){ sun12.setBackgroundColor(Color.WHITE); }else { sun12.setBackgroundColor(Color.LTGRAY);}
        if(sun13_b==false){ sun13.setBackgroundColor(Color.WHITE); }else { sun13.setBackgroundColor(Color.LTGRAY);}


        mon1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(mon1_b==false){
                    mon1_b=true;
                    mon1.setBackgroundColor(Color.LTGRAY);
                }else {
                    mon1_b=false;
                    mon1.setBackgroundColor(Color.WHITE);
                }

            }
        });
        mon2.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View view) {
                if(mon2_b==false){
                    mon2_b=true;
                    mon2.setBackgroundColor(Color.LTGRAY);
                }else {
                    mon2_b=false;
                    mon2.setBackgroundColor(Color.WHITE);
                }

            }
        });
        mon3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(mon3_b==false){
                    mon3_b=true;
                    mon3.setBackgroundColor(Color.LTGRAY);
                }else {
                    mon3_b=false;
                    mon3.setBackgroundColor(Color.WHITE);
                }

            }
        });
        mon4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(mon4_b==false){
                    mon4_b=true;
                    mon4.setBackgroundColor(Color.LTGRAY);
                }else {
                    mon4_b=false;
                    mon4.setBackgroundColor(Color.WHITE);
                }

            }
        });
        mon5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(mon5_b==false){
                    mon5_b=true;
                    mon5.setBackgroundColor(Color.LTGRAY);
                }else {
                    mon5_b=false;
                    mon5.setBackgroundColor(Color.WHITE);
                }

            }
        });
        mon6.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(mon6_b==false){
                    mon6_b=true;
                    mon6.setBackgroundColor(Color.LTGRAY);
                }else {
                    mon6_b=false;
                    mon6.setBackgroundColor(Color.WHITE);
                }

            }
        });
        mon7.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(mon7_b==false){
                    mon7_b=true;
                    mon7.setBackgroundColor(Color.LTGRAY);
                }else {
                    mon7_b=false;
                    mon7.setBackgroundColor(Color.WHITE);
                }

            }
        });
        mon8.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(mon8_b==false){
                    mon8_b=true;
                    mon8.setBackgroundColor(Color.LTGRAY);
                }else {
                    mon8_b=false;
                    mon8.setBackgroundColor(Color.WHITE);
                }

            }
        });
        mon9.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(mon9_b==false){
                    mon9_b=true;
                    mon9.setBackgroundColor(Color.LTGRAY);
                }else {
                    mon9_b=false;
                    mon9.setBackgroundColor(Color.WHITE);
                }

            }
        });
        mon10.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(mon10_b==false){
                    mon10_b=true;
                    mon10.setBackgroundColor(Color.LTGRAY);
                }else {
                    mon10_b=false;
                    mon10.setBackgroundColor(Color.WHITE);
                }

            }
        });
        mon11.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(mon11_b==false){
                    mon11_b=true;
                    mon11.setBackgroundColor(Color.LTGRAY);
                }else {
                    mon11_b=false;
                    mon11.setBackgroundColor(Color.WHITE);
                }

            }
        });
        mon12.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(mon12_b==false){
                    mon12_b=true;
                    mon12.setBackgroundColor(Color.LTGRAY);
                }else {
                    mon12_b=false;
                    mon12.setBackgroundColor(Color.WHITE);
                }

            }
        });
        mon13.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(mon13_b==false){
                    mon13_b=true;
                    mon13.setBackgroundColor(Color.LTGRAY);
                }else {
                    mon13_b=false;
                    mon13.setBackgroundColor(Color.WHITE);
                }

            }
        });

        tue1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(tue1_b==false){
                    tue1_b=true;
                    tue1.setBackgroundColor(Color.LTGRAY);
                }else {
                    tue1_b=false;
                    tue1.setBackgroundColor(Color.WHITE);
                }

            }
        });
        tue2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(tue2_b==false){
                    tue2_b=true;
                    tue2.setBackgroundColor(Color.LTGRAY);
                }else {
                    tue2_b=false;
                    tue2.setBackgroundColor(Color.WHITE);
                }

            }
        });
        tue3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(tue3_b==false){
                    tue3_b=true;
                    tue3.setBackgroundColor(Color.LTGRAY);
                }else {
                    tue3_b=false;
                    tue3.setBackgroundColor(Color.WHITE);
                }

            }
        });
        tue4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(tue4_b==false){
                    tue4_b=true;
                    tue4.setBackgroundColor(Color.LTGRAY);
                }else {
                    tue4_b=false;
                    tue4.setBackgroundColor(Color.WHITE);
                }

            }
        });
        tue5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(tue5_b==false){
                    tue5_b=true;
                    tue5.setBackgroundColor(Color.LTGRAY);
                }else {
                    tue5_b=false;
                    tue5.setBackgroundColor(Color.WHITE);
                }

            }
        });
        tue6.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(tue6_b==false){
                    tue6_b=true;
                    tue6.setBackgroundColor(Color.LTGRAY);
                }else {
                    tue6_b=false;
                    tue6.setBackgroundColor(Color.WHITE);
                }

            }
        });
        tue7.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(tue7_b==false){
                    tue7_b=true;
                    tue7.setBackgroundColor(Color.LTGRAY);
                }else {
                    tue7_b=false;
                    tue7.setBackgroundColor(Color.WHITE);
                }

            }
        });
        tue8.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(tue8_b==false){
                    tue8_b=true;
                    tue8.setBackgroundColor(Color.LTGRAY);
                }else {
                    tue8_b=false;
                    tue8.setBackgroundColor(Color.WHITE);
                }

            }
        });
        tue9.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(tue9_b==false){
                    tue9_b=true;
                    tue9.setBackgroundColor(Color.LTGRAY);
                }else {
                    tue9_b=false;
                    tue9.setBackgroundColor(Color.WHITE);
                }

            }
        });
        tue10.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(tue10_b==false){
                    tue10_b=true;
                    tue10.setBackgroundColor(Color.LTGRAY);
                }else {
                    tue10_b=false;
                    tue10.setBackgroundColor(Color.WHITE);
                }

            }
        });
        tue11.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(tue11_b==false){
                    tue11_b=true;
                    tue11.setBackgroundColor(Color.LTGRAY);
                }else {
                    tue11_b=false;
                    tue11.setBackgroundColor(Color.WHITE);
                }

            }
        });
        tue12.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(tue12_b==false){
                    tue12_b=true;
                    tue12.setBackgroundColor(Color.LTGRAY);
                }else {
                    tue12_b=false;
                    tue12.setBackgroundColor(Color.WHITE);
                }

            }
        });
        tue13.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(tue13_b==false){
                    tue13_b=true;
                    tue13.setBackgroundColor(Color.LTGRAY);
                }else {
                    tue13_b=false;
                    tue13.setBackgroundColor(Color.WHITE);
                }

            }
        });

        wed1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(wed1_b==false){
                    wed1_b=true;
                    wed1.setBackgroundColor(Color.LTGRAY);
                }else {
                    wed1_b=false;
                    wed1.setBackgroundColor(Color.WHITE);
                }

            }
        });
        wed2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(wed2_b==false){
                    wed2_b=true;
                    wed2.setBackgroundColor(Color.LTGRAY);
                }else {
                    wed2_b=false;
                    wed2.setBackgroundColor(Color.WHITE);
                }

            }
        });
        wed3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(wed3_b==false){
                    wed3_b=true;
                    wed3.setBackgroundColor(Color.LTGRAY);
                }else {
                    wed3_b=false;
                    wed3.setBackgroundColor(Color.WHITE);
                }

            }
        });
        wed4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(wed4_b==false){
                    wed4_b=true;
                    wed4.setBackgroundColor(Color.LTGRAY);
                }else {
                    wed4_b=false;
                    wed4.setBackgroundColor(Color.WHITE);
                }

            }
        });
        wed5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(wed5_b==false){
                    wed5_b=true;
                    wed5.setBackgroundColor(Color.LTGRAY);
                }else {
                    wed5_b=false;
                    wed5.setBackgroundColor(Color.WHITE);
                }

            }
        });
        wed6.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(wed6_b==false){
                    wed6_b=true;
                    wed6.setBackgroundColor(Color.LTGRAY);
                }else {
                    wed6_b=false;
                    wed6.setBackgroundColor(Color.WHITE);
                }

            }
        });
        wed7.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(wed7_b==false){
                    wed7_b=true;
                    wed7.setBackgroundColor(Color.LTGRAY);
                }else {
                    wed7_b=false;
                    wed7.setBackgroundColor(Color.WHITE);
                }

            }
        });
        wed8.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(wed8_b==false){
                    wed8_b=true;
                    wed8.setBackgroundColor(Color.LTGRAY);
                }else {
                    wed8_b=false;
                    wed8.setBackgroundColor(Color.WHITE);
                }

            }
        });
        wed9.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(wed9_b==false){
                    wed9_b=true;
                    wed9.setBackgroundColor(Color.LTGRAY);
                }else {
                    wed9_b=false;
                    wed9.setBackgroundColor(Color.WHITE);
                }

            }
        });
        wed10.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(wed10_b==false){
                    wed10_b=true;
                    wed10.setBackgroundColor(Color.LTGRAY);
                }else {
                    wed10_b=false;
                    wed10.setBackgroundColor(Color.WHITE);
                }

            }
        });
        wed11.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(wed11_b==false){
                    wed11_b=true;
                    wed11.setBackgroundColor(Color.LTGRAY);
                }else {
                    wed11_b=false;
                    wed11.setBackgroundColor(Color.WHITE);
                }

            }
        });
        wed12.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(wed12_b==false){
                    wed12_b=true;
                    wed12.setBackgroundColor(Color.LTGRAY);
                }else {
                    wed12_b=false;
                    wed12.setBackgroundColor(Color.WHITE);
                }

            }
        });
        wed13.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(wed13_b==false){
                    wed13_b=true;
                    wed13.setBackgroundColor(Color.LTGRAY);
                }else {
                    wed13_b=false;
                    wed13.setBackgroundColor(Color.WHITE);
                }

            }
        });

        thu1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(thu1_b==false){
                    thu1_b=true;
                    thu1.setBackgroundColor(Color.LTGRAY);
                }else {
                    thu1_b=false;
                    thu1.setBackgroundColor(Color.WHITE);
                }

            }
        });
        thu2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(thu2_b==false){
                    thu2_b=true;
                    thu2.setBackgroundColor(Color.LTGRAY);
                }else {
                    thu2_b=false;
                    thu2.setBackgroundColor(Color.WHITE);
                }

            }
        });
        thu3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(thu3_b==false){
                    thu3_b=true;
                    thu3.setBackgroundColor(Color.LTGRAY);
                }else {
                    thu3_b=false;
                    thu3.setBackgroundColor(Color.WHITE);
                }

            }
        });
        thu4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(thu4_b==false){
                    thu4_b=true;
                    thu4.setBackgroundColor(Color.LTGRAY);
                }else {
                    thu4_b=false;
                    thu4.setBackgroundColor(Color.WHITE);
                }

            }
        });
        thu5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(thu5_b==false){
                    thu5_b=true;
                    thu5.setBackgroundColor(Color.LTGRAY);
                }else {
                    thu5_b=false;
                    thu5.setBackgroundColor(Color.WHITE);
                }

            }
        });
        thu6.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(thu6_b==false){
                    thu6_b=true;
                    thu6.setBackgroundColor(Color.LTGRAY);
                }else {
                    thu6_b=false;
                    thu6.setBackgroundColor(Color.WHITE);
                }

            }
        });
        thu7.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(thu7_b==false){
                    thu7_b=true;
                    thu7.setBackgroundColor(Color.LTGRAY);
                }else {
                    thu7_b=false;
                    thu7.setBackgroundColor(Color.WHITE);
                }

            }
        });
        thu8.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(thu8_b==false){
                    thu8_b=true;
                    thu8.setBackgroundColor(Color.LTGRAY);
                }else {
                    thu8_b=false;
                    thu8.setBackgroundColor(Color.WHITE);
                }

            }
        });
        thu9.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(thu9_b==false){
                    thu9_b=true;
                    thu9.setBackgroundColor(Color.LTGRAY);
                }else {
                    thu9_b=false;
                    thu9.setBackgroundColor(Color.WHITE);
                }

            }
        });
        thu10.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(thu10_b==false){
                    thu10_b=true;
                    thu10.setBackgroundColor(Color.LTGRAY);
                }else {
                    thu10_b=false;
                    thu10.setBackgroundColor(Color.WHITE);
                }

            }
        });
        thu11.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(thu11_b==false){
                    thu11_b=true;
                    thu11.setBackgroundColor(Color.LTGRAY);
                }else {
                    thu11_b=false;
                    thu11.setBackgroundColor(Color.WHITE);
                }

            }
        });
        thu12.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(thu12_b==false){
                    thu12_b=true;
                    thu12.setBackgroundColor(Color.LTGRAY);
                }else {
                    thu12_b=false;
                    thu12.setBackgroundColor(Color.WHITE);
                }

            }
        });
        thu13.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(thu13_b==false){
                    thu13_b=true;
                    thu13.setBackgroundColor(Color.LTGRAY);
                }else {
                    thu13_b=false;
                    thu13.setBackgroundColor(Color.WHITE);
                }

            }
        });

        fri1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(fri1_b==false){
                    fri1_b=true;
                    fri1.setBackgroundColor(Color.LTGRAY);
                }else {
                    fri1_b=false;
                    fri1.setBackgroundColor(Color.WHITE);
                }

            }
        });
        fri2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(fri2_b==false){
                    fri2_b=true;
                    fri2.setBackgroundColor(Color.LTGRAY);
                }else {
                    fri2_b=false;
                    fri2.setBackgroundColor(Color.WHITE);
                }

            }
        });
        fri3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(fri3_b==false){
                    fri3_b=true;
                    fri3.setBackgroundColor(Color.LTGRAY);
                }else {
                    fri3_b=false;
                    fri3.setBackgroundColor(Color.WHITE);
                }

            }
        });
        fri4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(fri4_b==false){
                    fri4_b=true;
                    fri4.setBackgroundColor(Color.LTGRAY);
                }else {
                    fri4_b=false;
                    fri4.setBackgroundColor(Color.WHITE);
                }

            }
        });
        fri5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(fri5_b==false){
                    fri5_b=true;
                    fri5.setBackgroundColor(Color.LTGRAY);
                }else {
                    fri5_b=false;
                    fri5.setBackgroundColor(Color.WHITE);
                }

            }
        });
        fri6.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(fri6_b==false){
                    fri6_b=true;
                    fri6.setBackgroundColor(Color.LTGRAY);
                }else {
                    fri6_b=false;
                    fri6.setBackgroundColor(Color.WHITE);
                }

            }
        });
        fri7.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(fri7_b==false){
                    fri7_b=true;
                    fri7.setBackgroundColor(Color.LTGRAY);
                }else {
                    fri7_b=false;
                    fri7.setBackgroundColor(Color.WHITE);
                }

            }
        });
        fri8.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(fri8_b==false){
                    fri8_b=true;
                    fri8.setBackgroundColor(Color.LTGRAY);
                }else {
                    fri8_b=false;
                    fri8.setBackgroundColor(Color.WHITE);
                }

            }
        });
        fri9.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(fri9_b==false){
                    fri9_b=true;
                    fri9.setBackgroundColor(Color.LTGRAY);
                }else {
                    fri9_b=false;
                    fri9.setBackgroundColor(Color.WHITE);
                }

            }
        });
        fri10.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(fri10_b==false){
                    fri10_b=true;
                    fri10.setBackgroundColor(Color.LTGRAY);
                }else {
                    fri10_b=false;
                    fri10.setBackgroundColor(Color.WHITE);
                }

            }
        });
        fri11.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(fri11_b==false){
                    fri11_b=true;
                    fri11.setBackgroundColor(Color.LTGRAY);
                }else {
                    fri11_b=false;
                    fri11.setBackgroundColor(Color.WHITE);
                }

            }
        });
        fri12.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(fri12_b==false){
                    fri12_b=true;
                    fri12.setBackgroundColor(Color.LTGRAY);
                }else {
                    fri12_b=false;
                    fri12.setBackgroundColor(Color.WHITE);
                }

            }
        });
        fri13.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(fri13_b==false){
                    fri13_b=true;
                    fri13.setBackgroundColor(Color.LTGRAY);
                }else {
                    fri13_b=false;
                    fri13.setBackgroundColor(Color.WHITE);
                }

            }
        });

        sat1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sat1_b==false){
                    sat1_b=true;
                    sat1.setBackgroundColor(Color.LTGRAY);
                }else {
                    sat1_b=false;
                    sat1.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sat2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sat2_b==false){
                    sat2_b=true;
                    sat2.setBackgroundColor(Color.LTGRAY);
                }else {
                    sat2_b=false;
                    sat2.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sat3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sat3_b==false){
                    sat3_b=true;
                    sat3.setBackgroundColor(Color.LTGRAY);
                }else {
                    sat3_b=false;
                    sat3.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sat4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sat4_b==false){
                    sat4_b=true;
                    sat4.setBackgroundColor(Color.LTGRAY);
                }else {
                    sat4_b=false;
                    sat4.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sat5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sat5_b==false){
                    sat5_b=true;
                    sat5.setBackgroundColor(Color.LTGRAY);
                }else {
                    sat5_b=false;
                    sat5.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sat6.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sat6_b==false){
                    sat6_b=true;
                    sat6.setBackgroundColor(Color.LTGRAY);
                }else {
                    sat6_b=false;
                    sat6.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sat7.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sat7_b==false){
                    sat7_b=true;
                    sat7.setBackgroundColor(Color.LTGRAY);
                }else {
                    sat7_b=false;
                    sat7.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sat8.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sat8_b==false){
                    sat8_b=true;
                    sat8.setBackgroundColor(Color.LTGRAY);
                }else {
                    sat8_b=false;
                    sat8.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sat9.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sat9_b==false){
                    sat9_b=true;
                    sat9.setBackgroundColor(Color.LTGRAY);
                }else {
                    sat9_b=false;
                    sat9.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sat10.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sat10_b==false){
                    sat10_b=true;
                    sat10.setBackgroundColor(Color.LTGRAY);
                }else {
                    sat10_b=false;
                    sat10.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sat11.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sat11_b==false){
                    sat11_b=true;
                    sat11.setBackgroundColor(Color.LTGRAY);
                }else {
                    sat11_b=false;
                    sat11.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sat12.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sat12_b==false){
                    sat12_b=true;
                    sat12.setBackgroundColor(Color.LTGRAY);
                }else {
                    sat12_b=false;
                    sat12.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sat13.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sat13_b==false){
                    sat13_b=true;
                    sat13.setBackgroundColor(Color.LTGRAY);
                }else {
                    sat13_b=false;
                    sat13.setBackgroundColor(Color.WHITE);
                }

            }
        });

        sun1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sun1_b==false){
                    sun1_b=true;
                    sun1.setBackgroundColor(Color.LTGRAY);
                }else {
                    sun1_b=false;
                    sun1.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sun2.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sun2_b==false){
                    sun2_b=true;
                    sun2.setBackgroundColor(Color.LTGRAY);
                }else {
                    sun2_b=false;
                    sun2.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sun3.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sun3_b==false){
                    sun3_b=true;
                    sun3.setBackgroundColor(Color.LTGRAY);
                }else {
                    sun3_b=false;
                    sun3.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sun4.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sun4_b==false){
                    sun4_b=true;
                    sun4.setBackgroundColor(Color.LTGRAY);
                }else {
                    sun4_b=false;
                    sun4.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sun5.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sun5_b==false){
                    sun5_b=true;
                    sun5.setBackgroundColor(Color.LTGRAY);
                }else {
                    sun5_b=false;
                    sun5.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sun6.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sun6_b==false){
                    sun6_b=true;
                    sun6.setBackgroundColor(Color.LTGRAY);
                }else {
                    sun6_b=false;
                    sun6.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sun7.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sun7_b==false){
                    sun7_b=true;
                    sun7.setBackgroundColor(Color.LTGRAY);
                }else {
                    sun7_b=false;
                    sun7.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sun8.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sun8_b==false){
                    sun8_b=true;
                    sun8.setBackgroundColor(Color.LTGRAY);
                }else {
                    sun8_b=false;
                    sun8.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sun9.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sun9_b==false){
                    sun9_b=true;
                    sun9.setBackgroundColor(Color.LTGRAY);
                }else {
                    sun9_b=false;
                    sun9.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sun10.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sun10_b==false){
                    sun10_b=true;
                    sun10.setBackgroundColor(Color.LTGRAY);
                }else {
                    sun10_b=false;
                    sun10.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sun11.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sun11_b==false){
                    sun11_b=true;
                    sun11.setBackgroundColor(Color.LTGRAY);
                }else {
                    sun11_b=false;
                    sun11.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sun12.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sun12_b==false){
                    sun12_b=true;
                    sun12.setBackgroundColor(Color.LTGRAY);
                }else {
                    sun12_b=false;
                    sun12.setBackgroundColor(Color.WHITE);
                }

            }
        });
        sun13.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                if(sun13_b==false){
                    sun13_b=true;
                    sun13.setBackgroundColor(Color.LTGRAY);
                }else {
                    sun13_b=false;
                    sun13.setBackgroundColor(Color.WHITE);
                }

            }
        });



    }

}
