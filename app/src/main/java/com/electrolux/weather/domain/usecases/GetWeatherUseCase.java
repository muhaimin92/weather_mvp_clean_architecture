package com.electrolux.weather.domain.usecases;

import com.electrolux.weather.data.repo.WeatherDataSource;
import com.electrolux.weather.domain.models.WeatherDetailModel;
import com.electrolux.weather.domain.repositories.WeatherRepository;

import io.reactivex.rxjava3.core.Single;

public class GetWeatherUseCase {

    private WeatherRepository weatherRepository;

    public GetWeatherUseCase() {
        this.weatherRepository = new WeatherDataSource();
    }

    public Single<WeatherDetailModel> invoke(double latitude, double longitude) {
        return weatherRepository.getCurrentWeather(latitude,longitude);
    }
}
