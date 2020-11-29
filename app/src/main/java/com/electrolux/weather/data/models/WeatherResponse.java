package com.electrolux.weather.data.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherResponse {

    @SerializedName("coord")
    private Coordinate coordinate;
    @SerializedName("weather")
    private List<Weather> weather;
    @SerializedName("name")
    private String name;

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public String getName() {
        return name;
    }

    public static class Coordinate{
        private double lon;
        private double lat;

        public double getLon() {
            return lon;
        }

        public double getLat() {
            return lat;
        }
    }

    public static class Weather{
        private String main;
        private String description;

        public String getMain() {
            return main;
        }

        public String getDescription() {
            return description;
        }
    }

}
