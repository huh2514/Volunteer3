package com.cookandroid.volunteer;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Target;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class StudentMain extends AppCompatActivity {

    private GpsTracker gpsTracker;
    private static final int GPS_ENABLE_REQUEST_CODE = 2001;
    private static final int PERMISSIONS_REQUEST_CODE = 100;
    String[] REQUIRED_PERMISSIONS  = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    String location,sido,gugun,sidoCode,gugunCode;
    String [] locationArr;
    public String dataKey = "99scXLkolGPdepyUojDC%2BsBNxExtjmJaDwjRHkpJoVZ0yJ49tcKZk6r%2BM3y77UcfpaxvdLBlU%2F8nkuR2mm%2BG9Q%3D%3D";
    addrCodeItem busSido = null;
    addrCodeItem busGugun = null;


    Button SearchVolunteerBtn, mypageBtn, openCalendarBtn, timeTableBtn;
    TextView targetTime;
    ViewPager vp;

    //php 스케쥴 데이터 가져올때
    String myJSON;
    String email;
    String name;
    JSONArray peoples = null;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_vDate = "vDate";
    private static final String TAG_vPlace = "vPlace";
    private static final String TAG_vCode = "vCode";
    private static final String TAG_Email = "Email";

    //백그라운드서비스 실행확인

    // 마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    private long backKeyPressedTime = 0;
    // 첫 번째 뒤로가기 버튼을 누를때 표시
    private Toast toast;


    public final String PREFERENCE = "com.volunteerLogin.samplesharepreference";
    public final String schedulePreference = "com.volunteerLogin.schedulePreference";


    //로그인 정보
    public final String emailkey = "Email";
    public final String namekey = "Name";
    public final String key02 = "pwd";

    //봉사활동 스케쥴 정보
    public final String scheduleKey01 = "Email";
    public String schedulegetEmail="em";
    public final String dateKey = "Date";
    public final String titleKey = "Title";
    public final String codeKey = "Code";
    public final String timeKey = "Time";
    public final String placeKey = "Place";

    public static Context context_main;


    private String requestUrl;
    private String requestUrl1;
    private String requestUrl2;
    private String requestUrl3;

    searchItem bus = null;
    searchItem buss = null;
    public String datakey3 = "99scXLkolGPdepyUojDC%2BsBNxExtjmJaDwjRHkpJoVZ0yJ49tcKZk6r%2BM3y77";
    public String dataKey2 = "99scXLkolGPdepyUojDC%2BsBNxExtjmJaDwjRHkpJoVZ0yJ49tcKZk6r%2BM3y77UcfpaxvdLBlU%2F8nkuR2mm%2BG9Q%3D%3D";
    String strc;
    ArrayList<searchItem> list = null;
    public int getCodeCount;
    public int filtercount = 0;
    boolean[] checkWeek = new boolean[7];
    boolean[] mon = new boolean[14];
    boolean[] tue = new boolean[14];
    boolean[] wed = new boolean[14];
    boolean[] thu = new boolean[14];
    boolean[] fri = new boolean[14];
    boolean[] sat = new boolean[14];
    boolean[] sun = new boolean[14];

    boolean[] getWeek = new boolean[7];
    boolean[] gmon = new boolean[14];
    boolean[] gtue = new boolean[14];
    boolean[] gwed = new boolean[14];
    boolean[] gthu = new boolean[14];
    boolean[] gfri = new boolean[14];
    boolean[] gsat = new boolean[14];
    boolean[] gsun = new boolean[14];

    String getsplitemail;
    boolean[] weekdy, schedule;
    SearchData[] getAll = new SearchData[100];
    SearchData[] scheduleFiltered = new SearchData[100];

    String title1, place1, sTime1, eTime1, sDay1, eDay1, code1;
    TextView title,place,time,day;
    LinearLayout first_layout;
    SearchData data = new SearchData();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentmain_layout);
        context_main = this;

        if (!checkLocationServicesStatus()) {

            showDialogForLocationServiceSetting();
        }else {

            checkRunTimePermission();
        }

        gpsTracker = new GpsTracker(StudentMain.this);

        double latitude = gpsTracker.getLatitude();
        double longitude = gpsTracker.getLongitude();

        String address = getCurrentAddress(latitude, longitude);
        location = address;
        if(location!="") {
            locationArr = location.split(" ");
            sido = locationArr[1];
            gugun = locationArr[2];
        }

        if(location=="") sidoCode="";
        else {
            MyAsyncTask1 myAsyncTask1 = new MyAsyncTask1();
            myAsyncTask1.execute();
        }
        if(location=="") gugunCode="";
        else {
            MyAsyncTask2 myAsyncTask2 = new MyAsyncTask2();
            myAsyncTask2.execute();
        }

        Log.d("현재위치",location);


        //background 서비스 시작
        Intent serviceintent = new Intent(StudentMain.this, MyService.class);
        startService(serviceintent);
        /*Intent serviceintent1 = new Intent(StudentMain.this, MyServicegetSchedule.class);*/
        /*startService(serviceintent1);*/


        first_layout = (LinearLayout)findViewById(R.id.mainVolounteerLayout);
        title = (TextView)findViewById(R.id.title1);
        place = (TextView)findViewById(R.id.place1);
        time = (TextView)findViewById(R.id.time1);
        day = (TextView)findViewById(R.id.day1);
        targetTime = (TextView)findViewById(R.id.yourTarget);
        SearchVolunteerBtn = (Button)findViewById(R.id.SearchVolunteerBtn);
        timeTableBtn = (Button)findViewById((R.id.timeTableBtn));
        openCalendarBtn = (Button)findViewById(R.id.openCalendarBtn);
        mypageBtn = (Button)findViewById(R.id.mypageBtn);
        SearchVolunteerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.SearchInfo");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });
        timeTableBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.TimeTable");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });
        mypageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.MyPage");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });
        openCalendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.Calendar");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });

        first_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
                myAsyncTasks.execute();
                strc = code1;
                data.setProgrmRegistNo(strc);

                Toast.makeText(context_main, data.progrmRegistNo, Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.mainSelectedVolunteer");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });


        //SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        //email = pref.getString(emailkey, "");
        //name = pref.getString(namekey, "");
        //String[] emailsplit = email.split("@");
        //getsplitemail = emailsplit[0];

        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        email = pref.getString(emailkey,"");
        name = pref.getString(namekey,"");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("schedule").child("student");
        final DatabaseReference targetRef = database.getReference("customer").child("targetTime");
        //final DatabaseReference timeTableRef = database.getReference("customer").child("timeTable").child(getsplitemail);
        final DatabaseReference timeTableRef = database.getReference("customer").child("timeTable").child(email+name);

        timeTableRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            for (DataSnapshot weekData : snapshot.getChildren()) {
                                if (Boolean.parseBoolean(weekData.getValue().toString())) {
                                    String weekdy = weekData.getKey().substring(0, 3);
                                    int time = Integer.parseInt(weekData.getKey().substring(3, weekData.getKey().length()));
                                    switch (weekdy) {
                                        case "mon":
                                            mon[time - 1] = true;
                                            checkWeek[0] = true;
                                            break;
                                        case "tue":
                                            tue[time - 1] = true;
                                            checkWeek[1] = true;
                                            break;
                                        case "wed":
                                            wed[time - 1] = true;
                                            checkWeek[2] = true;
                                            break;
                                        case "thu":
                                            thu[time - 1] = true;
                                            checkWeek[3] = true;
                                            break;
                                        case "fri":
                                            fri[time - 1] = true;
                                            checkWeek[4] = true;
                                            break;
                                        case "sat":
                                            sat[time - 1] = true;
                                            checkWeek[5] = true;
                                            break;
                                        case "sun":
                                            sun[time - 1] = true;
                                            checkWeek[6] = true;
                                            break;
                                        default:
                                            break;
                                    }
                                }

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        MyAsyncTaskUseSchedule myAsyncTaskUseSchedule = new MyAsyncTaskUseSchedule();
        myAsyncTaskUseSchedule.execute();
        MyAsyncTaskUseSchedules myAsyncTaskUseSchedules = new MyAsyncTaskUseSchedules();
        myAsyncTaskUseSchedules.execute();
        Log.d("리스트 크기 확인", String.valueOf(filtercount));


        Handler mHandler = new Handler();
        mHandler.postDelayed(new Runnable()  {
            public void run() {
                if(list.get(0).progrmSj==null) title.setText("적절한 봉사활동이 없습니다.");
                else {
                    title1 = list.get(0).progrmSj;
                    Log.d("확인", title1);
                    place1 = list.get(0).actPlace;
                    sTime1 = list.get(0).actBeginTm;
                    eTime1 = list.get(0).actEndTm;
                    sDay1 = list.get(0).progrmBgnde;
                    eDay1 = list.get(0).progrmEndde;
                    code1 = list.get(0).progrmRegistNo;


                    title.setText(title1);
                    place.setText(place1);
                    time.setText(sTime1 + "시 ~ " + eTime1 + "시");
                    day.setText(sDay1 + " ~ " + eDay1 + "");
                }

                // 시간 지난 후 실행할 코딩
            }
        }, 5000);


        targetRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot data : snapshot.getChildren()) {
                    if(data.getKey().equals(name + email))
                        targetTime.setText(String.valueOf(data.getValue()));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                SharedPreferences sPref = getSharedPreferences(schedulePreference, MODE_PRIVATE);
                SharedPreferences.Editor editor = sPref.edit();
                editor.clear();
                editor.commit();
                int count = 1;
                for (DataSnapshot data : snapshot.getChildren()) {
                    String getData[] = data.getValue().toString().split(",");
                    String getEmail = getData[0];
                    String getDate = getData[1];
                    String getTitle = getData[2];
                    String getCode = getData[3];
                    String getTime = getData[4];
                    String getPlace = getData[5];
                    if(getEmail.equals(email)) {
                        editor.putString(emailkey + count, getEmail);
                        editor.commit();
                        editor.putString(dateKey + count, getDate);
                        editor.commit();
                        editor.putString(titleKey + count, getTitle);
                        editor.commit();
                        editor.putString(codeKey + count, getCode);
                        editor.commit();
                        editor.putString(timeKey + count, getTime);
                        editor.commit();
                        editor.putString(placeKey + count, getPlace);
                        editor.commit();
                        count++;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }



    @Override
    public void onRequestPermissionsResult(int permsRequestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grandResults) {

        if ( permsRequestCode == PERMISSIONS_REQUEST_CODE && grandResults.length == REQUIRED_PERMISSIONS.length) {

            // 요청 코드가 PERMISSIONS_REQUEST_CODE 이고, 요청한 퍼미션 개수만큼 수신되었다면

            boolean check_result = true;


            // 모든 퍼미션을 허용했는지 체크합니다.

            for (int result : grandResults) {
                if (result != PackageManager.PERMISSION_GRANTED) {
                    check_result = false;
                    break;
                }
            }


            if ( check_result ) {

                //위치 값을 가져올 수 있음
                ;
            }
            else {
                // 거부한 퍼미션이 있다면 앱을 사용할 수 없는 이유를 설명해주고 앱을 종료합니다.2 가지 경우가 있습니다.

                if (ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[0])
                        || ActivityCompat.shouldShowRequestPermissionRationale(this, REQUIRED_PERMISSIONS[1])) {

                    Toast.makeText(StudentMain.this, "퍼미션이 거부되었습니다. 앱을 다시 실행하여 퍼미션을 허용해주세요.", Toast.LENGTH_LONG).show();
                    finish();


                }else {

                    Toast.makeText(StudentMain.this, "퍼미션이 거부되었습니다. 설정(앱 정보)에서 퍼미션을 허용해야 합니다. ", Toast.LENGTH_LONG).show();

                }
            }

        }
    }

    void checkRunTimePermission(){

        //런타임 퍼미션 처리
        // 1. 위치 퍼미션을 가지고 있는지 체크합니다.
        int hasFineLocationPermission = ContextCompat.checkSelfPermission(StudentMain.this,
                Manifest.permission.ACCESS_FINE_LOCATION);
        int hasCoarseLocationPermission = ContextCompat.checkSelfPermission(StudentMain.this,
                Manifest.permission.ACCESS_COARSE_LOCATION);


        if (hasFineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                hasCoarseLocationPermission == PackageManager.PERMISSION_GRANTED) {

            // 2. 이미 퍼미션을 가지고 있다면
            // ( 안드로이드 6.0 이하 버전은 런타임 퍼미션이 필요없기 때문에 이미 허용된 걸로 인식합니다.)


            // 3.  위치 값을 가져올 수 있음



        } else {  //2. 퍼미션 요청을 허용한 적이 없다면 퍼미션 요청이 필요합니다. 2가지 경우(3-1, 4-1)가 있습니다.

            // 3-1. 사용자가 퍼미션 거부를 한 적이 있는 경우에는
            if (ActivityCompat.shouldShowRequestPermissionRationale(StudentMain.this, REQUIRED_PERMISSIONS[0])) {

                // 3-2. 요청을 진행하기 전에 사용자가에게 퍼미션이 필요한 이유를 설명해줄 필요가 있습니다.
                Toast.makeText(StudentMain.this, "이 앱을 실행하려면 위치 접근 권한이 필요합니다.", Toast.LENGTH_LONG).show();
                // 3-3. 사용자게에 퍼미션 요청을 합니다. 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(StudentMain.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);


            } else {
                // 4-1. 사용자가 퍼미션 거부를 한 적이 없는 경우에는 퍼미션 요청을 바로 합니다.
                // 요청 결과는 onRequestPermissionResult에서 수신됩니다.
                ActivityCompat.requestPermissions(StudentMain.this, REQUIRED_PERMISSIONS,
                        PERMISSIONS_REQUEST_CODE);
            }

        }

    }


    public String getCurrentAddress( double latitude, double longitude) {

        //지오코더... GPS를 주소로 변환
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());

        List<Address> addresses;

        try {

            addresses = geocoder.getFromLocation(
                    latitude,
                    longitude,
                    7);
        } catch (IOException ioException) {
            //네트워크 문제
            Toast.makeText(this, "지오코더 서비스 사용불가", Toast.LENGTH_LONG).show();
            return "지오코더 서비스 사용불가";
        } catch (IllegalArgumentException illegalArgumentException) {
            Toast.makeText(this, "잘못된 GPS 좌표", Toast.LENGTH_LONG).show();
            return "잘못된 GPS 좌표";

        }



        if (addresses == null || addresses.size() == 0) {
            Toast.makeText(this, "주소 미발견", Toast.LENGTH_LONG).show();
            return "";

        }

        Address address = addresses.get(0);
        return address.getAddressLine(0).toString()+"\n";

    }


    //여기부터는 GPS 활성화를 위한 메소드들
    private void showDialogForLocationServiceSetting() {

        AlertDialog.Builder builder = new AlertDialog.Builder(StudentMain.this);
        builder.setTitle("위치 서비스 비활성화");
        builder.setMessage("앱을 사용하기 위해서는 위치 서비스가 필요합니다.\n"
                + "위치 설정을 수정하실래요?");
        builder.setCancelable(true);
        builder.setPositiveButton("설정", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                Intent callGPSSettingIntent
                        = new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivityForResult(callGPSSettingIntent, GPS_ENABLE_REQUEST_CODE);
            }
        });
        builder.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {

            case GPS_ENABLE_REQUEST_CODE:

                //사용자가 GPS 활성 시켰는지 검사
                if (checkLocationServicesStatus()) {
                    if (checkLocationServicesStatus()) {

                        Log.d("@@@", "onActivityResult : GPS 활성화 되있음");
                        checkRunTimePermission();
                        return;
                    }
                }

                break;
        }
    }

    public boolean checkLocationServicesStatus() {
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
                || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }




            // 모든 데이터 삭제
    public void setPreferenceClear(){
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }

    @Override
    public void onBackPressed() {
        // 기존 뒤로가기 버튼의 기능을 막기위해 주석처리 또는 삭제
        //super.onBackPressed();

        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지났으면 Toast Show
        // 2000 milliseconds = 2 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지나지 않았으면 종료
        // 현재 표시된 Toast 취소
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {

            finishAffinity();
            System.runFinalization();
            System.exit(0);
            finish();
            toast.cancel();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_actions, menu); //메뉴 XML파일 인플레이션
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.action_setting:
                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.MyPage");
                intent.setComponent(cmpName);
                startActivity(intent);

        }

        return super.onOptionsItemSelected(item);
    }

    public class MyAsyncTaskUseSchedule extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            requestUrl = "http://openapi.1365.go.kr/openapi/service/rest/VolunteerPartcptnService/getVltrSearchWordList?serviceKey=" + datakey3 +"&schCateGu="+"progrmSj"+"&progrmBgnde="+"&progrmEndde="+"&numOfRows=100"+"&schSido="+sidoCode+"&schSign1="+gugunCode;
            try {
                boolean b_progrmRegistNo = false;

                URL url = new URL(requestUrl);
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(is, "UTF-8"));
                Log.d("URL확인",requestUrl);

                String tag;
                int eventType = parser.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            list = new ArrayList<searchItem>();
                            getCodeCount = 0;
                            for(int i = 0;i<getAll.length;i++){
                                getAll[i] = new SearchData();
                            }
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG:
                            if(parser.getName().equals("item") && bus != null) {
                                list.add(buss);
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if(parser.getName().equals("item")){
                                buss = new searchItem();
                            }
                            if (parser.getName().equals("progrmRegistNo")) b_progrmRegistNo = true;
                            break;
                        case XmlPullParser.TEXT:
                            if(b_progrmRegistNo) {
                                buss.setProgrmRegistNo(parser.getText());
                                getAll[getCodeCount].progrmRegistNo = parser.getText();
                                Log.d("번호 알림" + getCodeCount,getAll[getCodeCount].progrmRegistNo);
                                getCodeCount++;
                                b_progrmRegistNo = false;
                            }
                            break;
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //어답터 연결
        }

    }

    public class MyAsyncTaskUseSchedules extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            for(int i = 0;i<getCodeCount;i++) {
                requestUrl1 = "http://openapi.1365.go.kr/openapi/service/rest/VolunteerPartcptnService/getVltrPartcptnItem?serviceKey=" + dataKey2 + "&progrmRegistNo=" + getAll[i].progrmRegistNo;
                try {
                    boolean c_progrmSj = false;
                    boolean c_actPlace = false;
                    boolean c_progrmBgnde = false;
                    boolean c_progrmEndde = false;
                    boolean c_progrmRegistNo = false;
                    boolean c_actBeginTm = false;
                    boolean c_actEndTm = false;
                    boolean c_actWkdy = false;

                    URL url = new URL(requestUrl1);
                    InputStream is = url.openStream();
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = factory.newPullParser();
                    parser.setInput(new InputStreamReader(is, "UTF-8"));

                    String tag;
                    int eventType = parser.getEventType();

                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType) {
                            case XmlPullParser.START_DOCUMENT:
                                list = new ArrayList<searchItem>();
                                break;
                            case XmlPullParser.END_TAG:
                                if (parser.getName().equals("item") && bus != null) {
                                    list.add(buss);
                                }
                                break;
                            case XmlPullParser.START_TAG:
                                if (parser.getName().equals("item")) {
                                    buss = new searchItem();
                                }
                                if (parser.getName().equals("progrmSj")) {
                                    c_progrmSj = true;
                                }
                                if (parser.getName().equals("actPlace")) {
                                    c_actPlace = true;
                                }
                                if (parser.getName().equals("progrmBgnde")) {
                                    c_progrmBgnde = true;
                                }
                                if (parser.getName().equals("progrmEndde")) {
                                    c_progrmEndde = true;
                                }
                                if (parser.getName().equals("progrmRegistNo")) {
                                    c_progrmRegistNo = true;
                                }
                                if (parser.getName().equals("actBeginTm")) {
                                    c_actBeginTm = true;
                                }
                                if (parser.getName().equals("actEndTm")) {
                                    c_actEndTm = true;
                                }
                                if (parser.getName().equals("actWkdy")) {
                                    c_actWkdy = true;
                                }
                                break;
                            case XmlPullParser.TEXT:
                                if (c_progrmSj) {
                                    getAll[i].progrmSj = parser.getText();
                                    c_progrmSj = false;
                                }
                                if (c_actPlace) {
                                    getAll[i].actPlace = parser.getText();
                                    c_actPlace = false;
                                }
                                if (c_actBeginTm) {
                                    getAll[i].actBeginTm = parser.getText();
                                    c_actBeginTm = false;
                                }
                                if (c_actEndTm) {
                                    getAll[i].actEndTm = parser.getText();
                                    c_actEndTm = false;
                                }
                                if (c_progrmRegistNo) {
                                    getAll[i].progrmRegistNo = parser.getText();
                                    c_progrmRegistNo = false;
                                }
                                if (c_progrmBgnde) {
                                    getAll[i].progrmBgnde = parser.getText();
                                    c_progrmBgnde = false;
                                }
                                if (c_progrmEndde) {
                                    getAll[i].progrmEndde = parser.getText();
                                    c_progrmEndde = false;//////
                                }
                                if (c_actWkdy) {
                                    getAll[i].actWkdy = parser.getText();

                                    c_actWkdy = false;//////
                                }
                                break;
                        }
                        eventType = parser.next();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            filterGetAll();


            return null;
        }

        public void filterGetAll(){
            for(int i = 0;i<getCodeCount;i++){
                boolean checkfilter = false;
                getWeek = new boolean[7];
                gmon = new boolean[14];
                gtue = new boolean[14];
                gwed = new boolean[14];
                gthu = new boolean[14];
                gfri = new boolean[14];
                gsat = new boolean[14];
                gsun = new boolean[14];

                String weekArray = getAll[i].actWkdy;
                if(weekArray.equals("Y")) continue;
                for(int j = 0;j<7;j++){
                    if(weekArray.substring(j,j+1).equals("1")){
                        getWeek[j] = true;
                        switch (j){
                            case 0:
                                for(int k = Integer.parseInt(getAll[i].actBeginTm);k<Integer.parseInt(getAll[i].actEndTm);k++){
                                    if(k>8 && k<23)
                                        gmon[k-9] = true;
                                }
                                break;
                            case 1:
                                for(int k = Integer.parseInt(getAll[i].actBeginTm);k<Integer.parseInt(getAll[i].actEndTm);k++){
                                    if(k>8 && k<23)
                                        gtue[k-9] = true;
                                }
                                break;
                            case 2:
                                for(int k = Integer.parseInt(getAll[i].actBeginTm);k<Integer.parseInt(getAll[i].actEndTm);k++){
                                    if(k>8 && k<23)
                                        gwed[k-9] = true;
                                }
                                break;
                            case 3:
                                for(int k = Integer.parseInt(getAll[i].actBeginTm);k<Integer.parseInt(getAll[i].actEndTm);k++){
                                    if(k>8 && k<23)
                                        gthu[k-9] = true;
                                }
                                break;
                            case 4:
                                for(int k = Integer.parseInt(getAll[i].actBeginTm);k<Integer.parseInt(getAll[i].actEndTm);k++){
                                    if(k>8 && k<23)
                                        gfri[k-9] = true;
                                }
                                break;
                            case 5:
                                for(int k = Integer.parseInt(getAll[i].actBeginTm);k<Integer.parseInt(getAll[i].actEndTm);k++){
                                    if(k>8 && k<23)
                                        gsat[k-9] = true;
                                }
                                break;
                            case 6:
                                for(int k = Integer.parseInt(getAll[i].actBeginTm);k<Integer.parseInt(getAll[i].actEndTm);k++){
                                    if(k>8 && k<23)
                                        gsun[k-9] = true;
                                }
                                break;
                        }
                    }
                }

                for(int j = 0;j<7;j++){
                    switch(j){
                        case 0 :
                            for(int k = 0;k<14;k++){
                                if(gmon[k] == true && mon[k] == true) {
                                    checkfilter = true;
                                    break;
                                }
                                else {
                                }
                            }
                            break;
                        case 1 :
                            for(int k = 0;k<14;k++){
                                if(gtue[k] == true && tue[k] == true) {
                                    checkfilter = true;
                                    break;
                                }
                                else {
                                }
                            }
                            break;
                        case 2 :
                            for(int k = 0;k<14;k++){
                                if(gwed[k] == true && wed[k] == true) {
                                    checkfilter = true;
                                    break;
                                }
                                else {
                                }
                            }
                            break;
                        case 3 :
                            for(int k = 0;k<14;k++){
                                if(gthu[k] == true && thu[k] == true) {
                                    checkfilter = true;
                                    break;
                                }
                                else {
                                }
                            }
                            break;
                        case 4 :
                            for(int k = 0;k<14;k++){
                                if(gfri[k] == true && fri[k] == true) {
                                    checkfilter = true;
                                    break;
                                }
                                else {
                                }
                            }
                            break;
                        case 5 :
                            for(int k = 0;k<14;k++){
                                if(gsat[k] == true && sat[k] == true) {
                                    checkfilter = true;
                                    break;
                                }
                                else {
                                }
                            }
                            break;
                        case 6 :
                            for(int k = 0;k<14;k++){
                                if(gsun[k] == true && sun[k] == true) {
                                    checkfilter = true;
                                    break;
                                }
                                else {
                                }
                            }
                            break;

                    }


                    if(filtercount>=99)
                        break;
                }
                if(!checkfilter){
                    scheduleFiltered[filtercount] = getAll[i];
                    filtercount++;
                }
                if(filtercount>=99)
                    break;
            }
            Log.d("필터 확인",String.valueOf(filtercount));
            for(int i = 0;i<7;i++){
                switch (i){
                    case 0 :
                        for(int j = 0;j<14;j++){
                            if(gmon[j])
                                Log.d("mon" + j,"true");
                            else
                                Log.d("mon","false");
                        }
                        break;
                    case 1 :
                        for(int j = 0;j<14;j++){
                            if(gtue[j])
                                Log.d("tue" + j,"true");
                            else
                                Log.d("tue","false");
                        }
                        break;
                    case 2 :
                        for(int j = 0;j<14;j++){
                            if(gwed[j])
                                Log.d("wed" + j,"true");
                            else
                                Log.d("wed","false");
                        }
                        break;
                    case 3 :
                        for(int j = 0;j<14;j++){
                            if(gthu[j])
                                Log.d("thu" + j,"true");
                            else
                                Log.d("thu","false");
                        }
                        break;
                    case 4 :
                        for(int j = 0;j<14;j++){
                            if(gfri[j])
                                Log.d("fri" + j,"true");
                            else
                                Log.d("fri","false");
                        }
                        break;
                    case 5 :
                        for(int j = 0;j<14;j++){
                            if(gsat[j])
                                Log.d("sat" + j,"true");
                            else
                                Log.d("sat","false");
                        }
                        break;
                    case 6 :
                        for(int j = 0;j<14;j++){
                            if(gsun[j])
                                Log.d("sun" + j,"true");
                            else
                                Log.d("sun","false");
                        }
                        break;
                }
            }
            for(int i = 0;i<7;i++){
                switch (i){
                    case 0 :
                        for(int j = 0;j<14;j++){
                            if(mon[j])
                                Log.d("mon" + j,"true");
                            else
                                Log.d("mon","false");
                        }
                        break;
                    case 1 :
                        for(int j = 0;j<14;j++){
                            if(tue[j])
                                Log.d("tue" + j,"true");
                            else
                                Log.d("tue","false");
                        }
                        break;
                    case 2 :
                        for(int j = 0;j<14;j++){
                            if(wed[j])
                                Log.d("wed" + j,"true");
                            else
                                Log.d("wed","false");
                        }
                        break;
                    case 3 :
                        for(int j = 0;j<14;j++){
                            if(thu[j])
                                Log.d("thu" + j,"true");
                            else
                                Log.d("thu","false");
                        }
                        break;
                    case 4 :
                        for(int j = 0;j<14;j++){
                            if(fri[j])
                                Log.d("fri" + j,"true");
                            else
                                Log.d("fri","false");
                        }
                        break;
                    case 5 :
                        for(int j = 0;j<14;j++){
                            if(sat[j])
                                Log.d("sat" + j,"true");
                            else
                                Log.d("sat","false");
                        }
                        break;
                    case 6 :
                        for(int j = 0;j<14;j++){
                            if(sun[j])
                                Log.d("sun" + j,"true");
                            else
                                Log.d("sun","false");
                        }
                        break;
                }

            }
            /*for(int i = 0;i<7;i++){
                if(getWeek[i] == true){
                    switch (i){
                        case 0:

                            break;
                    }
                }
            }*/
            list = new ArrayList<searchItem>();
            for (int i = 0; i < filtercount; i++) {
                buss = new searchItem();
                buss.progrmSj = scheduleFiltered[i].progrmSj;
                buss.actPlace = scheduleFiltered[i].actPlace;
                buss.actBeginTm = scheduleFiltered[i].actBeginTm;
                buss.actEndTm = scheduleFiltered[i].actEndTm;
                buss.progrmBgnde = scheduleFiltered[i].progrmBgnde;
                buss.progrmEndde = scheduleFiltered[i].progrmEndde;
                buss.progrmRegistNo = scheduleFiltered[i].progrmRegistNo;
                Log.d("리스트 추가 확인", scheduleFiltered[i].progrmSj);
                list.add(buss);
            }
        }


        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //어답터 연결
        }

    }
    public class MyAsyncTask1 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            requestUrl2 = "http://openapi.1365.go.kr/openapi/service/rest/CodeInquiryService/getAreaCodeInquiryList?" +
                    "serviceKey=" + dataKey + "&schSido=" + sido;
            try {
                boolean b_sidoCd = false;



                URL url = new URL(requestUrl2);
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(is, "UTF-8"));

                String tag;
                int eventType = parser.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG:
                            if (parser.getName().equals("item") && busSido != null) {
                                sidoCode = busSido.sidoCd;
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if (parser.getName().equals("item")) {
                                busSido = new addrCodeItem();
                            }
                            if (parser.getName().equals("sidoCd")) b_sidoCd = true;

                            break;
                        case XmlPullParser.TEXT:

                            if (b_sidoCd) {
                                busSido.setSidoCd(parser.getText());
                                b_sidoCd = false;
                            }
                            break;
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


    }

    public class MyAsyncTask2 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            requestUrl3 = "http://openapi.1365.go.kr/openapi/service/rest/CodeInquiryService/getAreaCodeInquiryList?" +
                    "serviceKey=" + dataKey + "&schGugun="+gugun;
            try {
                boolean b_gugunCd = false;



                URL url = new URL(requestUrl3);
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(is, "UTF-8"));

                String tag;
                int eventType = parser.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG:
                            if(parser.getName().equals("item") && busGugun != null) {
                                gugunCode = busGugun.gugunCd;
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if(parser.getName().equals("item")){
                                busGugun = new addrCodeItem();
                            }
                            if (parser.getName().equals("gugunCd")) b_gugunCd = true;

                            break;
                        case XmlPullParser.TEXT:

                            if(b_gugunCd){
                                busGugun.setGugunCd(parser.getText());
                                b_gugunCd = false;
                            }
                            break;
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

    }
    public class MyAsyncTasks extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            requestUrl1 = "http://openapi.1365.go.kr/openapi/service/rest/VolunteerPartcptnService/getVltrPartcptnItem?serviceKey=" + dataKey2 + "&progrmRegistNo=" + strc;
            try {
                boolean c_progrmSj = false;
                boolean c_actPlace =false;
                boolean c_progrmBgnde = false;
                boolean c_progrmEndde = false;
                boolean c_progrmRegistNo = false;
                boolean c_actBeginTm = false;
                boolean c_actEndTm =false;
                boolean c_noticeBgnde = false;
                boolean c_noticeEndde = false;
                boolean c_rcritNmpr = false;
                boolean c_appTotal = false;
                boolean c_actWkdy =false;
                boolean c_srvcClCode = false;
                boolean c_mnnstNm = false;
                boolean c_nanmmbyNm = false;
                boolean c_telno = false;
                boolean c_email =false;
                boolean c_progrmCn = false;
                boolean c_progrmLink = false;

                URL url = new URL(requestUrl1);
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(is, "UTF-8"));

                String tag;
                int eventType = parser.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            list = new ArrayList<searchItem>();
                            break;
                        case XmlPullParser.END_TAG:
                            if (parser.getName().equals("item") && bus != null) {
                                list.add(bus);
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if (parser.getName().equals("item")) {
                                bus = new searchItem();
                            }
                            if (parser.getName().equals("progrmSj")) {
                                c_progrmSj = true;
                            }
                            if (parser.getName().equals("actPlace")) {
                                c_actPlace = true;
                            }
                            if (parser.getName().equals("progrmBgnde")) {
                                c_progrmBgnde = true;
                            }
                            if (parser.getName().equals("progrmEndde")) {
                                c_progrmEndde = true;
                            }
                            if (parser.getName().equals("progrmRegistNo")) {
                                c_progrmRegistNo = true;
                            }
                            if (parser.getName().equals("actBeginTm")) {
                                c_actBeginTm = true;
                            }
                            if (parser.getName().equals("actEndTm")) {
                                c_actEndTm = true;
                            }
                            if (parser.getName().equals("noticeBgnde")) {
                                c_noticeBgnde = true;
                            }
                            if (parser.getName().equals("noticeEndde")) {
                                c_noticeEndde = true;
                            }
                            if (parser.getName().equals("rcritNmpr")) {
                                c_rcritNmpr = true;
                            }
                            if (parser.getName().equals("appTotal")) {
                                c_appTotal = true;
                            }
                            if (parser.getName().equals("actWkdy")) {
                                c_actWkdy = true;
                            }
                            if (parser.getName().equals("srvcClCode")) {
                                c_srvcClCode = true;
                            }
                            if (parser.getName().equals("mnnstNm")) c_mnnstNm = true;
                            if (parser.getName().equals("nanmmbyNm")) c_nanmmbyNm = true;
                            if (parser.getName().equals("telno")) c_telno = true;
                            if (parser.getName().equals("email")) c_email = true;
                            if (parser.getName().equals("progrmCn")) {
                                c_progrmCn = true;
                            }
                            if (parser.getName().equals("progrmLink")) {c_progrmLink = true;
                            }
                            break;
                        case XmlPullParser.TEXT:
                            if(c_progrmSj){
                                data.setProgrmSj(parser.getText());
                                c_progrmSj = false;
                            }if(c_actPlace) {
                            data.setActPlace(parser.getText());
                            c_actPlace = false;
                        }if (c_progrmBgnde) {
                            data.setProgrmBgnde(parser.getText());
                            c_progrmBgnde = false;
                        }if(c_progrmEndde) {
                            data.setProgrmEndde(parser.getText());
                            c_progrmEndde = false;
                        }if(c_progrmRegistNo) {
                            data.setProgrmRegistNo(parser.getText());
                            c_progrmRegistNo = false;
                        }if(c_actBeginTm) {
                            data.setActBeginTm(parser.getText());
                            c_actBeginTm = false;
                        }if (c_actEndTm) {
                            data.setActEndTm(parser.getText());
                            c_actEndTm = false;
                        }if(c_noticeBgnde) {
                            data.setNoticeBgnde(parser.getText());
                            c_noticeBgnde = false;
                        }if(c_noticeEndde) {
                            data.setNoticeEndde(parser.getText());
                            c_noticeEndde = false;
                        }if(c_rcritNmpr) {
                            data.setRcritNmpr(parser.getText());
                            c_rcritNmpr = false;
                        }if (c_appTotal) {
                            data.setAppTotal(parser.getText());
                            c_appTotal = false;
                        }if(c_actWkdy) {
                            data.setActWkdy(parser.getText());
                            c_actWkdy = false;
                        }if(c_srvcClCode) {
                            data.setSrvcClCode(parser.getText());
                            c_srvcClCode = false;
                        }if(c_mnnstNm) {
                            data.setMnnstNm(parser.getText());
                            c_mnnstNm = false;
                        }if (c_nanmmbyNm) {
                            data.setNanmmbyNm(parser.getText());
                            c_nanmmbyNm = false;
                        }if(c_telno) {
                            data.setTelno(parser.getText());
                            c_telno = false;
                        }if(c_email) {
                            data.seTemail(parser.getText());
                            c_email = false;
                        }if(c_progrmCn) {
                            data.setProgrmCn(parser.getText());
                            c_progrmCn = false;
                        }if(c_progrmLink) {
                            data.setProgrmLink(parser.getText());
                            c_progrmLink = false;
                        }
                            break;
                    }
                    eventType = parser.next();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            //어답터 연결
        }

    }


}
