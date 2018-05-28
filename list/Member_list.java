package tpghksdl521.nokizzoneapp.list;

/**
 * Created by tpghk on 2018-04-10.
 */

public class Member_list {
    String name,address,viewex;
    int img;
    double v,v1;
    double distance;

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public Member_list(String name, String address, String viewex, int img, double v, double v1) {
        this.name = name;
        this.address = address;
        this.viewex = viewex;
        this.img = img;
        this.v=v;
        this.v1=v1;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getViewex() {
        return viewex;
    }

    public void setViewex(String viewex) {
        this.viewex = viewex;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
    public double getV() {
        return v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public double getV1() {
        return v1;
    }

    public void setV1(double v1) {
        this.v1 = v1;
    }



}
