
package com.agazin.myapplication.weatherbitModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("rh")
    @Expose
    private Integer rh;
    @SerializedName("pod")
    @Expose
    private String pod;
    @SerializedName("pres")
    @Expose
    private Double pres;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("weather")
    @Expose
    private Weather weather;
    @SerializedName("country_code")
    @Expose
    private String countryCode;
    @SerializedName("clouds")
    @Expose
    private Integer clouds;
    @SerializedName("vis")
    @Expose
    private Integer vis;
    @SerializedName("wind_spd")
    @Expose
    private Integer windSpd;
    @SerializedName("wind_cdir_full")
    @Expose
    private String windCdirFull;
    @SerializedName("app_temp")
    @Expose
    private Double appTemp;
    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("state_code")
    @Expose
    private String stateCode;
    @SerializedName("ts")
    @Expose
    private Integer ts;
    @SerializedName("elev_angle")
    @Expose
    private Integer elevAngle;
    @SerializedName("h_angle")
    @Expose
    private Integer hAngle;
    @SerializedName("dewpt")
    @Expose
    private Double dewpt;
    @SerializedName("ob_time")
    @Expose
    private String obTime;
    @SerializedName("uv")
    @Expose
    private Integer uv;
    @SerializedName("sunset")
    @Expose
    private String sunset;
    @SerializedName("sunrise")
    @Expose
    private String sunrise;
    @SerializedName("city_name")
    @Expose
    private String cityName;
    @SerializedName("precip")
    @Expose
    private Double precip;
    @SerializedName("station")
    @Expose
    private String station;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("dhi")
    @Expose
    private Integer dhi;
    @SerializedName("datetime")
    @Expose
    private String datetime;
    @SerializedName("temp")
    @Expose
    private Integer temp;
    @SerializedName("wind_dir")
    @Expose
    private Integer windDir;
    @SerializedName("slp")
    @Expose
    private Integer slp;
    @SerializedName("wind_cdir")
    @Expose
    private String windCdir;

    public Integer getRh() {
        return rh;
    }

    public void setRh(Integer rh) {
        this.rh = rh;
    }

    public String getPod() {
        return pod;
    }

    public void setPod(String pod) {
        this.pod = pod;
    }

    public Double getPres() {
        return pres;
    }

    public void setPres(Double pres) {
        this.pres = pres;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        this.clouds = clouds;
    }

    public Integer getVis() {
        return vis;
    }

    public void setVis(Integer vis) {
        this.vis = vis;
    }

    public Integer getWindSpd() {
        return windSpd;
    }

    public void setWindSpd(Integer windSpd) {
        this.windSpd = windSpd;
    }

    public String getWindCdirFull() {
        return windCdirFull;
    }

    public void setWindCdirFull(String windCdirFull) {
        this.windCdirFull = windCdirFull;
    }

    public Double getAppTemp() {
        return appTemp;
    }

    public void setAppTemp(Double appTemp) {
        this.appTemp = appTemp;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getStateCode() {
        return stateCode;
    }

    public void setStateCode(String stateCode) {
        this.stateCode = stateCode;
    }

    public Integer getTs() {
        return ts;
    }

    public void setTs(Integer ts) {
        this.ts = ts;
    }

    public Integer getElevAngle() {
        return elevAngle;
    }

    public void setElevAngle(Integer elevAngle) {
        this.elevAngle = elevAngle;
    }

    public Integer getHAngle() {
        return hAngle;
    }

    public void setHAngle(Integer hAngle) {
        this.hAngle = hAngle;
    }

    public Double getDewpt() {
        return dewpt;
    }

    public void setDewpt(Double dewpt) {
        this.dewpt = dewpt;
    }

    public String getObTime() {
        return obTime;
    }

    public void setObTime(String obTime) {
        this.obTime = obTime;
    }

    public Integer getUv() {
        return uv;
    }

    public void setUv(Integer uv) {
        this.uv = uv;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Double getPrecip() {
        return precip;
    }

    public void setPrecip(Double precip) {
        this.precip = precip;
    }

    public String getStation() {
        return station;
    }

    public void setStation(String station) {
        this.station = station;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Integer getDhi() {
        return dhi;
    }

    public void setDhi(Integer dhi) {
        this.dhi = dhi;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public Integer getTemp() {
        return temp;
    }

    public void setTemp(Integer temp) {
        this.temp = temp;
    }

    public Integer getWindDir() {
        return windDir;
    }

    public void setWindDir(Integer windDir) {
        this.windDir = windDir;
    }

    public Integer getSlp() {
        return slp;
    }

    public void setSlp(Integer slp) {
        this.slp = slp;
    }

    public String getWindCdir() {
        return windCdir;
    }

    public void setWindCdir(String windCdir) {
        this.windCdir = windCdir;
    }

}
