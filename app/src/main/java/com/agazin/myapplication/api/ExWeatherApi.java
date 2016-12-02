package com.agazin.myapplication.api;

import com.agazin.myapplication.ExWeatherModel.ExWeather;
import com.agazin.myapplication.WeatherModel.Weather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ayugazin on 02.12.16.
 */

public interface ExWeatherApi {
    @GET("/data/2.5/forecast")
    Call<ExWeather> getValues(@Query("appid") String appid,
                              @Query("q") String town,
                              @Query("units") String metric,
                              @Query("lang") String lang);
}
