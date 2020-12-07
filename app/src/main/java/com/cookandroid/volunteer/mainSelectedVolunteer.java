package com.cookandroid.volunteer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

/*import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;*/
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

public class mainSelectedVolunteer extends AppCompatActivity /*implements OnMapReadyCallback*/ {
    boolean[] getWeek = new boolean[7];

   /* private GoogleMap mgoogleMap;*/

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
    public String schedulegetEmail="em";


    TextView seldTItle, seldTime, seldPlace, seldHour, seldNTime, seldhoumany, seldApplyd, seldBoon, seldRecPeriod, seldRegist, seldProgrmCn, seldWeekDay;
    SearchData searchVo = ((StudentMain) StudentMain.context_main).data;

    //셰어드 프리퍼런스 변수
    public final String PREFERENCE = "com.volunteerLogin.samplesharepreference";
    public final String key01 = "Email";
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_selected_volunteer);
        String weekArray = searchVo.actWkdy;
        for(int j = 0;j<7;j++) {

            if (weekArray.substring(j, j + 1).equals("1")) {
                getWeek[j] = true;
            }
        }
        String weekText = "";
        for(int j = 0;j<7;j++){
            switch (j){
                case 0:
                    if(getWeek[j])
                        weekText += "월 ";
                    break;
                case 1:
                    if(getWeek[j])
                        weekText += "화 ";
                    break;
                case 2:
                    if(getWeek[j])
                        weekText += "수 ";
                    break;
                case 3:
                    if(getWeek[j])
                        weekText += "목 ";
                    break;
                case 4:
                    if(getWeek[j])
                        weekText += "금 ";
                    break;
                case 5:
                    if(getWeek[j])
                        weekText += "토 ";
                    break;
                case 6:
                    if(getWeek[j])
                        weekText += "일 ";
                    break;
            }
        }
        seldTItle = (TextView) findViewById(R.id.seldTItle);
        seldTime = (TextView) findViewById(R.id.seldTime);
        seldPlace = (TextView) findViewById(R.id.seldPlace);
        seldHour = (TextView) findViewById(R.id.seldHour);
        seldNTime = (TextView) findViewById(R.id.seldNTime);
        seldhoumany = (TextView) findViewById(R.id.seldhoumany);
        seldApplyd = (TextView) findViewById(R.id.seldApplyd);
        seldBoon = (TextView) findViewById(R.id.seldBoon);
        seldRecPeriod = (TextView) findViewById(R.id.seldRecPeriod);
        seldRegist = (TextView) findViewById(R.id.seldRegist);
        seldProgrmCn= (TextView) findViewById(R.id.seldContents);
       /*seldWeekDay= (TextView) findViewById(R.id.seldWeekDay);*/

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
        /*seldWeekDay.setText(weekText)

        SupportMapFragment supportMapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this);*/
    }
    public void onBtnClicked(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://1365.go.kr/vols/P9210/partcptn/timeCptn.do?type=show&progrmRegistNo=" + searchVo.progrmRegistNo));
        startActivity(intent);

        //빌더 선언
        AlertDialog.Builder builder = new AlertDialog.Builder(mainSelectedVolunteer.this);
        // 제목 설정
        builder.setTitle("봉사활동을 신청하셨습니까?");
        // 요소 설정
        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String vDate = searchVo.progrmBgnde + searchVo.progrmEndde;
                String vTitle = searchVo.progrmSj;
                String vCode = searchVo.progrmRegistNo;
                String vPlace = searchVo.actPlace;
                String vTime = searchVo.actBeginTm + searchVo.actEndTm;
                String Email = getEmailFromDevice(key01);

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("schedule").child("student");
                String inputString = Email + "," + vDate + "," + vTitle + "," + vCode + "," + vTime + "," + vPlace;
                myRef.push().setValue(inputString);

                Toast.makeText(getApplicationContext(),"봉사활동이 추가되었습니다",Toast.LENGTH_LONG);

                checkoutSchedule(key01);
            }
        });
        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertD = builder.create();
        alertD.show();
    }


    public String getEmailFromDevice(String emailkey){
        Log.d("이메일 가져오기","메서드 실행됨");
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        email = pref.getString(emailkey,"");
        Log.d("이메일 가져오기",email);
        return email;
    }


    public void checkoutSchedule(String emailkey){
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        schedulegetEmail = pref.getString(emailkey,"");
        Log.d("스케쥴 체크아웃",schedulegetEmail);

        /*getData("http://192.168.142.97/PHP_connection_schedules.php");*/
    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            SharedPreferences pref = getSharedPreferences(schedulePreference, MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
            editor.clear();
            int count = 0;
            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                String date = c.getString(TAG_vDate);
                /*Log.d("봉사활동정보 가져오기",date);*/
                String code = c.getString(TAG_vCode);
                /*Log.d("봉사활동정보 가져오기",code);*/
                String id = c.getString(TAG_Email);
                /*Log.d("봉사활동정보 가져오기",id);*/

                if(schedulegetEmail.equals(id)) {
                    editor.putString("date" + count, date);
                    editor.putString("code" + count, code);
                    editor.commit();
                    count++;
                }
            }
            Log.d("봉사활동정보","실행");
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    /*@Override
    public void onMapReady(final GoogleMap googleMap) {
        mgoogleMap = googleMap;
        Context context = this;

        Location cityHallLocation = addrToPoint(context);
        final LatLng vlocation = new LatLng(cityHallLocation.getLatitude(),cityHallLocation.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(vlocation);
        markerOptions.title("봉사장소");
        googleMap.addMarker(markerOptions);

        googleMap.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                mgoogleMap.moveCamera(CameraUpdateFactory.newLatLng(vlocation));
                mgoogleMap.animateCamera(CameraUpdateFactory.zoomTo(15));
            }
        });
    }*/

    public Location addrToPoint(Context context){
        Location location = new Location("");
        Geocoder geocoder = new Geocoder(context);
        List<Address> address = null;

        try{
            Log.d("위치확인",searchVo.actPlace);
            address = geocoder.getFromLocationName(searchVo.actPlace,3);
        }catch (IOException e){
            e.printStackTrace();
        }
        if(address != null){
            for(int i = 0;i<address.size();i++){
                Address latlng = address.get(i);
                location.setLatitude(latlng.getLatitude());
                location.setLongitude(latlng.getLongitude());
            }
        }
        return location;
    }

/*
    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldverson, int newVerson) {
            db.execSQL("DROP TABLE IF EXISTS volunteerTBL");
            onCreate(db);
        }

    }
*/

}
