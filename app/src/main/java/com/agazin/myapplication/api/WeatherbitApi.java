package com.agazin.myapplication.api;

import com.agazin.myapplication.weatherbitModel.Weatherbit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ayugazin on 05.12.2017.
 */

public interface WeatherbitApi {
    @GET("/v2.0/current")
    Call<Weatherbit> getValues(@Query("city") String town,
                               @Query("key") String key);
}
