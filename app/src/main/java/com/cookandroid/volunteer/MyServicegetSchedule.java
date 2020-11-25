package com.cookandroid.volunteer;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
public class MyServicegetSchedule extends Service {
    private static final MyServicegetSchedule ourInstance1 = new MyServicegetSchedule();

    public static MyServicegetSchedule getInstance() {
        return ourInstance1;
    }

    public MyServicegetSchedule() {
    }
    NotificationManager Notifi_M;
    ServiceThread1 thread1;

    //스케쥴 데이터 가져올떄
    String myJSON;
    String email;
    JSONArray peoples = null;
    private static final String TAG_RESULTS = "result";
    private static final String TAG_vDate = "vDate";
    private static final String TAG_vPlace = "vPlace";
    private static final String TAG_vCode = "vCode";
    private static final String TAG_Email = "Email";

    public final String PREFERENCE = "com.volunteerLogin.samplesharepreference";
    public final String schedulePreference = "com.volunteerLogin.schedulePreference";

    //로그인 정보
    public final String key01 = "Email";
    public final String key02 = "pwd";

    //봉사활동 스케쥴 정보
    public final String scheduleKey01 = "Email";
    public String schedulegetEmail="em";


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("backgroundservice2","시작");
        myServiceHandler1 handler1 = new myServiceHandler1();
        thread1 = new ServiceThread1(handler1);
        thread1.stopForever();
        return START_STICKY;
    }

    //서비스가 종료될 때 할 작업
    public void onDestroy() {
        myServiceHandler1 handler1 = new myServiceHandler1();
        thread1 = new ServiceThread1(handler1);
        thread1.start();
    }

    public void start() {
        myServiceHandler1 handler1 = new myServiceHandler1();
        thread1 = new ServiceThread1(handler1);
        thread1.start();
    }

    public void top() {
        myServiceHandler1 handler1 = new myServiceHandler1();
        thread1 = new ServiceThread1(handler1);
        thread1.stopForever();
    }

    public class myServiceHandler1 extends Handler {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void handleMessage(android.os.Message msg) {
            checkoutSchedule(key01);
        }
    }

    public void checkoutSchedule(String emailkey){
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        schedulegetEmail = pref.getString(emailkey,"");
        Log.d("스케쥴 체크아웃",schedulegetEmail);

        getData("http://192.168.142.97/PHP_connection_schedules.php");
    }

    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);
            SharedPreferences pref = getSharedPreferences(schedulePreference, MODE_PRIVATE);
            SharedPreferences.Editor editor = pref.edit();
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

    public void getData(String url) {
        class GetDataJSON extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {

                String uri = params[0];

                BufferedReader bufferedReader = null;
                try {
                    URL url = new URL(uri);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();

                    bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }

                    return sb.toString().trim();

                } catch (Exception e) {
                    return null;
                }

            }

            @Override
            protected void onPostExecute(String result) {
                myJSON = result;
                showList();
            }
        }
        GetDataJSON g = new GetDataJSON();
        g.execute(url);
    }


}
