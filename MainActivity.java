package tpghksdl521.nokizzoneapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.LocationManager;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.request.SimpleMultiPartRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import tpghksdl521.nokizzoneapp.list.Member_list;

public class MainActivity extends AppCompatActivity {
    static public ArrayList<ArrayList<Member_list>> arrayLists = new ArrayList<>();

    TabLayout tabLayout, tabLayout2;
    ViewPager pager;
    MyAdapter myAdapter;
    Toolbar toolbar;
    Cursor cursor;

    static public boolean AreThereList = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //locationPermition();

//----------------------------------------------------------------------------------------------------------------------------database lite 말고 닷홈으로 고치기
             new DatabaseClass().checkDB(this);
                 //new DatabaseClass().databaseLiteload(this);
                 //   new DistanceCalCul(this, (LocationManager) getSystemService(LOCATION_SERVICE), arrayLists);

                   // setTabLayout();

//----------------------------------------------------------------------------------------------------------------------------database lite 말고 닷홈으로 고치기
            //관리자용 페이지를 만들어서 HTML로 만들어서 관리하고 그걸 불러와서 여기서씀

//
//            try {
//                new DatabaseLiteclass().databaseLiteload(this);
//                cursor = DatabaseLiteclass.nokizdb.rawQuery("select * from  memberlist3", null);
//                System.out.println(cursor.getCount()+"이건뭔가요");
//                if (cursor.getCount()!=0) {
//                    new DatabaseClass().loadlist(this);
//                    System.out.println("로드2");
//                    new DatabaseLiteclass().databaseLiteload(this);
//                    cursor = DatabaseLiteclass.nokizdb.rawQuery("select * from  memberlist3", null);
//
//                    divideList();
//                    arrayLists.add(seoul_list);
//                    arrayLists.add(gyungi_list);
//                    arrayLists.add(busan_list);
//                    arrayLists.add(jeju_list);
//                    arrayLists.add(theOther_list);
//                    DistanceCalCul distanceCalCul = new DistanceCalCul(this, (LocationManager) getSystemService(LOCATION_SERVICE), arrayLists);
//
//                    setTabLayout();
//
//
//
//                    System.out.println("로드1");
//
//                } else {
//                    //---------------------리스트 분류
//                    new DatabaseClass().loadlist(this);
//                    divideList();
//
//                    arrayLists.add(seoul_list);
//                    arrayLists.add(gyungi_list);
//                    arrayLists.add(busan_list);
//                    arrayLists.add(jeju_list);
//                    arrayLists.add(theOther_list);
//
//                    //-------------------------거리계산 클래스
//                    DistanceCalCul distanceCalCul = new DistanceCalCul(this, (LocationManager) getSystemService(LOCATION_SERVICE), arrayLists);
//
//
//                    //---------------------탭바설정
//                    setTabLayout();
//                }
//
//            } catch (Exception e) {
//                System.out.println(e + "여기");
//                Toast.makeText(this, "데이터를 불러오는데 실패하였습니다.", Toast.LENGTH_SHORT).show();
//            }

//-----------------------------------------------------------------------------------------------------------------------------


//       ? toolbar.dispatchSystemUiVisibilityChanged(View.INVISIBLE);


    }//oncreate

