package tpghksdl521.nokizzoneapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.widget.TextView;


import java.util.ArrayList;

/**
 * Created by tpghk on 2018-04-04.
 */

public class DatabaseLiteclass {
    TextView textView;
    String dbName="nokizzone2";
   public static String tableName = "memberlist3";
  static  SQLiteDatabase nokizdb;
    Context context;
    Cursor cursor;

    void databaseLiteload(Context context) {
        nokizdb=context.openOrCreateDatabase(dbName,context.MODE_PRIVATE,null);
        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String address = cursor.getString(2);
            double v = cursor.getDouble(3);
            double v1 = cursor.getDouble(4);
            String further = cursor.getString(5);
            DatabaseClass.DBkizzoneLists.add(new KizzoneList(address, name, further, v, v1));
            System.out.println(name + address + v + v1 + further + "\n");
        }//while
            System.out.println("로드");
        }



    void databaseLitecreate(Context context, ArrayList<KizzoneList> list) {
        System.out.println("로드a");
      nokizdb=context.openOrCreateDatabase(dbName,context.MODE_PRIVATE,null);
    nokizdb.execSQL("DROP TABLE IF EXISTS "+tableName);
    nokizdb.execSQL("CREATE TABLE if not exists "+tableName+"(no integer primary key autoincrement,name text not null,address text,v real,v1 real,further text);");
        System.out.println("로드b");
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
    }


}
