
package com.agazin.myapplication.ExWeatherModel;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Wind {

    @SerializedName("speed")
    @Expose
    private Float speed;
    @SerializedName("deg")
    @Expose
    private Float deg;

    /**
     * 
     * @return
     *     The speed
     */
    public Float getSpeed() {
        return speed;
    }

    /**
     * 
     * @param speed
     *     The speed
     */
    public void setSpeed(Float speed) {
        this.speed = speed;
    }

    /**
     * 
     * @return
     *     The deg
     */
    public Float getDeg() {
        return deg;
    }

    /**
     * 
     * @param deg
     *     The deg
     */
    public void setDeg(Float deg) {
        this.deg = deg;
    }

}
