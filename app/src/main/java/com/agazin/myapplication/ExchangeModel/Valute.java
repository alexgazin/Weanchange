package com.agazin.myapplication.ExchangeModel;

import com.agazin.myapplication.ExchangeModel.USD;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by ayugazin on 16.11.16.
 */

public class Valute {
    @SerializedName("USD")
    @Expose
    private USD uSD;

    public EUR geteUR() {
        return eUR;
    }

    public void seteUR(EUR eUR) {
        this.eUR = eUR;
    }

    @SerializedName("EUR")
    @Expose

    private EUR eUR;

    public USD getuSD() {
        return uSD;
    }

    public void setuSD(USD uSD) {
        this.uSD = uSD;
    }
}