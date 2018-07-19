package tpghksdl521.nokizzoneapp;


import android.content.Context;

import android.util.AttributeSet;

import com.nhn.android.maps.NMapView;

import com.nhn.android.maps.overlay.NMapPOIdata;

import com.nhn.android.mapviewer.overlay.NMapOverlayManager;
import com.nhn.android.mapviewer.overlay.NMapPOIdataOverlay;
import com.nhn.android.mapviewer.overlay.NMapResourceProvider;

/**
 * Created by tpghk on 2018-03-27.
 */

public class NaverMapview extends NMapView {


    NMapResourceProvider nMapResourceProvider;
    int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }


    NMapPOIdata poIdata;
    NMapPOIdataOverlay poiDataOverlay;

    public NaverMapview(final Context context, AttributeSet attributeSet) {
        super(context, attributeSet);






        nMapResourceProvider=new NMapViewerResourceProvider(context);

        NMapOverlayManager nMapOverlayManager = new NMapOverlayManager(context, this, nMapResourceProvider);
        int markid= NMapPOIflagType.PIN;
        poIdata = new NMapPOIdata(DatabaseClass.DBkizzoneLists.size(), nMapResourceProvider);
        for(int locNum=0;locNum<MainActivity.arrayLists.size();locNum++) {



            poIdata.beginPOIdata(MainActivity.arrayLists.get(locNum).size());
            for (int i = 0; i < MainActivity.arrayLists.get(locNum).size(); i++) {

                poIdata.addPOIitem(MainActivity.arrayLists.get(locNum).get(i).getV(),
                        MainActivity.arrayLists.get(locNum).get(i).getV1(),
                        MainActivity.arrayLists.get(locNum).get(i).getName(),
                        markid, i+locNum*1000);
                        System.out.println(i+locNum*1000+"num");

            }

        }
        poIdata.endPOIdata();
        poiDataOverlay = nMapOverlayManager.createPOIdataOverlay(poIdata, null);
       System.out.println(TabList_adapter_Member.positionmark+"다른도시");
        poiDataOverlay.selectPOIitemBy(TabList_adapter_Member.positionmark,true);






    }//constructer
}//class









































































































