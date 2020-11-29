package com.electrolux.weather.domain.repositories;

import com.electrolux.weather.domain.models.WeatherDetailModel;

import io.reactivex.rxjava3.core.Single;

public interface WeatherRepository {

    Single<WeatherDetailModel> getCurrentWeather(double latitude, double longitude);
}

