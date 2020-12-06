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

public class ThirdFragment extends Fragment
{
    String title3, place3, sTime3, eTime3, sDay3, eDay3, code3;
    ArrayList<searchItem> list3 = ((StudentMain)StudentMain.context_main).list;

    public ThirdFragment()
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
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.fragment_third, container, false);
        TextView title = (TextView)layout.findViewById(R.id.title3);
        TextView place = (TextView)layout.findViewById(R.id.place3);
        TextView time = (TextView)layout.findViewById(R.id.time3);
        TextView day = (TextView)layout.findViewById(R.id.day3);
        LinearLayout third_layout = (LinearLayout)layout.findViewById(R.id.fragment_third_layout);

        if(list3.get(2).progrmSj==null) title.setText("적절한 봉사활동이 없습니다.");

        else {
            title3 = list3.get(2).progrmSj;
            Log.d("확인", title3);
            place3 = list3.get(2).actPlace;
            sTime3 = list3.get(2).actBeginTm;
            eTime3 = list3.get(2).actEndTm;
            sDay3 = list3.get(2).progrmBgnde;
            eDay3 = list3.get(2).progrmEndde;
            code3 = list3.get(2).progrmRegistNo;


            title.setText(title3);
            place.setText(place3);
            time.setText(sTime3 + "시 ~ " + eTime3 + "시");
            day.setText(sDay3 + " ~ " + eDay3 + "");
        }
        third_layout.setOnClickListener(new View.OnClickListener() {
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

