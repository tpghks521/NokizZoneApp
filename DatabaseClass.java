package tpghksdl521.nokizzoneapp;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.LocationManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import tpghksdl521.nokizzoneapp.list.Member_list;

/**
 * Created by tpghk on 2018-05-29.
 */

public class DatabaseClass {
    static public ArrayList<KizzoneList> DBkizzoneLists = new ArrayList<>();

    ArrayList<Member_list> seoul_list = new ArrayList<>();
    ArrayList<Member_list> busan_list = new ArrayList<>();
    ArrayList<Member_list> jeju_list = new ArrayList<>();
    ArrayList<Member_list> gyungi_list = new ArrayList<>();
    ArrayList<Member_list> theOther_list = new ArrayList<>();

    String dbName="nokizzone2";
    public static String tableName = "memberlist3";
    static SQLiteDatabase nokizdb;

    Cursor cursor;
    void checkDB(Context context){
        nokizdb=context.openOrCreateDatabase(dbName,context.MODE_PRIVATE,null);
        nokizdb.execSQL("CREATE TABLE if not exists "+tableName+"(no integer primary key autoincrement,name text not null,address text,v real,v1 real,further text);");

            cursor = nokizdb.rawQuery("select * from "+tableName, null);


            if(cursor.getCount()>0){
                System.out.println("라이트로드");
                databaseLiteload(context);
            }else{
                System.out.println("디비로드");
                loadlist(context);
            }








    }//checkDB
    void loadlist(final Context context) {
        System.out.println("여기요1");
        String url = "http://sh199lim.dothome.co.kr/android/nokizzonewebload.php";
        RequestQueue queue = Volley.newRequestQueue(context);
        System.out.println("여기요2");
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                System.out.println("여기요3");
                try {
//                    response.getJSONArray("webnautes");

                    JSONArray jsonArray=response.getJSONArray("nokizlist");

                    for(int i =0;i<jsonArray.length();i++) {
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
//                        System.out.println(jsonObject.getString("name"));
//                        System.out.println(jsonObject.getString("address"));
//                        System.out.println(jsonObject.getString("explanation"));
//                        System.out.println(jsonObject.getString("v1"));
//                        System.out.println(jsonObject.getString("v"));
                        double v= Double.parseDouble(jsonObject.getString("v"));
                        double v1=Double.parseDouble(jsonObject.getString("v1"));

                        DBkizzoneLists.add(new KizzoneList(jsonObject.getString("address"),jsonObject.getString("name"),jsonObject.getString("explanation"),v,v1));

                    }
                    dividelist(context);
                    setui(context);
                    System.out.println(DBkizzoneLists.size()+ "이거입니다");
                    databaseLitecreate(context,DBkizzoneLists);

//                   JSONArray jsonArray=response.getJSONArray("webnautes");
//System.out.println(response.getString("nokizlist"));
//                    for(int i=0;i<10;i++){
//                        System.out.println( jsonArray.getString(i));
//
//                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                    System.out.println(e+" 에러요");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error+" 에러요");
            }
        }
        );
            queue.add(jsonObjectRequest);
    }//loadlist

    void databaseLitecreate(Context context, ArrayList<KizzoneList> list) {
        System.out.println("로드a");
        for(int i=0;i<list.size();i++) {
            nokizdb.execSQL("insert into " + tableName + "(name, address, v, v1, further) values('"
                    +list.get(i).name+"','"
                    +list.get(i).address+"','"
                    +list.get(i).v+"','"
                    +list.get(i).v1+"','"
                    +list.get(i).further_explanation+"')"


            );

//          System.out.println(memberclasses.get(i).getAddress());
        }
//        System.out.println("로드c");
//
//
//        Cursor cursor =  DatabaseLiteclass.nokizdb.rawQuery("select * from "+"memberlist", null);
//        if(cursor==null) return;
//        int cnt=cursor.getCount();
//        StringBuffer stringBuffer = new StringBuffer();
//        System.out.println("로드d");
//        while (cursor.moveToNext()) {
//            String name = cursor.getString(1);
//            String address = cursor.getString(2);
//            double v = cursor.getDouble(3);
//            double v1 = cursor.getDouble(4);
//            String further = cursor.getString(5);
//
////            System.out.println(name + address + v + v1 + further + "\n");
//        }
        System.out.println("로드f");
    }//databaseLitecreate





    void databaseLiteload(Context context) {

        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String address = cursor.getString(2);
            double v = cursor.getDouble(3);
            double v1 = cursor.getDouble(4);
            String further = cursor.getString(5);
            DBkizzoneLists.add(new KizzoneList(address, name, further, v, v1));

    }//while
              dividelist(context);

        setui(context);
        System.out.println("로드");
    }

    void setui(Context context){
        Intent intent = new Intent(context,Main2Activity.class);
        context.startActivity(intent);

    }


    void dividelist(Context context){
        System.out.println("g1");
        System.out.println(DBkizzoneLists.size()+" g2");
        for (int i = 0; i < DBkizzoneLists.size(); i++) {
            String s = DBkizzoneLists.get(i).address.toString();
            System.out.println(s +"aaa");
            if (s.contains("서울")) {
                seoul_list.add(new Member_list(DBkizzoneLists.get(i).name, DBkizzoneLists.get(i).address, DBkizzoneLists.get(i).further_explanation, 0, DBkizzoneLists.get(i).v, DBkizzoneLists.get(i).v1));
            } else if (s.contains("경기")) {
                gyungi_list.add(new Member_list(DBkizzoneLists.get(i).name, DBkizzoneLists.get(i).address, DBkizzoneLists.get(i).further_explanation, 0, DBkizzoneLists.get(i).v, DBkizzoneLists.get(i).v1));
            } else if (s.contains("부산")) {
                busan_list.add(new Member_list(DBkizzoneLists.get(i).name, DBkizzoneLists.get(i).address, DBkizzoneLists.get(i).further_explanation, 0, DBkizzoneLists.get(i).v,DBkizzoneLists.get(i).v1));
            } else if (s.contains("제주")) {
                jeju_list.add(new Member_list(DBkizzoneLists.get(i).name, DBkizzoneLists.get(i).address, DBkizzoneLists.get(i).further_explanation, 0, DBkizzoneLists.get(i).v, DBkizzoneLists.get(i).v1));
            } else {
                theOther_list.add(new Member_list(DBkizzoneLists.get(i).name, DBkizzoneLists.get(i).address, DBkizzoneLists.get(i).further_explanation, 0, DBkizzoneLists.get(i).v, DBkizzoneLists.get(i).v1));
            }
        }

        MainActivity.arrayLists.add(seoul_list);
        MainActivity.arrayLists.add(gyungi_list);
        MainActivity.arrayLists.add(busan_list);
        MainActivity.arrayLists.add(jeju_list);
        MainActivity.arrayLists.add(theOther_list);

        new DistanceCalCul(context, (LocationManager) context.getSystemService(context.LOCATION_SERVICE), MainActivity.arrayLists);
//

    }

}