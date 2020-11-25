package com.cookandroid.volunteer;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;

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

public class LoadingActivity extends Activity {

    //자동로그인 변수
    String myJSON;
    String email;
    String pwd;

    public final String PREFERENCE = "com.volunteerLogin.samplesharepreference";
    public final String key01 = "Email";
    public final String key02 = "pwd";

    private static final String TAG_RESULTS = "result";
    private static final String TAG_ID = "email";
    private static final String TAG_PW = "pw";
    JSONArray peoples = null;
    boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_loading);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadData(key01, key02);
                finish();

            }
        }, 2000);


    }




    public void loadData(String emailkey, String pwdkey){
        SharedPreferences pref = getSharedPreferences(PREFERENCE, MODE_PRIVATE);
        email = pref.getString(emailkey,"");
        pwd = pref.getString(pwdkey,"");

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference myRef = database.getReference("customer").child("student");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(email != "" && email != null)
                for(DataSnapshot data : snapshot.getChildren()) {
                    if (data.child("email").getValue().toString().equals(email)) {
                        if (data.child("pw").getValue().toString().equals(pwd)) {
                            Intent intent = new Intent();
                            ComponentName cmpName = new ComponentName("com.cookandroid.volunteer",
                                    "com.cookandroid.volunteer.StudentMain");
                            intent.setComponent(cmpName);
                            startActivity(intent);
                            break;
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        Toast.makeText(this, email + "계정으로 로그인 합니다.", Toast.LENGTH_SHORT).show();
        /*getData("http://192.168.142.97/PHP_connection.php");*/

    }

/*


    protected void showList() {
        try {
            JSONObject jsonObj = new JSONObject(myJSON);
            peoples = jsonObj.getJSONArray(TAG_RESULTS);

            for (int i = 0; i < peoples.length(); i++) {
                JSONObject c = peoples.getJSONObject(i);
                String id = c.getString(TAG_ID);
                String pw = c.getString(TAG_PW);
                if(email.equals(id)) {
                    if(pwd.equals(pw)) {
                        isLogin = true;

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
*/


}