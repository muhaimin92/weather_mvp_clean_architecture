package com.electrolux.weather.presentation.contract;

import com.electrolux.weather.domain.models.WeatherDetailModel;

public interface WeatherContract {
    interface View {
        void showProgress();
        void hideProgress();
        void onFailure(String errMsg);
        void onFinish(WeatherDetailModel weatherDetailModel);
    }


    interface Presenter {
        void onGetWeatherButtonClicked();
        void onDestroy();
    }
}
