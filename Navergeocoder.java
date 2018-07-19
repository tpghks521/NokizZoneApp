package tpghksdl521.nokizzoneapp;

import android.content.Context;
import android.content.res.Resources;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

/**
 * Created by tpghk on 2018-03-29.
 */

public class Navergeocoder extends Thread{
    final     String clientId = "iKSZaAVCBWCWQetF6wzs";//애플리케이션 클라이언트 아이디값";
    final  String clientSecret = "W5K1Ja_P7G";//애플리케이션 클라이언트 시크릿값
Context context;


 ArrayList<Parserclass.Memberclass> memberclasses;
    public Navergeocoder(ArrayList<Parserclass.Memberclass> memberclasses,Context context) {
        this.memberclasses=memberclasses;
        this.context=context;


    }//constructer
    int a;
    int b;
    String s;
    @Override
    public void run() {




        String addr = null;
        try {
           for(int i=0;i<memberclasses.size();i++) {

               addr = URLEncoder.encode(memberclasses.get(i).getV() + "," + memberclasses.get(i).getV1(), "UTF-8");

               String apiURL = "https://openapi.naver.com/v1/map/reversegeocode.xml (xml)?query=" + addr; // xml
               URL url = new URL(apiURL);
               HttpURLConnection con = (HttpURLConnection) url.openConnection();
               con.setRequestMethod("GET");

               con.setRequestProperty("X-Naver-Client-Id", clientId);
               con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

               BufferedReader bufferedReader;
               bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
               String inputLine;
               StringBuffer reponse = new StringBuffer();
               Resources res = context.getResources();


               while ((inputLine = bufferedReader.readLine()) != null) {

                       reponse.append(inputLine);
                       s= reponse.toString();
                       a=  s.indexOf("<address><![CDATA[");
                       b= s.indexOf("]]></address>")  ;
               }
                      bufferedReader.close();
//                    MainActivity.tv.setText(reponse.toString());



                memberclasses.get(i).setAddress(s.substring(a+18,b));
              // System.out.println(memberclasses.get(i).getAddress());

           }//for
        } catch (Exception e) {
            System.out.println(e.toString());
        }//catch
        //----------------------------------------------------------------데이터베이스에저장하기
        //new DatabaseLiteclass(context,memberclasses);
        //----------------------------------------------------------------
    }//run
}//class
