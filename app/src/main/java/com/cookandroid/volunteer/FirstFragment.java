package com.cookandroid.volunteer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class FirstFragment extends Fragment
{
    String title1, place1, sTime1, eTime1, sDay1, eDay1, code1;
    ArrayList<searchItem> list1 = ((StudentMain)StudentMain.context_main).list;



    public FirstFragment()
    {
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_first, container, false);

        TextView title = (TextView)layout.findViewById(R.id.title1);
        TextView place = (TextView)layout.findViewById(R.id.place1);
        TextView time = (TextView)layout.findViewById(R.id.time1);
        TextView day = (TextView)layout.findViewById(R.id.day1);
        LinearLayout first_layout = (LinearLayout)layout.findViewById(R.id.fragment_first_layout);

        if(list1.get(0).progrmSj==null) title.setText("적절한 봉사활동이 없습니다.");
        else {
            title1 = list1.get(0).progrmSj;
            Log.d("확인", title1);
            place1 = list1.get(0).actPlace;
            sTime1 = list1.get(0).actBeginTm;
            eTime1 = list1.get(0).actEndTm;
            sDay1 = list1.get(0).progrmBgnde;
            eDay1 = list1.get(0).progrmEndde;
            code1 = list1.get(0).progrmRegistNo;


            title.setText(title1);
            place.setText(place1);
            time.setText(sTime1 + "시 ~ " + eTime1 + "시");
            day.setText(sDay1 + " ~ " + eDay1 + "");
        }

        first_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.SearchInfo");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });
        return layout;
    }

}

