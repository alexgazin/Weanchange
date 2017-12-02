package com.agazin.myapplication.api;

import com.agazin.myapplication.EthereumModel.Ethereum;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ayugazin on 30.11.2017.
 */

public interface EthereumApi {
    @GET("/api/ticker/eth-usd")
    Call<Ethereum> getValues();
}
