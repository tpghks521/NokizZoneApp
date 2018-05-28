package tpghksdl521.nokizzoneapp.location_tab;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import tpghksdl521.nokizzoneapp.R;
import tpghksdl521.nokizzoneapp.TabList_adapter_Member;
import tpghksdl521.nokizzoneapp.list.Member_list;

/**
 * Created by tpghk on 2018-03-30.
 */

@SuppressLint("ValidFragment")
public class Member extends Fragment {
    TabList_adapter_Member tabList_adapterSeoul;

    RecyclerView recyclerViews;
    ArrayList<Member_list> lists;





    @SuppressLint("ValidFragment")
    public Member(ArrayList<Member_list> lists) {
        this.lists = lists;
    }
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.member_layout,container,false);

        context  = getContext();
//데이터 추가작업




        recyclerViews=view.findViewById(R.id.recyclerview);
        tabList_adapterSeoul = new TabList_adapter_Member(context,lists);
        recyclerViews.setAdapter(tabList_adapterSeoul);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViews.setLayoutManager(layoutManager);
        recyclerViews.setItemAnimator(new DefaultItemAnimator());
        //어댑터연결

        return  view;
    }//oncreate

// View.OnClickListener onClickListener = new View.OnClickListener() {
//     @Override
//     public void onClick(View view) {
//         Intent intent = new Intent(context,NaverMapActivity.class);
//         startActivity(intent);
//     }
// };//onclickListner


    }//class



