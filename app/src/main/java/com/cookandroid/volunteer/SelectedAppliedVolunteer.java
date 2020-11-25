package com.cookandroid.volunteer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.ImageDecoder;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;

public class SelectedAppliedVolunteer extends AppCompatActivity {


    //스케쥴 데이터 가져올떄
    String myJSON;
    JSONArray peoples = null;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_vDate = "vDate";
    private static final String TAG_vPlace = "vPlace";
    private static final String TAG_vCode = "vCode";
    private static final String TAG_Email = "Email";

    public final String schedulePreference = "com.volunteerLogin.schedulePreference";

    //로그인 정보
    public final String key02 = "pwd";

    //봉사활동 스케쥴 정보
    public final String scheduleKey01 = "Email";
    public String schedulegetEmail = "em";

    Button DeleteAppliedVolunteerbtn;
    TextView seldTItle, seldTime, seldPlace, seldHour, seldNTime, seldhoumany, seldApplyd, seldBoon, seldRecPeriod, seldRegist, seldProgrmCn;
    SearchData searchVo = ((ShowAppliedVolunteer) ShowAppliedVolunteer.context_main_ShowAppliedVolunteer).data;

    //셰어드 프리퍼런스 변수
    public final String PREFERENCE = "com.volunteerLogin.samplesharepreference";
    public final String key01 = "Email";
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_applied_volunteer);


        seldTItle = (TextView) findViewById(R.id.appliedTItle);
        seldTime = (TextView) findViewById(R.id.appliedTime);
        seldPlace = (TextView) findViewById(R.id.appliedPlace);
        seldHour = (TextView) findViewById(R.id.appliedHour);
        seldNTime = (TextView) findViewById(R.id.appliedNTime);
        seldhoumany = (TextView) findViewById(R.id.appliedhoumany);
        seldApplyd = (TextView) findViewById(R.id.appliedApplyd);
        seldBoon = (TextView) findViewById(R.id.appliedBoon);
        seldRecPeriod = (TextView) findViewById(R.id.appliedRecPeriod);
        seldRegist = (TextView) findViewById(R.id.appliedRegist);
        seldProgrmCn = (TextView) findViewById(R.id.appliedContents);

        seldTItle.setText(searchVo.progrmSj);
        seldTime.setText(searchVo.progrmBgnde + "~" + searchVo.progrmEndde);
        seldPlace.setText(searchVo.actPlace);
        seldHour.setText(searchVo.actBeginTm + "~" + searchVo.actEndTm);
        seldNTime.setText(searchVo.noticeBgnde + "~" + searchVo.noticeEndde);
        seldhoumany.setText(searchVo.rcritNmpr);
        seldApplyd.setText(searchVo.appTotal);
        seldBoon.setText(searchVo.srvcClCode);
        seldRecPeriod.setText(searchVo.mnnstNm);
        seldRegist.setText(searchVo.nanmmbyNm);
        seldProgrmCn.setText(searchVo.progrmCn);

        DeleteAppliedVolunteerbtn = (Button) findViewById(R.id.DeleteAppliedVolunteerBtn);
        DeleteAppliedVolunteerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //빌더 선언
                AlertDialog.Builder builder = new AlertDialog.Builder(SelectedAppliedVolunteer.this);
                // 제목 설정
                builder.setTitle("해당 봉사활동을 신청목록에서 제거하시겠습니까??");
                // 요소 설정
                builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        final String vCode = searchVo.progrmRegistNo;
                        String Email = getEmailFromDevice(key01);

                        FirebaseDatabase database = FirebaseDatabase.getInstance();
                        String findValue = Email + searchVo.progrmBgnde + searchVo.progrmEndde + searchVo.progrmSj + searchVo.progrmRegistNo + searchVo.actBeginTm + searchVo.actPlace;

                    }
                });
                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

            }
        });
    }

    public String getEmailFromDevice(String emailkey){
        Log.d("이메일 가져오기","메서드 실행됨");
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        email = pref.getString(emailkey,"");
        Log.d("이메일 가져오기",email);
        return email;
    }


}