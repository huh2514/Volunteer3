package com.cookandroid.volunteer;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class SecondFragment extends Fragment
{
    String title2, place2, sTime2, eTime2, sDay2, eDay2, code2;
    ArrayList<searchItem> list2 = ((StudentMain)StudentMain.context_main).list;

    public SecondFragment()
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
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_second, container, false);
        TextView title = (TextView)layout.findViewById(R.id.title2);
        TextView place = (TextView)layout.findViewById(R.id.place2);
        TextView time = (TextView)layout.findViewById(R.id.time2);
        TextView day = (TextView)layout.findViewById(R.id.day2);

        LinearLayout second_layout = (LinearLayout)layout.findViewById(R.id.fragment_second_layout);

        if(list2.get(1).progrmSj==null) title.setText("적절한 봉사활동이 없습니다.");
        else {
            title2 = list2.get(1).progrmSj;
            Log.d("확인", title2);
            place2 = list2.get(1).actPlace;
            sTime2 = list2.get(1).actBeginTm;
            eTime2 = list2.get(1).actEndTm;
            sDay2 = list2.get(1).progrmBgnde;
            eDay2 = list2.get(1).progrmEndde;
            code2 = list2.get(1).progrmRegistNo;


            title.setText(title2);
            place.setText(place2);
            time.setText(sTime2 + "시 ~ " + eTime2 + "시");
            day.setText(sDay2 + " ~ " + eDay2 + "");
        }
        second_layout.setOnClickListener(new View.OnClickListener() {
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

