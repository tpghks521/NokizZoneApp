package tpghksdl521.nokizzoneapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


import tpghksdl521.nokizzoneapp.list.Member_list;


/**
 * Created by tpghk on 2018-04-01.
 */

public class TabList_adapter_Member extends RecyclerView.Adapter {

    Context context;
    List<Member_list> memberLists;

      static int positionmark;

    public TabList_adapter_Member(){

    }

    public TabList_adapter_Member(Context context, List<Member_list> list) {
        this.context = context;
        this.memberLists=list;

    }



    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.item_list_layout,parent,false);



        return new VH(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        VH vh =(VH)holder;
         final Member_list member_list = memberLists.get(position);
        vh.name.setText(member_list.getName());
        vh.address.setText(member_list.getAddress());
        vh.viewex.setText(member_list.getViewex());

        double dis=member_list.getDistance()/1000;
        String s=String.valueOf(dis);
        int i=s.indexOf(".");
        s=s.substring(0,i+2);

        vh.distance.setText("거리 "+s+"Km");

        // 이미지 연결 글라이드로 하던가 비트로하던가 아직안함

vh.location.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(context,NaverMapActivity.class);
        System.out.println(member_list.getV()+"vvv"+member_list.getV1());


        if(member_list.getAddress().contains("경기")){

            positionmark =position+1000;

        }else if(member_list.getAddress().contains("부산")){
            positionmark =position+2000;

        }else if(member_list.getAddress().contains("제주")){
            positionmark =position+3000;

        }else if(member_list.getAddress().contains("서울")){

            positionmark=position;
        }else{
            positionmark=position+4000;
        }
        context.startActivity(intent);
    }
});


    }

    @Override
    public int getItemCount() {
        return memberLists.size();
    }

    class VH extends RecyclerView.ViewHolder{
        TextView location;
       public TextView name,address,viewex,distance;
        ImageView img;

        public VH(View itemView){
            super(itemView);

            name=itemView.findViewById(R.id.name);
            address=itemView.findViewById(R.id.address);
            viewex=itemView.findViewById(R.id.textlist);
            img =itemView.findViewById(R.id.imageview);
            distance=itemView.findViewById(R.id.distance);

            location=itemView.findViewById(R.id.location);


        }


    }//VH



}
