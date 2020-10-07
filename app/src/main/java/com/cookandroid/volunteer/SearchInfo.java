package com.cookandroid.volunteer;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

import android.os.Bundle;
import android.widget.EditText;

public class SearchInfo extends AppCompatActivity {

    public static Context context_main;
    int sy=0, sm=0, sd=0, ey=0, em=0, ed=0;
    EditText editText;
    String sDate;
    String eDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_info);
        context_main = this;
        editText = (EditText)findViewById(R.id.searchText);


        Button sButton = findViewById(R.id.sBtn);
        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sShowDate();

            }

        });

        Button eButton = findViewById(R.id.eBtn);
        eButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eShowDate();
            }
        });

        Button searchBtn = findViewById(R.id.searchBtn);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResultView.class);
                startActivity(intent);
            }
        });

    }

    void sShowDate() {
        final Button sButton = findViewById(R.id.sBtn);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                sy = year;
                sm = month+1;
                sd = dayOfMonth;
                String sy1 = Integer.toString(sy);
                String sm1, sd1;
                if(sm<10) {
                    sm1 = Integer.toString(sm);
                    sm1 = "0"+sm1;
                }
                else {
                    sm1 = Integer.toString(sm);
                }
                if(sd<10) {
                    sd1 = Integer.toString(sd);
                    sd1 = "0"+sd1;
                }
                else {
                    sd1 = Integer.toString(sd);
                }
                sDate = sy1+sm1+sd1;
                sButton.setText(sDate);
            }
        },2020, 1, 11);

        datePickerDialog.setMessage("메시지");
        datePickerDialog.show();

    }

    void eShowDate() {
        final Button eButton = findViewById(R.id.eBtn);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                ey = year;
                em = month+1;
                ed = dayOfMonth;
                String ey1 = Integer.toString(ey);
                String em1, ed1;
                if(em<10) {
                    em1 = Integer.toString(em);
                    em1 = "0"+em1;
                }
                else {
                    em1 = Integer.toString(em);
                }
                if(ed<10) {
                    ed1 = Integer.toString(ed);
                    ed1 = "0"+ed1;
                }
                else {
                    ed1 = Integer.toString(ed);
                }
                eDate = ey1+em1+ed1;
                eButton.setText(eDate);
            }
        },2020, 1, 11);

        datePickerDialog.setMessage("메시지");
        datePickerDialog.show();
    }
}
