package tpghksdl521.nokizzoneapp;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nhn.android.maps.NMapActivity;


public class NaverMapActivity extends NMapActivity {

    private NaverMapview mMapView;// 지도 화면 View
    private final String CLIENT_ID = "iKSZaAVCBWCWQetF6wzs";// 애플리케이션 클라이언트 아이디 값

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naver_map_view);

//--
        Intent intent =getIntent();

        mMapView = findViewById(R.id.nmap);
        mMapView.setPosition(intent.getIntExtra("position",0));
        System.out.println(intent.getIntExtra("position",0)+"ppppp2");




        mMapView.setClientId(CLIENT_ID); // 클라이언트 아이디 값 설정

        mMapView.setClickable(true);
        mMapView.setEnabled(true);
        mMapView.setFocusable(true);
        mMapView.setFocusableInTouchMode(true);
        mMapView.requestFocus();


    }

    public void clickFab(View view) {
        mMapView.executeNaverMap();

    }
}
