package com.cookandroid.volunteer;


import android.os.Handler;
import android.util.Log;

public class ServiceThread1 extends Thread {
    Handler handler1;
    boolean isRun = true;

    public ServiceThread1(Handler handler) {
        this.handler1 = handler;
    }
    public void stopForever() {
        synchronized (this) {
            this.isRun = false;
        }
    } public void run() {
        //반복적으로 수행할 작업을 한다.
        while (isRun) {
            handler1.sendEmptyMessage( 0 );
            Log.d("알림","스케쥴 업데이트 자동 실행중");
            //쓰레드에 있는 핸들러에게 메세지를 보냄
            try { Thread.sleep( 10000 );
                //10초씩 쉰다.
            } catch (Exception e) { }
        }
    }
}
