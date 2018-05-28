package tpghksdl521.nokizzoneapp;

/**
 * Created by tpghk on 2018-04-04.
 */

public class KizzoneList {

    String address;
    String name;
    String further_explanation;

    double v,v1;

    public KizzoneList(String address, String name, String further_explanation,double v, double v1) {
        this.address = address;
        this.name = name;
        this.further_explanation = further_explanation;

        this.v = v;
        this.v1 = v1;
    }
}
