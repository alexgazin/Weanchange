package com.agazin.myapplication.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

import com.agazin.myapplication.WeatherModel.Weather;

/**
 * Created by ayugazin on 17.11.16.
 */

public interface WeatherApi {
    @GET("/data/2.5/weather")
    Call<Weather> getValues(@Query("appid") String appid,
                            @Query("q") String town,
                            @Query("units") String metric,
                            @Query("lang") String lang);
}