package com.cookandroid.volunteer;


import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class ResultView extends AppCompatActivity {
    SearchData data = new SearchData();
    static String TAG = "ResultView";
    public String dataKey = "99scXLkolGPdepyUojDC%2BsBNxExtjmJaDwjRHkpJoVZ0yJ49tcKZk6r%2BM3y77";
    public String dataKey2 = "99scXLkolGPdepyUojDC%2BsBNxExtjmJaDwjRHkpJoVZ0yJ49tcKZk6r%2BM3y77UcfpaxvdLBlU%2F8nkuR2mm%2BG9Q%3D%3D";
    private String requestUrl;
    private String requestUrl1;
    String strc;
    ArrayList<searchItem> list = null;
    searchItem bus = null;
    RecyclerView recyclerView;
    EditText editText;
    public static Context context_main;

    String sDate = ((SearchInfo)SearchInfo.context_main).sDate;
    String eDate = ((SearchInfo)SearchInfo.context_main).eDate;
    String searchText = ((SearchInfo)SearchInfo.context_main).editText.getText().toString();
    String sido = ((SearchInfo)SearchInfo.context_main).sidoCode;
    String gugun = ((SearchInfo)SearchInfo.context_main).gugunCode;
    String sel1Save = ((SearchInfo)SearchInfo.context_main).sel1Save;
    String sel2Save = ((SearchInfo)SearchInfo.context_main).sel2Save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_view);

        context_main = this;
        //if(sido==null)sido="";
        //if(gugun==null)gugun ="";
        Log.v("1",sel1Save);
        //Log.v("1",sido);
        Log.v("1",gugun);



        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

//        AsyncTask
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
    }

    public class MyAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {




            requestUrl = "http://openapi.1365.go.kr/openapi/service/rest/VolunteerPartcptnService/getVltrSearchWordList?serviceKey=" + dataKey + "&keyword="+searchText+"&schCateGu="+"progrmSj"+"&progrmBgnde="+sDate+"&progrmEndde="+eDate+"&numOfRows=100"+"&schSido="+sido+"&schSign1="+gugun;
                        try {
                boolean b_progrmSj = false;
                boolean b_actPlace =false;
                boolean b_progrmBgnde = false;
                boolean b_progrmEndde = false;
                boolean b_progrmRegistNo = false;

                URL url = new URL(requestUrl);
                InputStream is = url.openStream();
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();
                parser.setInput(new InputStreamReader(is, "UTF-8"));

                String tag;
                int eventType = parser.getEventType();

                while(eventType != XmlPullParser.END_DOCUMENT){
                    switch (eventType){
                        case XmlPullParser.START_DOCUMENT:
                            list = new ArrayList<searchItem>();
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.END_TAG:
                            if(parser.getName().equals("item") && bus != null) {
                                list.add(bus);
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if(parser.getName().equals("item")){
                                bus = new searchItem();
                            }
                            if (parser.getName().equals("progrmSj")) b_progrmSj = true;
                            if (parser.getName().equals("actPlace")) b_actPlace = true;
                            if (parser.getName().equals("progrmBgnde")) b_progrmBgnde = true;
                            if (parser.getName().equals("progrmEndde")) b_progrmEndde = true;
                            if (parser.getName().equals("progrmRegistNo")) b_progrmRegistNo = true;
                            break;
                        case XmlPullParser.TEXT:

                            if(b_progrmSj){
                                bus.setProgrmSj(parser.getText());
                                b_progrmSj = false;
                            } else if(b_actPlace) {
                                bus.setActPlace(parser.getText());
                                b_actPlace = false;
                            } else if (b_progrmBgnde) {
                                bus.setProgrmBgnde(parser.getText());
                                b_progrmBgnde = false;
                            } else if(b_progrmEndde) {
                                bus.setProgrmEndde(parser.getText());
                                b_progrmEndde = false;
                            } else if(b_progrmRegistNo) {
                                bus.setProgrmRegistNo(parser.getText());
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
            MyAdapter adapter = new MyAdapter(getApplicationContext(), list);
            recyclerView.setAdapter(adapter);
        }

    }

    public void select(String strcode){

        MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
        myAsyncTasks.execute();
        strc = strcode;
        data.setProgrmRegistNo(strc);

        Toast.makeText(context_main, data.progrmRegistNo, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                "com.cookandroid.volunteer.SelectedVolunteer");
        intent.setComponent(cmpName);
        startActivity(intent);

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
                                Log.d("알람", "제목");
                            }
                            if (parser.getName().equals("actPlace")) {
                                c_actPlace = true;
                                Log.d("알람", "장소");
                            }
                            if (parser.getName().equals("progrmBgnde")) {
                                c_progrmBgnde = true;
                                Log.d("알람", "시작날짜");
                            }
                            if (parser.getName().equals("progrmEndde")) {
                                c_progrmEndde = true;
                                Log.d("알람", "종료날짜");
                            }
                            if (parser.getName().equals("progrmRegistNo")) {
                                c_progrmRegistNo = true;
                                Log.d("알람", "코드");
                            }
                            if (parser.getName().equals("actBeginTm")) {
                                c_actBeginTm = true;
                                Log.d("알람", "시작시간");
                            }
                            if (parser.getName().equals("actEndTm")) {
                                c_actEndTm = true;
                                Log.d("알람", "종료시간");
                            }
                            if (parser.getName().equals("noticeBgnde")) {
                                c_noticeBgnde = true;
                                Log.d("알람", "모집기간");
                            }
                            if (parser.getName().equals("noticeEndde")) {
                                c_noticeEndde = true;
                                Log.d("알람", "마감기간");
                            }
                            if (parser.getName().equals("rcritNmpr")) {
                                c_rcritNmpr = true;
                                Log.d("알람", "모집인원");
                            }
                            if (parser.getName().equals("appTotal")) {
                                c_appTotal = true;
                                Log.d("알람", "신청자수");
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
                                Log.d("알람", "내용");
                            }
                            if (parser.getName().equals("progrmLink")) {c_progrmLink = true;
                                Log.d("알람", "링크");
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