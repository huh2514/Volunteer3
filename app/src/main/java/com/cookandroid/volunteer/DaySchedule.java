package com.cookandroid.volunteer;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DaySchedule extends AppCompatActivity {
    TextView date;
    String receiveDate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_layout);

        date = (TextView)findViewById(R.id.date1);
        receiveDate = ((Calendar)Calendar.context_calendar).date;
    }

    @Override
    protected void onStart() {
        super.onStart();
        date.setText(receiveDate);
    }
}