//    void divideList() {
//        System.out.println("이거되나1");
//        while (cursor.moveToNext()) {
//            String name = cursor.getString(1);
//            String address = cursor.getString(2);
//            double v = cursor.getDouble(3);
//            double v1 = cursor.getDouble(4);
//            String further = cursor.getString(5);
//            kizzoneLists.add(new KizzoneList(address, name, further, v, v1));
//            System.out.println(name + address + v + v1 + further + "\n");
//        }//while
//        System.out.println("여기요123");
//        for (int i = 0; i < kizzoneLists.size(); i++) {
//            String s = kizzoneLists.get(i).address.toString();
//            System.out.println(s +"aaa");
//            if (s.contains("서울")) {
//                seoul_list.add(new Member_list(kizzoneLists.get(i).name, kizzoneLists.get(i).address, kizzoneLists.get(i).further_explanation, 0, kizzoneLists.get(i).v, kizzoneLists.get(i).v1));
//            } else if (s.contains("경기")) {
//                gyungi_list.add(new Member_list(kizzoneLists.get(i).name, kizzoneLists.get(i).address, kizzoneLists.get(i).further_explanation, 0, kizzoneLists.get(i).v, kizzoneLists.get(i).v1));
//            } else if (s.contains("부산")) {
//                busan_list.add(new Member_list(kizzoneLists.get(i).name, kizzoneLists.get(i).address, kizzoneLists.get(i).further_explanation, 0, kizzoneLists.get(i).v, kizzoneLists.get(i).v1));
//            } else if (s.contains("제주")) {
//                jeju_list.add(new Member_list(kizzoneLists.get(i).name, kizzoneLists.get(i).address, kizzoneLists.get(i).further_explanation, 0, kizzoneLists.get(i).v, kizzoneLists.get(i).v1));
//            } else {
//                theOther_list.add(new Member_list(kizzoneLists.get(i).name, kizzoneLists.get(i).address, kizzoneLists.get(i).further_explanation, 0, kizzoneLists.get(i).v, kizzoneLists.get(i).v1));
//            }
//        }
//        System.out.println("이거되나2");
//
//        //  loadlistinDb(kizzoneLists.get(num).name,kizzoneLists.get(num).address,kizzoneLists.get(num).further_explanation,kizzoneLists.get(num).v,kizzoneLists.get(num).v1);
//
//    }//divideList
////    int num=0;
//    void loadlistinDb(String name,String address,String explanation,double v, double v1){
//        String serverUrl = "http://sh199lim.dothome.co.kr/android/nokizzzonewebpage.php";
//
//        SimpleMultiPartRequest multiPartRequest = new SimpleMultiPartRequest(Request.Method.POST, serverUrl, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//            System.out.println(response+"성공했습니다.");
//                num++;
//                loadlistinDb(kizzoneLists.get(num).name,kizzoneLists.get(num).address,kizzoneLists.get(num).further_explanation,kizzoneLists.get(num).v,kizzoneLists.get(num).v1);
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//
//        multiPartRequest.addStringParam("name",name);
//        multiPartRequest.addStringParam("address",address);
//        multiPartRequest.addStringParam("explanation",explanation);
//        multiPartRequest.addStringParam("v",v+"");
//        multiPartRequest.addStringParam("v1",v1+"");
//        RequestQueue requestQueue= Volley.newRequestQueue(this);
//        requestQueue.add(multiPartRequest);
//    }


    //------------------------------------------------------------------------------------------------- 위치정보 제공 동의 물어보기
//    void locationPermition() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
//                requestPermissions(permissions, 10);
//            }//if
//
//        }//if
//
//    }//locationPermition


//    //----------------------------------------------------------------상단 메뉴 아이콘생성
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.activity_menu, menu);
//
//        return super.onCreateOptionsMenu(menu);
//    }//onCreateOptionsMenu
//
//    //--------------------------------------------------------------메뉴 아이콘 클릭리스너
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_search:
//                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
//                break;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }//onOptionsItemSelected

//    void findview(){
//        toolbar = findViewById(R.id.toolbar);
//
//        tabLayout = findViewById(R.id.layout_tab);
//        pager = findViewById(R.id.pager);
//
//    }

//    //--------------------------------------------------------------
//void setTabLayout() {
//
//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        tabLayout = findViewById(R.id.layout_tab);
//        pager = findViewById(R.id.pager);
//        myAdapter = new MyAdapter(getSupportFragmentManager(), arrayLists);
//        pager.setAdapter(myAdapter);
//        //탭 레이아웃과 뷰페이져를 연동
//        tabLayout.setupWithViewPager(pager);
//        //---------------------------------------------------------------------------두번째 탭만들기
//
//        tabLayout2 = findViewById(R.id.layout_tab2);
//
//        Tab tab = tabLayout2.newTab();
//        Tab tab2 = tabLayout2.newTab();
////        Tab tab3= tabLayout2.newTab();
//        tab2.setText("지도보기");
//        tab.setText("거리순");
////        tab3.setText("추천순");
//
//
//        tabLayout2.addTab(tab);
//        tabLayout2.addTab(tab2);
////        tabLayout2.addTab(tab3);
//
//
//        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(Tab tab) {
//
//                switch (tab.getText().toString()) {
//
//                    case "지도보기":
//
//                        Intent intent = new Intent(MainActivity.this, NaverMapActivity.class);
//                        startActivity(intent);
//
//
//                        break;
//
//                    case "거리순":
//                        new Thread(new Runnable() {
//
//                            @Override
//                            public void run() {
//                                new DistanceCalCul().calculDistance();
//                                Toast.makeText(MainActivity.this, "reset", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//
//
//                        break;
//                }
//
//
//            }
//
//            @Override
//            public void onTabUnselected(Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(Tab tab) {
//
//            }
//        });
//
//
//    }//setTabLayout


//--------------------------------------------------------------
}
