package com.cookandroid.volunteer;


import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;

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

    static String TAG = "ResultView";
    public String dataKey = "99scXLkolGPdepyUojDC%2BsBNxExtjmJaDwjRHkpJoVZ0yJ49tcKZk6r%2BM3y77";
    private String requestUrl;
    ArrayList<searchItem> list = null;
    searchItem bus = null;
    RecyclerView recyclerView;
    EditText editText;

    String sDate = ((SearchInfo)SearchInfo.context_main).sDate;
    String eDate = ((SearchInfo)SearchInfo.context_main).eDate;
    String searchText = ((SearchInfo)SearchInfo.context_main).editText.getText().toString();
    String sido = ((SearchInfo)SearchInfo.context_main).sidoCode;
    String gugun = ((SearchInfo)SearchInfo.context_main).gugunCode;
    String sel2Save = ((SearchInfo)SearchInfo.context_main).sel2Save;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_view);

        if(sDate=="시작일선택") sDate=null;
        if(eDate=="종료일선택") eDate=null;


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
}