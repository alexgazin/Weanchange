package com.agazin.myapplication.api;

import com.agazin.myapplication.CryptoModel.Crypto;
import com.agazin.myapplication.CryptoModel.Ticker;
import com.agazin.myapplication.ExchangeModel.ExchangeRates;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ayugazin on 30.11.2017.
 */

public interface CryptoApi {
    @GET("/api/ticker/btc-usd")
    Call<Crypto> getValues();
}
