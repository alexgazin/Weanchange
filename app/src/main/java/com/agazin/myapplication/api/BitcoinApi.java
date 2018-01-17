package com.agazin.myapplication.api;

import com.agazin.myapplication.BitcoinModel.Crypto;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ayugazin on 30.11.2017.
 */

public interface BitcoinApi {
    @GET("/api/ticker/btc-usd")
    Call<Crypto> getValues();
}
