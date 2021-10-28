package model;

import java.io.Serializable;

public class Prix implements Serializable {


     String cateogire;

     float prix1;

    public Prix(String cateogire, float prix1) {
        this.cateogire = cateogire;
        this.prix1 = prix1;
    }

    public String getCateogire() {
        return cateogire;
    }

    public void setCateogire(String cateogire) {
        this.cateogire = cateogire;
    }

    public float getPrix1() {
        return prix1;
    }

    public void setPrix1(int prix1) {
        this.prix1 = prix1;
    }

}
