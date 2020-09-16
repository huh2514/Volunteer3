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
        String names;
        boolean initem = false, inAddr = false, inChargeTp = false, inCpId = false, inCpNm = false;
        boolean inCpStat = false, inCpTp = false, inCsId = false, inCsNm = false, inLat=false;
        boolean inLongi = false, inStatUpdateDatetime = false;
        boolean inTitle = false, inbeginTm = false, inendTm = false, inPlace = false, inJy = false, inUrl = false;

        String addr = null, chargeTp = null, cpId = null, cpNm = null, cpStat = null, cpTp=null, csId=null, csNm=null;
        String lat = null, longi = null, statUpdateDatetime = null;
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
                        /*if(parser.getName().equals("addr")){ //title 만나면 내용을 받을수 있게 하자
                            inAddr = true;
                        }
                        if(parser.getName().equals("chargeTp")){ //address 만나면 내용을 받을수 있게 하자
                            inChargeTp = true;
                        }
                        if(parser.getName().equals("cpId")){ //mapx 만나면 내용을 받을수 있게 하자
                            inCpId = true;
                        }
                        if(parser.getName().equals("cpNm")){ //mapy 만나면 내용을 받을수 있게 하자
                            inCpNm = true;
                        }
                        if(parser.getName().equals("cpStat")){ //mapy 만나면 내용을 받을수 있게 하자
                            inCpStat = true;
                        }
                        if(parser.getName().equals("cpTp")){ //mapy 만나면 내용을 받을수 있게 하자
                            inCpTp = true;
                        }
                        if(parser.getName().equals("csId")){ //mapy 만나면 내용을 받을수 있게 하자
                            inCsId = true;
                        }
                        if(parser.getName().equals("csNm")){ //mapy 만나면 내용을 받을수 있게 하자
                            inCsNm = true;
                        }
                        if(parser.getName().equals("lat")){ //mapy 만나면 내용을 받을수 있게 하자
                            inLat = true;
                        }
                        if(parser.getName().equals("longi")){ //mapy 만나면 내용을 받을수 있게 하자
                            inLongi = true;
                        }
                        if(parser.getName().equals("statUpdateDatetime")){ //mapy 만나면 내용을 받을수 있게 하자
                            inStatUpdateDatetime = true;
                        }
                        if(parser.getName().equals("statUpdateDatetime")){ //mapy 만나면 내용을 받을수 있게 하자
                            inStatUpdateDatetime = true;
                        }
                        if(parser.getName().equals("message")){ //message 태그를 만나면 에러 출력
                            status1.setText(status1.getText()+"에러");
                            //여기에 에러코드에 따라 다른 메세지를 출력하도록 할 수 있다.
                        }*/
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
                        /*if(inAddr){ //isTitle이 true일 때 태그의 내용을 저장.
                            addr = parser.getText();
                            inAddr = false;
                        }
                        if(inChargeTp){ //isAddress이 true일 때 태그의 내용을 저장.
                            chargeTp = parser.getText();
                            inChargeTp = false;
                        }
                        if(inCpId){ //isMapx이 true일 때 태그의 내용을 저장.
                            cpId = parser.getText();
                            inCpId = false;
                        }
                        if(inCpNm){ //isMapy이 true일 때 태그의 내용을 저장.
                            cpNm = parser.getText();
                            inCpNm = false;
                        }
                        if(inCpStat){ //isMapy이 true일 때 태그의 내용을 저장.
                            cpStat = parser.getText();
                            inCpStat = false;
                        }
                        if(inCpTp){ //isMapy이 true일 때 태그의 내용을 저장.
                            cpTp = parser.getText();
                            inCpTp = false;
                        }
                        if(inCsId){ //isMapy이 true일 때 태그의 내용을 저장.
                            csId = parser.getText();
                            inCsId = false;
                        }
                        if(inCsNm){ //isMapy이 true일 때 태그의 내용을 저장.
                            csNm = parser.getText();
                            inCsNm = false;
                        }
                        if(inLat){ //isMapy이 true일 때 태그의 내용을 저장.
                            lat = parser.getText();
                            inLat = false;
                        }
                        if(inLongi){ //isMapy이 true일 때 태그의 내용을 저장.
                            longi = parser.getText();
                            inLongi = false;
                        }
                        if(inStatUpdateDatetime){ //isMapy이 true일 때 태그의 내용을 저장.
                            statUpdateDatetime = parser.getText();
                            inStatUpdateDatetime = false;
                        }*/
                        break;
                    case XmlPullParser.END_TAG:
                        if(parser.getName().equals("item")){
                            status1.setText(status1.getText()+"제목 : "+ title + "\n"+"시작시간 : "+ beginTm + "\n"+"종료시간 : "+ endTm + "\n"+"장소 : "+ place + "\n"+"지역 : "+ jy + "\n"+"페이지 주소 : "+ wurl + "\n\n\n");
                            initem = false;
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
