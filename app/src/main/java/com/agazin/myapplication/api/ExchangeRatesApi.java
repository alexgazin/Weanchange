package com.agazin.myapplication.api;

import com.agazin.myapplication.ExchangeModel.ExchangeRates;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ayugazin on 13.11.16.
 */

public interface ExchangeRatesApi {
    @GET("/daily_json.js")
    Call<ExchangeRates> getValues();
}

