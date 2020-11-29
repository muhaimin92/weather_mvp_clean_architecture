package com.electrolux.weather.data.repo;

import android.util.Log;

import com.electrolux.weather.data.apiservice.WeatherService;
import com.electrolux.weather.data.mappers.WeatherMapper;
import com.electrolux.weather.domain.models.WeatherDetailModel;
import com.electrolux.weather.domain.repositories.WeatherRepository;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.electrolux.weather.data.Constant.API_KEY;
import static com.electrolux.weather.data.Constant.WEATHER_URL;

public class WeatherDataSource implements WeatherRepository {
    private static final String TAG = "--- Weather ---";

    @Override
    public Single<WeatherDetailModel> getCurrentWeather(double latitude, double longitude) {
        Log.d(TAG, "getCurrentWeather: qwerty");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WEATHER_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        WeatherService service = retrofit.create(WeatherService.class);
        return service.getWeatherByLatLon(latitude, longitude, API_KEY)
                .map(weatherResponse -> new WeatherMapper().toWeatherModel(weatherResponse));
    }
}
