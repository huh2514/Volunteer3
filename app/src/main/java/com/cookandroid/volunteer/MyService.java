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
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MyService extends Service {
    private static final MyService ourInstance = new MyService();

    public final String PREFERENCE = "com.volunteerLogin.samplesharepreference";
    public final String schedulePreference = "com.volunteerLogin.schedulePreference";
    public final String key01 = "Email";

    public static MyService getInstance() {
        return ourInstance;
    }

    public MyService() {
    }
    NotificationManager Notifi_M;
    ServiceThread thread;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("backgroundservice","시작");
        Notifi_M = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        myServiceHandler handler = new myServiceHandler();
        thread = new ServiceThread(handler);
        thread.stopForever();
        return START_STICKY;
    }

    //서비스가 종료될 때 할 작업
    public void onDestroy() {
        myServiceHandler handler = new myServiceHandler();
        thread = new ServiceThread(handler);
        thread.start();
    }

    public void start() {
        myServiceHandler handler = new myServiceHandler();
        thread = new ServiceThread(handler);
        thread.start();
    }

    public void top() {
        myServiceHandler handler = new myServiceHandler();
        thread = new ServiceThread(handler);
        thread.stopForever();
    }

    public class myServiceHandler extends Handler {
        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public void handleMessage(android.os.Message msg) {
/*
            SharedPreferences.Editor editor = backpref.edit();
            editor.putString("noti","running");*/
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

            Intent intent = new Intent(MyService.this, StudentMain.class);

            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, 0, intent, PendingIntent.FLAG_ONE_SHOT);
            Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel("my_notification", "n_channel", NotificationManager.IMPORTANCE_MAX);
                notificationChannel.setDescription("description");
                notificationChannel.setName("Channel Name");
                assert notificationManager != null;
                notificationManager.createNotificationChannel(notificationChannel);
            }

            SharedPreferences sPref = getSharedPreferences(schedulePreference, MODE_PRIVATE);
            for(int count = 1;count==count;count++){

                String getDate = sPref.getString("Date" + count,"");
                String getTime = sPref.getString("Time" + count,"");
                if(getDate == "")
                    break;
                Date currentTime = Calendar.getInstance().getTime();
                SimpleDateFormat weekdayFormat = new SimpleDateFormat("EE", Locale.getDefault());
                SimpleDateFormat dayFormat = new SimpleDateFormat("dd", Locale.getDefault());
                SimpleDateFormat monthFormat = new SimpleDateFormat("MM", Locale.getDefault());
                SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy", Locale.getDefault());

                String weekDay = weekdayFormat.format(currentTime);
                String year = yearFormat.format(currentTime);
                String month = monthFormat.format(currentTime);
                String day = dayFormat.format(currentTime);

                String getsDate = getDate.substring(0, 8);
                String getsTime = getTime.substring(0, 2);

                Log.d("webnautes", year + "년 " + month + "월 " + day + "일 " + weekDay + "요일");
                Log.d("date",getsDate);
                Log.d("time",getsTime);

                Calendar cal = Calendar.getInstance();
                if(getsDate.equals(year + month + day) && getsTime.equals(String.valueOf(cal.get(Calendar.HOUR_OF_DAY)))){
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(MyService.this,"default")
                            .setSmallIcon(R.drawable.pengu)
                            .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.pengu))
                            .setContentTitle("봉사활동앱")
                            .setContentText("봉사활동할 시간입니다")
                            .setAutoCancel(true)
                            .setSound(soundUri)
                            .setContentIntent(pendingIntent)
                            .setDefaults(Notification.DEFAULT_ALL)
                            .setOnlyAlertOnce(true).setChannelId("my_notification")
                            .setColor(Color.parseColor("#ffffff"));
                    //.setProgress(100,50,false);


                    assert notificationManager != null;
                    int m = (int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE);
/*                    Calendar cal = Calendar.getInstance();
                    int hour = cal.get(Calendar.HOUR_OF_DAY);
                    int min = cal.get(Calendar.MINUTE);
                    if ((hour == 20 || hour == 10 ) && (min == 57 || min == 23)) {*/
                        Log.d("시간","됨");
                        notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
                        notificationManager.notify(m, notificationBuilder.build());
                        thread.stopForever();
/*                    }*/
                }

            }


        }
    }

}
