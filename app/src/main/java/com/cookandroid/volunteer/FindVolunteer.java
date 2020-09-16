package com.cookandroid.volunteer;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.net.URL;

public class FindVolunteer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findvolunteer);
        setTitle("test");

        StrictMode.enableDefaults();

        TextView status1 = (TextView)findViewById(R.id.result); //파싱된 결과확인!

        boolean inTitle = false, inbeginTm = false, inendTm = false, inPlace = false, inJy = false, inUrl = false;

        String title = null,beginTm = null,endTm = null,place = null,jy = null,wurl = null;


        try{
            URL url = new URL("http://openapi.1365.go.kr/openapi/service/rest/Volunteer" +
                    "PartcptnService/getVltrSearchWordList?serviceKey=" +
                    "99scXLkolGPdepyUojDC%2BsBNxExtjmJaDwjRHkpJoVZ0yJ49tcKZk6r%2BM3y77" +
                    "UcfpaxvdLBlU%2F8nkuR2mm%2BG9Q%3D%3D&progrmBgnde=&progrmEndde=&adult" +
                    "PosblAt=&yngbgsPosblAt=&numOfRows=10&pageNo=2&keyword=&schCateGu=" +
                    "&schSido=&schSign1=&upperClCode=&nanmClCode=&actBeginTm=&actEndTm=" +
                    "&noticeBgnde=&noticeEndde=&actPlace=&nanmmbyNm=&"
            ); //검색 URL부분
            XmlPullParserFactory parserCreator = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserCreator.newPullParser();

            parser.setInput(url.openStream(), null);

            int parserEvent = parser.getEventType();
            System.out.println("파싱시작합니다.");

            while (parserEvent != XmlPullParser.END_DOCUMENT){
                switch(parserEvent){
                    case XmlPullParser.START_TAG://parser가 시작 태그를 만나면 실행
                        if(parser.getName().equals("progrmSj")){ //봉사제목
                            inTitle = true;
                        }
                        if(parser.getName().equals("actBeginTm")){ //봉사시작시간
                            inbeginTm = true;
                        }
                        if(parser.getName().equals("actEndTm")){ //봉사종료시간
                            inendTm = true;
                        }
                        if(parser.getName().equals("actPlace")){ //봉사장소
                            inPlace = true;
                        }
                        if(parser.getName().equals("nanmmbyNm")){ //봉사지역
                            inJy = true;
                        }
                        if(parser.getName().equals("url")){ //웹주소
                            inUrl = true;
                        }
                        break;

                    case XmlPullParser.TEXT://parser가 내용에 접근했을때
                        if(inTitle){ //isTitle이 true일 때 태그의 내용을 저장.
                            title = parser.getText();
                            inTitle = false;
                        }
                        if(inbeginTm){ //isbeginTm이 true일 때 태그의 내용을 저장.
                            beginTm = parser.getText();
                            inbeginTm = false;
                        }
                        if(inendTm){ //isendTm이 true일 때 태그의 내용을 저장.
                            endTm = parser.getText();
                            inendTm = false;
                        }
                        if(inPlace){ //isPlace이 true일 때 태그의 내용을 저장.
                            place = parser.getText();
                            inPlace = false;
                        }
                        if(inJy){ //isJy이 true일 때 태그의 내용을 저장.
                            jy = parser.getText();
                            inJy = false;
                        }
                        if(inUrl){ //isUrl이 true일 때 태그의 내용을 저장.
                            wurl = parser.getText();
                            inUrl = false;
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            status1.setText(status1.getText()+"제목 : "+ title + "\n"+"시작시간 : "+ beginTm + "\n"+"종료시간 : "+ endTm + "\n"+"장소 : "+ place + "\n"+"지역 : "+ jy + "\n"+"페이지 주소 : "+ wurl + "\n\n\n");
                        }
                        break;
                }
                parserEvent = parser.next();
            }
        } catch(Exception e){
            status1.setText("에러가..났습니다...");
            e.printStackTrace();
        }
    }
}
