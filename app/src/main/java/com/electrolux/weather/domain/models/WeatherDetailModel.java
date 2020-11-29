package com.electrolux.weather.domain.models;

public class WeatherDetailModel {

    private String cityName;
    private String weatherDesc;

    public WeatherDetailModel() {
    }

    public WeatherDetailModel(String cityName, String weatherDesc) {
        this.cityName = cityName;
        this.weatherDesc = weatherDesc;
    }

    public String getCityName() {
        return cityName;
    }

    public String getWeatherDesc() {
        return weatherDesc;
    }

}
