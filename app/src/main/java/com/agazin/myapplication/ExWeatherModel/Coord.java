
package com.agazin.myapplication.ExWeatherModel;;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Coord {

    @SerializedName("lon")
    @Expose
    private Float lon;
    @SerializedName("lat")
    @Expose
    private Float lat;

    /**
     * 
     * @return
     *     The lon
     */
    public Float getLon() {
        return lon;
    }

    /**
     * 
     * @param lon
     *     The lon
     */
    public void setLon(Float lon) {
        this.lon = lon;
    }

    /**
     * 
     * @return
     *     The lat
     */
    public Float getLat() {
        return lat;
    }

    /**
     * 
     * @param lat
     *     The lat
     */
    public void setLat(Float lat) {
        this.lat = lat;
    }

}
