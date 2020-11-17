package com.cookandroid.volunteer;

import androidx.appcompat.app.AppCompatActivity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class SearchInfo extends AppCompatActivity {

    public static Context context_main;
    public String dataKey = "99scXLkolGPdepyUojDC%2BsBNxExtjmJaDwjRHkpJoVZ0yJ49tcKZk6r%2BM3y77UcfpaxvdLBlU%2F8nkuR2mm%2BG9Q%3D%3D";
    int sy=0, sm=0, sd=0, ey=0, em=0, ed=0;
    private String requestUrl;
    private String requestUrl1;
    addrCodeItem bus = null;
    addrCodeItem bus1 = null;
    int today_year,today_month,today_day,today_year1,today_month1,today_day1;
    String today;
    String sel1Save, sel2Save, sidoCode, gugunCode;
    Spinner Sido,Gugun;
    EditText editText;
    String sDate;
    String eDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_info);
        context_main = this;

        editText = (EditText)findViewById(R.id.searchText);
        Sido = (Spinner) findViewById(R.id.Sido);
        Gugun = (Spinner) findViewById(R.id.Gugun);

        GregorianCalendar toDayMan = new GregorianCalendar();
        today_year1 = toDayMan.get(toDayMan.YEAR);  //년
        today_month1 = toDayMan.get(toDayMan.MONTH);//월
        today_day1 = toDayMan.get(toDayMan.DAY_OF_MONTH); // 일 int 값으로 불러오기
        today_year = toDayMan.get(toDayMan.YEAR);  //년
        today_month = toDayMan.get(toDayMan.MONTH)+1;//월
        today_day = toDayMan.get(toDayMan.DAY_OF_MONTH); // 일 int 값으로 불러오기
        sDate = Integer.toString(today_year) + Integer.toString(today_month) + Integer.toString(today_day);
        eDate = Integer.toString(today_year) + Integer.toString(today_month) + Integer.toString(today_day);


        Button sButton = findViewById(R.id.sBtn);
        sButton.setText(sDate);
        sButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sShowDate();

            }

        });

        Button eButton = findViewById(R.id.eBtn);
        eButton.setText(eDate);
        eButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eShowDate();
            }
        });

        Button searchBtn = findViewById(R.id.searchBtn);
        ;
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ResultView.class);
                startActivity(intent);
            }
        });

        final String[] addr = {"전체"};
        final String[] seoul = {"전체","종로구","중구","용산구","성동구","광진구","동대문구","중랑구","성북구","강북구","도봉구","노원구","은평구","서대문구","마포구","양천구","강서구","구로구","금천구","영등포구","동작구","관악구","서초구","강남구","송파구","강동구"};
        final String[] busan = {"전체","중구","서구","동구","영도구","부산진구","동래구","남구","북구","강서구","해운대구","사하구","금정구","연제구","수영구","사상구"};
        final String[] daegu = {"전체","중구","동구","서구","남구","북구","수성구","달서구","달성군"};
        final String[] incheon = {"전체","중구","동구","미추홀구","연수구","남동구","부평구","계양구","서구","강화군","옹진군"};
        final String[] gwangju = {"전체","동구","서구","남구","북구","광산구"};
        final String[] daejeon = {"전체","동구","중구","서구","유성구","대덕구"};
        final String[] ulsan = {"전체","중구","남구","동구","북구","울주군"};
        final String[] sejong = {"전체","세종시"};
        final String[] gyeonggi = {"전체","수원시","성남시","안양시","안산시","용인시","부천시","광명시","평택시","과천시","오산시","시흥시","군포시","의왕시","하남시","이천시","안성시","김포시","화성시","광주시","여주시","양평군","고양시","의정부시","동두천시","구리시","남양주시","파주시","양주시","포천시","연천군","가평군"};
        final String[] gangwon = {"전체","춘천시","원주시","강릉시","동해시","태백시","속초시","삼척시","홍천군","횡성군","영월군","평창군","정선군","철원군","화천군","양구군","인제군","고성군","양양군"};
        final String[] chungbuk = {"전체","청주시","충주시","제천시","보은군","옥천군","영동군","증평군","진천군","괴산군","음성군","단양군"};
        final String[] chungnam = {"전체","천안시","공주시","보령시","아산시","서산시","논산시","계룡시","당진시","금산군","부여군","서천군","청양군","홍성군","예산군","태안군"};
        final String[] jeonbuk = {"전체","전주시","군산시","익산시","정읍시","남원시","김제시","완주군","진안군","무주군","장수군","임실군","순창군","고창군","부안군"};
        final String[] jeonnam = {"전체","목포시","여수시","순천시","나주시","광양시","담양군","곡성군","구례군","고흥군","보성군","화순군","장흥군","강진군","해남군","영암군","무안군","함평군","영광군","장성군","완도군","진도군","신안군"};
        final String[] gyeongbuk = {"전체","포항시","경주시","김천시","안동시","구미시","영주시","영천시","상주시","문경시","경산시","군위군","의성군","청송군","영양군","영덕군","청도군","고령군","성주군","칠곡군","예천군","봉화군","울진군","울릉군"};
        final String[] gyeongnam = {"전체","창원시","진주시","통영시","사천시","김해시","밀양시","거제시","양산시","의령군","함안군","창녕군","고성군","남해군","하동군","산청군","함양군","거창군","합천군"};
        final String[] jeju = {"전체","제주시","서귀포시"};

        final ArrayAdapter adapter0 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,addr);
        final ArrayAdapter adapter1 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,seoul);
        final ArrayAdapter adapter2= new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,busan);
        final ArrayAdapter adapter3 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,daegu);
        final ArrayAdapter adapter4 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,incheon);
        final ArrayAdapter adapter5 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,gwangju);
        final ArrayAdapter adapter6 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,daejeon);
        final ArrayAdapter adapter7 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,ulsan);
        final ArrayAdapter adapter8 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,sejong);
        final ArrayAdapter adapter9 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,gyeonggi);
        final ArrayAdapter adapter10 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,gangwon);
        final ArrayAdapter adapter11 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,chungbuk);
        final ArrayAdapter adapter12 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,chungnam);
        final ArrayAdapter adapter13 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,jeonbuk);
        final ArrayAdapter adapter14 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,jeonnam);
        final ArrayAdapter adapter15 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,gyeongbuk);
        final ArrayAdapter adapter16 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,gyeongnam);
        final ArrayAdapter adapter17 = new ArrayAdapter(this,R.layout.support_simple_spinner_dropdown_item,jeju);

        Sido.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                sel1Save = adapterView.getItemAtPosition(i).toString();
                switch (sel1Save){
                    case "전체" :
                        Gugun.setAdapter(adapter0);
                        sidoCode = "";
                        break;
                    case "서울특별시" :
                        Gugun.setAdapter(adapter1);
                        break;
                    case "부산광역시" :
                        Gugun.setAdapter(adapter2);
                        break;
                    case "대구광역시" :
                        Gugun.setAdapter(adapter3);
                        break;
                    case "인천광역시" :
                        Gugun.setAdapter(adapter4);
                        break;
                    case "광주광역시" :
                        Gugun.setAdapter(adapter5);
                        break;
                    case "대전광역시" :
                        Gugun.setAdapter(adapter6);
                        break;
                    case "울산광역시" :
                        Gugun.setAdapter(adapter7);
                        break;
                    case "세종특별자치시" :
                        Gugun.setAdapter(adapter8);
                        break;
                    case "경기도" :
                        Gugun.setAdapter(adapter9);
                        break;
                    case "강원도" :
                        Gugun.setAdapter(adapter10);
                        break;
                    case "충청북도" :
                        Gugun.setAdapter(adapter11);
                        break;
                    case "충청남도" :
                        Gugun.setAdapter(adapter12);
                        break;
                    case "전라북도" :
                        Gugun.setAdapter(adapter13);
                        break;
                    case "전라남도" :
                        Gugun.setAdapter(adapter14);
                        break;
                    case "경상북도" :
                        Gugun.setAdapter(adapter15);
                        break;
                    case "경상남도" :
                        Gugun.setAdapter(adapter16);
                        break;
                    case "제주특별자치도" :
                        Gugun.setAdapter(adapter17);
                        break;

                }
                if(sel1Save=="전체") sidoCode="";
                else {
                    MyAsyncTask1 myAsyncTask = new MyAsyncTask1();
                    myAsyncTask.execute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Gugun.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                sel2Save = adapterView.getItemAtPosition(i).toString();
                if(sel2Save=="전체") gugunCode="";
                else {
                    MyAsyncTask2 myAsyncTask2 = new MyAsyncTask2();
                    myAsyncTask2.execute();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    void sShowDate() {
        final Button sButton = findViewById(R.id.sBtn);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                sy = year;
                sm = month+1;
                sd = dayOfMonth;
                String sy1 = Integer.toString(sy);
                String sm1, sd1;
                if(sm<10) {
                    sm1 = Integer.toString(sm);
                    sm1 = "0"+sm1;
                }
                else {
                    sm1 = Integer.toString(sm);
                }
                if(sd<10) {
                    sd1 = Integer.toString(sd);
                    sd1 = "0"+sd1;
                }
                else {
                    sd1 = Integer.toString(sd);
                }
                sDate = sy1+sm1+sd1;
                sButton.setText(sDate);
            }
        },today_year1, today_month1, today_day1);

        datePickerDialog.setMessage("메시지");
        datePickerDialog.show();

    }

    void eShowDate() {
        final Button eButton = findViewById(R.id.eBtn);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                ey = year;
                em = month+1;
                ed = dayOfMonth;
                String ey1 = Integer.toString(ey);
                String em1, ed1;
                if(em<10) {
                    em1 = Integer.toString(em);
                    em1 = "0"+em1;
                }
                else {
                    em1 = Integer.toString(em);
                }
                if(ed<10) {
                    ed1 = Integer.toString(ed);
                    ed1 = "0"+ed1;
                }
                else {
                    ed1 = Integer.toString(ed);
                }
                eDate = ey1+em1+ed1;
                eButton.setText(eDate);
            }
        },today_year1, today_month1, today_day1);

        datePickerDialog.setMessage("메시지");
        datePickerDialog.show();
    }


    public class MyAsyncTask1 extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            requestUrl = "http://openapi.1365.go.kr/openapi/service/rest/CodeInquiryService/getAreaCodeInquiryList?" +
                    "serviceKey=" + dataKey + "&schSido=" + sel1Save;
                try {
                    boolean b_sidoCd = false;



                    URL url = new URL(requestUrl);
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
                                if (parser.getName().equals("item") && bus != null) {
                                    sidoCode = bus.sidoCd;
                                }
                                break;
                            case XmlPullParser.START_TAG:
                                if (parser.getName().equals("item")) {
                                    bus = new addrCodeItem();
                                }
                                if (parser.getName().equals("sidoCd")) b_sidoCd = true;

                                break;
                            case XmlPullParser.TEXT:

                                if (b_sidoCd) {
                                    bus.setSidoCd(parser.getText());
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

            requestUrl1 = "http://openapi.1365.go.kr/openapi/service/rest/CodeInquiryService/getAreaCodeInquiryList?" +
                    "serviceKey=" + dataKey + "&schGugun="+sel2Save;
            try {
                boolean b_gugunCd = false;



                URL url = new URL(requestUrl1);
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
                            if(parser.getName().equals("item") && bus1 != null) {
                                gugunCode = bus1.gugunCd;
                            }
                            break;
                        case XmlPullParser.START_TAG:
                            if(parser.getName().equals("item")){
                                bus1 = new addrCodeItem();
                            }
                            if (parser.getName().equals("gugunCd")) b_gugunCd = true;

                            break;
                        case XmlPullParser.TEXT:

                            if(b_gugunCd){
                                bus1.setGugunCd(parser.getText());
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

}
