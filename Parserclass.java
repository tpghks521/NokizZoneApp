package tpghksdl521.nokizzoneapp;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by tpghk on 2018-03-27.
 */

public class Parserclass {

    ArrayList<Memberclass> members;
    String namemember;
    String pointmember;
    String description;
    Context context;
    public Parserclass(Context context) {
//        tv=view.findViewById(R.id.tv);
        //Resource폴더에 있는 xml문서를 읽어서
        //분석(parse)하는 작업 수행

        //res폴더 창고관리자 객체 소환
        this.context=context;
        Resources res = context.getResources();

        //창고관리자로부터 파서객체 얻어오기.
        XmlResourceParser xrp = res.getXml(R.xml.kizzonelist);

        StringBuffer buffer = new StringBuffer();
       members= new ArrayList<>();
        try {
            xrp.next();
            int eventType = xrp.getEventType();

            String name; //태그이름
            String text; //내용글씨

            while (eventType != XmlResourceParser.END_DOCUMENT) {

                switch (eventType) {
                    case XmlResourceParser.START_DOCUMENT:
                        buffer.append("xml 파싱 시작합니다..\n\n");
                        break;

                    case XmlResourceParser.START_TAG:
                        name = xrp.getName(); //태그문의 이름얻어오기
                       if (name.equals("name")) {

                            buffer.append("제목:");
                            xrp.next();
                            namemember=xrp.getText();

                        } else if (name.equals("description")) {
                            buffer.append("설명:");
                            xrp.next();

                           description=xrp.getText();

                        } else if (name.equals("coordinates")) {

                            buffer.append("위치:");
                            xrp.next();
                            pointmember=xrp.getText();
                            members.add(new Memberclass(namemember,pointmember,description));
                           description="";
                        }

                        break;

                    case XmlResourceParser.TEXT:
                        break;

                    case XmlResourceParser.END_TAG:
                        break;
                }//switch

                eventType = xrp.next();


            }//while....

            buffer.append("파싱 종료....\n");



for( int i=0;i<members.size();i++) {

    String s = members.get(i).point;
    String s1 = s;
    int a = s.indexOf(",");

    s = s.substring(0, a);

    s1 = s1.substring(a + 1);
    int b = s1.indexOf(",");
    s1 = s1.substring(0, b);


    members.get(i).setV(Double.parseDouble(s));
    members.get(i).setV1(Double.parseDouble(s1));
}//for
//--------------------------------------------


        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }//catch


//---------------------------------------네이버지오코더 쓰레드 스타트

     Navergeocoder navergeocoder =new Navergeocoder(members,context);
        navergeocoder.start();

//---------------------------------------

    }//constructer
    class Memberclass{
        String address;


        String name;
        String further_explanation;
        String point;

        double v,v1;

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public double getV() {
            return v;
        }

        public void setV(double v) {
            this.v = v;
        }

        public double getV1() {
            return v1;
        }

        public void setV1(double v1) {
            this.v1 = v1;
        }

        public Memberclass(String name, String point,String further_explanation) {
            this.name = name;
            this.further_explanation=further_explanation;
            this.point = point;
        }//memberclassMetiode

    }//memberclass
}//parserclass
