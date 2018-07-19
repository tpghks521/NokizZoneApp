package tpghksdl521.nokizzoneapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

import tpghksdl521.nokizzoneapp.list.Member_list;

public class Main2Activity extends AppCompatActivity {

    TabLayout tabLayout, tabLayout2;
    ViewPager pager;
    MyAdapter myAdapter;
    Toolbar toolbar;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

     locationPermition();

        setTabLayout();


//        for(int i =0;i<10;i++){
//            System.out.println(MainActivity.arrayLists.get(0).get(i).getName());
//        }

    }

    //----------------------------------------------------------------상단 메뉴 아이콘생성
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }//onCreateOptionsMenu

    //--------------------------------------------------------------메뉴 아이콘 클릭리스너
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_search:
                Toast.makeText(this, "Search", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }//onOptionsItemSelected

    void setTabLayout() {

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        tabLayout = findViewById(R.id.layout_tab);
        pager = findViewById(R.id.pager);
        myAdapter = new MyAdapter(getSupportFragmentManager(), MainActivity.arrayLists);
        System.out.println(MainActivity.arrayLists.size()+"여기숫자");
        pager.setAdapter(myAdapter);
        //탭 레이아웃과 뷰페이져를 연동
        tabLayout.setupWithViewPager(pager);

        //---------------------------------------------------------------------------두번째 탭만들기

        tabLayout2 = findViewById(R.id.layout_tab2);

        TabLayout.Tab tab = tabLayout2.newTab();
        TabLayout.Tab tab2 = tabLayout2.newTab();
//        Tab tab3= tabLayout2.newTab();
        tab2.setText("지도보기");
        tab.setText("거리순");
//        tab3.setText("추천순");


        tabLayout2.addTab(tab);
        tabLayout2.addTab(tab2);
//        tabLayout2.addTab(tab3);


        tabLayout2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                switch (tab.getText().toString()) {

                    case "지도보기":

                        Intent intent = new Intent(Main2Activity.this, NaverMapActivity.class);
                        startActivity(intent);


                        break;

                    case "거리순":
                        new Thread(new Runnable() {

                            @Override
                            public void run() {
                                new DistanceCalCul().calculDistance();
                                Toast.makeText(Main2Activity.this, "reset", Toast.LENGTH_SHORT).show();
                            }
                        });


                        break;
                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }//setTabLayout
    void locationPermition() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
                requestPermissions(permissions, 10);
            }//if

        }//if

    }//locationPermition
}
