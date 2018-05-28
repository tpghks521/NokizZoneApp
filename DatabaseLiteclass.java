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
    String dbName="nokizzone";
    String tableName = "memberlist";
  static  SQLiteDatabase nokizdb;
    Context context;
ArrayList<Parserclass.Memberclass> memberclasses;


    public DatabaseLiteclass(Context context) {
        nokizdb=context.openOrCreateDatabase(dbName,context.MODE_PRIVATE,null);



    }

    public DatabaseLiteclass(Context context, ArrayList<Parserclass.Memberclass> memberclasses) {
        this.context=context;
        this.memberclasses=memberclasses;
      nokizdb=context.openOrCreateDatabase(dbName,context.MODE_PRIVATE,null);
    nokizdb.execSQL("DROP TABLE IF EXISTS "+tableName);
    nokizdb.execSQL("CREATE TABLE if not exists "+tableName+"(no integer primary key autoincrement,name text not null,address text,v real,v1 real,further text);");

      for(int i=0;i<memberclasses.size();i++) {
          nokizdb.execSQL("insert into " + tableName + "(name, address, v, v1, further) values('"
                  +memberclasses.get(i).name+"','"
                  +memberclasses.get(i).getAddress()+"','"
                  +memberclasses.get(i).getV()+"','"
                  +memberclasses.get(i).getV1()+"','"
                  +memberclasses.get(i).further_explanation+"')"


          );
//          System.out.println(memberclasses.get(i).getAddress());
      }



        Cursor cursor =  DatabaseLiteclass.nokizdb.rawQuery("select * from "+"memberlist", null);
        if(cursor==null) return;
        int cnt=cursor.getCount();
        StringBuffer stringBuffer = new StringBuffer();

        while (cursor.moveToNext()) {
            String name = cursor.getString(1);
            String address = cursor.getString(2);
            double v = cursor.getDouble(3);
            double v1 = cursor.getDouble(4);
            String further = cursor.getString(5);

            System.out.println(name + address + v + v1 + further + "\n");
        }

    }


}
