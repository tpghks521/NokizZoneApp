package tpghksdl521.nokizzoneapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

import tpghksdl521.nokizzoneapp.list.Member_list;
import tpghksdl521.nokizzoneapp.location_tab.Member;

/**
 * Created by tpghk on 2018-03-30.
 */

public class MyAdapter extends FragmentPagerAdapter {

Fragment[] frags = new Fragment[5];
String[] titles ={"서울","경기","부산","제주","기타"};

    public MyAdapter(FragmentManager fm, ArrayList<ArrayList<Member_list>> arrayLists) {
        super(fm);


       for(int i=0;i<arrayLists.size();i++) {
           frags[i] = new Member(arrayLists.get(i));

       }


    }

    @Override
    public Fragment getItem(int position) {
        return frags[position];
    }

    @Override
    public int getCount() {
        return frags.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return titles[position];

    }
}
