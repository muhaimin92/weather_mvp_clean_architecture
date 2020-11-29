package com.electrolux.weather.data.apiservice;

import com.electrolux.weather.data.models.WeatherResponse;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;


public interface WeatherService {

    @GET("/data/2.5/weather?")
    Single<WeatherResponse> getWeatherByLatLon(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String apiKey);
}
