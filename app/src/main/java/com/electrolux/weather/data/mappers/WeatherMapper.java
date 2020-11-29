package com.electrolux.weather.data.mappers;

import android.util.Log;

import com.electrolux.weather.data.models.WeatherResponse;
import com.electrolux.weather.domain.models.WeatherDetailModel;

public class WeatherMapper {

    public WeatherDetailModel toWeatherModel(WeatherResponse response) {
        if (response != null && response.getWeather() !=null) {
            return new WeatherDetailModel(response.getName(),response.getWeather().get(0).getMain());
        }
        return new WeatherDetailModel();
    }
}
