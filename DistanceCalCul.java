package tpghksdl521.nokizzoneapp;

import android.Manifest;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import tpghksdl521.nokizzoneapp.list.Member_list;

/**
 * Created by tpghk on 2018-04-08.
 */


public class DistanceCalCul {
    LocationManager locationManager;

    Context context;


    ArrayList<ArrayList<Member_list>> arrayLists;


    double latitude;
    double longitude;

    public DistanceCalCul() {
    }

    public DistanceCalCul(final Context context, LocationManager locationManager, ArrayList<ArrayList<Member_list>> arrayLists) {

        this.locationManager = locationManager;
        this.context = context;
        this.arrayLists = arrayLists;


        System.out.println("bomb");
        getmylocation();
        System.out.println("bomb2");
        calculDistance();
        System.out.println("bomb3");
        autoUpDateMylocation();
        System.out.println("bomb4");


    }


    void autoUpDateMylocation() {
        System.out.println("autoUpdate");
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        //내 위치 자동 갱신
        if (locationManager.isProviderEnabled("gps")) {
            locationManager.requestLocationUpdates("gps", 5000, 2, locationListener);
        } else if (locationManager.isProviderEnabled("network")) {
            locationManager.requestLocationUpdates("network", 5000, 2, locationListener);
        }
    }  //autoUpdateMylocation


    Location location;
//    void locationPermition() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
//                requestPermissions(permissions, 10);
//            }//if
//
//        }//if

 //   }//locationPermition
    void getmylocation() {
        System.out.println("getmylocation");
        //퍼미션 체크...안되어있으면 하지마!!!
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            System.out.println("퍼미션체크안됨");

            return;
        }

        //현재 내 위치 얻어오기
        location = null;

        if (locationManager.isProviderEnabled("gps")) {
            location = locationManager.getLastKnownLocation("gps");
            System.out.println("내위치 확인 gps");
        } else if (locationManager.isProviderEnabled("network")) {
            location = locationManager.getLastKnownLocation("network");
            System.out.println("내위치 확인 network");
        }
        System.out.println(location + "내위치지정");
        if (location == null) {
            latitude = 37.5014357;
            longitude = 127.0284671;
            Toast.makeText(context, "위치를 찾을수 없습니다.", Toast.LENGTH_SHORT).show();
        } else {
            //위도, 경도 얻어오기
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            Toast.makeText(context, latitude + " , " + longitude, Toast.LENGTH_SHORT).show();

        }

    }//getmylocation

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {

            latitude = location.getLatitude();
            longitude = location.getLongitude();

            System.out.println(latitude + "::" + longitude + "aaa");


        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };


    public void calculDistance() {

        for (int list = 0; list < arrayLists.size(); list++) {


            for (int i = 0; i < arrayLists.get(list).size(); i++) {


                float[] result = new float[1];
                //------------------------------------------------------------------------------------------------ 테스트 출력
                System.out.println(arrayLists.get(list).get(i).getV1() + "  , bbb ,  " + arrayLists.get(list).get(i).getV());
                System.out.println(latitude + ":::" + longitude);
                //------------------------------------------------------------------------------------------------
                location.distanceBetween(latitude, longitude, arrayLists.get(list).get(i).getV1(), arrayLists.get(list).get(i).getV(), result);
                arrayLists.get(list).get(i).setDistance(result[0]);

                System.out.println(result[0] + "ccc");

            }//for

            for (int i = arrayLists.get(list).size() - 1; i > 0; i--) {
                for (int j = 0; j < i; j++) {
                    if ((int) arrayLists.get(list).get(j).getDistance() < (int) arrayLists.get(list).get(j + 1).getDistance()) {

                        Collections.swap(arrayLists.get(list), j, j + 1);

                    }

                }
            }
            Collections.reverse(arrayLists.get(list));


            for (int i = 0; i < arrayLists.get(list).size(); i++) {


                System.out.println(arrayLists.get(list).get(i).getName() + " , " + arrayLists.get(list).get(i).getDistance());

            }


        }
    }//carcul Distance


}//class
