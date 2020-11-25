package com.cookandroid.volunteer;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class StdLogin extends AppCompatActivity {

    // 마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    private long backKeyPressedTime = 0;
    // 첫 번째 뒤로가기 버튼을 누를때 표시
    private Toast toast;


    String myJSON;

    //셰어드프리퍼런스 로그인 정보 저장
    public final String PREFERENCE = "com.volunteerLogin.samplesharepreference";

    public final String key01 = "Email";
    public final String key02 = "pwd";
    public final String key03 = "Name";

    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID = "email";
    private static final String TAG_PW = "pw";
    JSONArray peoples = null;
    EditText inputEmail, inputPW;
    Button loginBtn, SignUp;
    String sendEmail;
    boolean isLogin = false;

    public static Context context_main;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stdlogin_layout);
        setTitle("학생 로그인");

        Intent intent = new Intent(this, LoadingActivity.class);
        startActivity(intent);

        inputEmail = (EditText)findViewById(R.id.inputEmail);
        inputPW = (EditText)findViewById(R.id.inputPW);
        loginBtn = (Button)findViewById(R.id.loginBtn);
        SignUp = (Button) findViewById(R.id.SignupBtn);

                context_main = this;


        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                FirebaseDatabase database = FirebaseDatabase.getInstance();
                final DatabaseReference myRef = database.getReference("customer").child("student");


                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Log.d("파이어베이스","이벤트실행");
                        for(DataSnapshot data : snapshot.getChildren()){
                            if(data.child("email").getValue().toString().equals(inputEmail.getText().toString())){
                                if(data.child("pw").getValue().toString().equals(inputPW.getText().toString())) {

                                    String name = data.child("name").getValue().toString();
                                    saveData(key01, key02, key03, inputEmail.getText().toString(),inputPW.getText().toString(), name);
                                    Log.d("알림", "로그인");
                                    Intent intent = new Intent();
                                    ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                                            "com.cookandroid.volunteer.StudentMain");
                                    intent.setComponent(cmpName);
                                    startActivity(intent);
                                    break;
                                }
                            };
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

                /*getData("http://192.168.142.97/PHP_connection.php"); //수정 필요*/


                if(isLogin == false) {
                    Toast.makeText(getApplicationContext(), "아이디 또는 비밀번호가 맞지않습니다!", 0).show();
                }

            }
        });


        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                        "com.cookandroid.volunteer.StudentSignUp");
                intent.setComponent(cmpName);
                startActivity(intent);
            }
        });
    }

    /*protected void showList() {
        Log.d("알림","showlist시작");
       *//* try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                String id = c.getString(TAG_ID);
                String pw = c.getString(TAG_PW);
                Log.d("알림",id);
                if(String.valueOf(inputEmail.getText()).equals(id)) {
                    if(String.valueOf(inputPW.getText()).equals(pw)) {

                        isLogin = true;

                        saveData(key01, key02, inputEmail.getText().toString(),inputPW.getText().toString());
                        Log.d("알림","로그인");
                        Intent intent = new Intent();
                        ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                                "com.cookandroid.volunteer.StudentMain");
                        intent.setComponent(cmpName);
                        startActivity(intent);
                        break;
                    }
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }*//*


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
*/
    public void saveData(String emailkey,String pwdkey, String namekey, String emailvalue, String pwdvalue, String namevalue){
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(emailkey, emailvalue);
        editor.commit();
        editor.putString(pwdkey, pwdvalue);
        editor.commit();
        editor.putString(namekey, namevalue);
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
}
