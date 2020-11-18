package com.cookandroid.volunteer;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Timer;

public class SelectedVolunteer extends AppCompatActivity {

    private static String IP_ADDRESS = "192.168.142.97";
    private static String TAG = "phptest";

    //스케쥴 데이터


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


    TextView seldTItle, seldTime, seldPlace, seldHour, seldNTime, seldhoumany, seldApplyd, seldBoon, seldRecPeriod, seldRegist, seldProgrmCn;
    SearchData searchVo = ((ResultView)ResultView.context_main).data;

    //셰어드 프리퍼런스 변수
    public final String PREFERENCE = "com.volunteerLogin.samplesharepreference";
    public final String key01 = "Email";
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectedvolunteer);

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

        seldTItle.setText(searchVo.progrmSj);
        seldTime.setText(searchVo.progrmBgnde + " ~ " + searchVo.progrmEndde+"");
        seldPlace.setText(searchVo.actPlace);
        seldHour.setText(searchVo.actBeginTm + "시 ~ " + searchVo.actEndTm+"시");
        seldNTime.setText(searchVo.noticeBgnde + " ~ " + searchVo.noticeEndde);
        seldhoumany.setText(searchVo.rcritNmpr+" 명 / 일");
        seldApplyd.setText(searchVo.appTotal+" 명 / 일");
        seldBoon.setText(searchVo.srvcClCode);
        seldRecPeriod.setText(searchVo.mnnstNm);
        seldRegist.setText(searchVo.nanmmbyNm);
        seldProgrmCn.setText(searchVo.progrmCn);
    }
    public void onBtnClicked(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://1365.go.kr/vols/P9210/partcptn/timeCptn.do?type=show&progrmRegistNo=" + searchVo.progrmRegistNo));
        startActivity(intent);

        //빌더 선언
        AlertDialog.Builder builder = new AlertDialog.Builder(SelectedVolunteer.this);
        // 제목 설정
        builder.setTitle("봉사활동을 신청하셨습니까?");
        // 요소 설정
        builder.setPositiveButton("네", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                String vDate = searchVo.progrmBgnde + searchVo.progrmEndde;
                String vTitle = searchVo.progrmSj;
                String vCode = searchVo.progrmRegistNo;
                String Email = getEmailFromDevice(key01);


                InsertData task = new InsertData();
                task.execute("http://" + IP_ADDRESS + "/insertVolunteer.php", vDate, vTitle, vCode, Email);

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


    class InsertData extends AsyncTask<String, Void, String> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            progressDialog = ProgressDialog.show(SelectedVolunteer.this,
                    "Please Wait", null, true, true);
        }


        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            progressDialog.dismiss();
            /*mTextViewResult.setText(result);*/
            Log.d(TAG, "POST response  - " + result);
        }


        @Override
        protected String doInBackground(String... params) {

            String vDate = (String)params[1];
            String vTitle = (String)params[2];
            String vCode = (String)params[3];
            String Email = (String)params[4];

            String serverURL = (String)params[0];
            String postParameters = "vDate=" + vDate + "&vTitle=" + vTitle + "&vCode=" + vCode + "&Email=" + Email;


            try {

                URL url = new URL(serverURL);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(postParameters.getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();
                Log.d(TAG, "POST response code - " + responseStatusCode);

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return sb.toString();


            } catch (Exception e) {

                Log.d(TAG, "InsertData: Error ", e);

                return new String("Error: " + e.getMessage());
            }

        }
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

        getData("http://192.168.142.97/PHP_connection_schedules.php");
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
