
package com.agazin.myapplication.EthereumModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ethereum {

    @SerializedName("ticker")
    @Expose
    private Ticker ticker;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("error")
    @Expose
    private String error;

    public Ticker getTicker() {
        return ticker;
    }

    public void setTicker(Ticker ticker) {
        this.ticker = ticker;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

}
