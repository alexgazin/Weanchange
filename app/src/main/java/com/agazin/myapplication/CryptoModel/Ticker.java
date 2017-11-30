
package com.agazin.myapplication.CryptoModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ticker {

    @SerializedName("base")
    @Expose
    private String base;
    @SerializedName("target")
    @Expose
    private String target;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("volume")
    @Expose
    private String volume;
    @SerializedName("change")
    @Expose
    private String change;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getChange() {
        return change;
    }

    public void setChange(String change) {
        this.change = change;
    }

}
