package com.cookandroid.volunteer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class Calendar extends AppCompatActivity {
    Button btn;
    LinearLayout calLay;
    Context context;
    String date;
    int count = 0;
    private static final String TAG = "CalendarActivity";

    private CalendarView mCalendarView;
    public static Context context_calendar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calendar_layout);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);
        calLay = (LinearLayout)findViewById(R.id.cal_Lay);
        context = this;
        context_calendar = this;
        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                date = i + "/" + (i1 + 1) + "/" + i2 ;
                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.DaySchedule");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });
        View.OnClickListener myListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int eventID = view.getId();
                Toast.makeText(context, String.valueOf(eventID), Toast.LENGTH_SHORT).show();
            }
        };

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }
}
