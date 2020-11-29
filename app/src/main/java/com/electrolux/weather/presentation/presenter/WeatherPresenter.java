package com.electrolux.weather.presentation.presenter;

import android.app.Application;
import android.util.Log;

import com.electrolux.weather.domain.models.WeatherDetailModel;
import com.electrolux.weather.domain.usecases.GetWeatherUseCase;
import com.electrolux.weather.presentation.contract.WeatherContract;
import com.electrolux.weather.presentation.services.LocationService;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class WeatherPresenter implements WeatherContract.Presenter {

    private WeatherContract.View view;
    private GetWeatherUseCase weatherUseCase;
    private LocationService locationService;

    public WeatherPresenter(WeatherContract.View view, Application application) {
        this.view = view;
        weatherUseCase = new GetWeatherUseCase();
        locationService = new LocationService(application);
    }

    @Override
    public void onGetWeatherButtonClicked() {
        view.showProgress();
        locationService.getCurrentLocation(new LocationService.OnLocationReceived() {
            @Override
            public void onSuccess(double latitude, double longitude) {
                weatherUseCase.invoke(latitude, longitude)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<WeatherDetailModel>() {
                            @Override
                            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

                            }

                            @Override
                            public void onSuccess(@io.reactivex.rxjava3.annotations.NonNull WeatherDetailModel weatherDetailModel) {
                                view.hideProgress();
                                view.onFinish(weatherDetailModel);
                            }

                            @Override
                            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                                view.hideProgress();
                                view.onFailure(e.getMessage());
                            }
                        });
            }

            @Override
            public void missingPermission() {
                view.onFailure("missing permission");
            }

        });

    }

    @Override
    public void onDestroy() {

    }
}
