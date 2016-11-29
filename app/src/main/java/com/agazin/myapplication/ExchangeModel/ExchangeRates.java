
package com.agazin.myapplication.ExchangeModel;

import javax.annotation.Generated;

import com.agazin.myapplication.ExchangeModel.Valute;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.Call;


public class ExchangeRates {


    @SerializedName("Date")
    @Expose
    private String date;
    @SerializedName("PreviousDate")
    @Expose
    private String previousDate;
    @SerializedName("PreviousURL")
    @Expose
    private String previousURL;
    @SerializedName("Timestamp")
    @Expose
    private String timestamp;
    @SerializedName("Valute")
    @Expose
    private Valute valute;

    /**
     *
     * @return
     *     The date
     */
    public String getDate() {
        return date;
    }

    /**
     *
     * @param date
     *     The Date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     *
     * @return
     *     The previousDate
     */
    public String getPreviousDate() {
        return previousDate;
    }

    /**
     *
     * @param previousDate
     *     The PreviousDate
     */
    public void setPreviousDate(String previousDate) {
        this.previousDate = previousDate;
    }

    /**
     *
     * @return
     *     The previousURL
     */
    public String getPreviousURL() {
        return previousURL;
    }

    /**
     *
     * @param previousURL
     *     The PreviousURL
     */
    public void setPreviousURL(String previousURL) {
        this.previousURL = previousURL;
    }

    /**
     *
     * @return
     *     The timestamp
     */
    public String getTimestamp() {
        return timestamp;
    }

    /**
     *
     * @param timestamp
     *     The Timestamp
     */
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    /**
     *
     * @return
     *     The valute
     */
    public Valute getValute() {
        return valute;
    }

    /**
     *
     * @param valute
     *     The Valute
     */
    public void setValute(Valute valute) {
        this.valute = valute;
    }

}