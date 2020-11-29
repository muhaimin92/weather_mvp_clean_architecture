package com.electrolux.weather.presentation.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.electrolux.weather.R;
import com.electrolux.weather.domain.models.WeatherDetailModel;
import com.electrolux.weather.presentation.contract.WeatherContract;
import com.electrolux.weather.presentation.presenter.WeatherPresenter;

public class MainActivity extends AppCompatActivity implements WeatherContract.View {

    private Button weatherButton;
    private TextView cityTextView,weatherTextView;
    private ProgressBar progressBar;
    WeatherContract.Presenter presenter;
    private static final int PERMISSION_REQUEST_CODE = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        weatherButton = findViewById(R.id.weatherButton);
        cityTextView = findViewById(R.id.cityTextView);
        weatherTextView = findViewById(R.id.weatherTextView);
        progressBar = findViewById(R.id.progressBar);
        presenter = new WeatherPresenter(this,getApplication());

        weatherButton.setOnClickListener(v -> {
            presenter.onGetWeatherButtonClicked();
        });

        if (!checkPermission()) {
            requestPermission();
        }
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onFailure(String errMsg) {
        Toast.makeText(getApplicationContext(),errMsg,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFinish(WeatherDetailModel weatherDetailModel) {
        cityTextView.setText(weatherDetailModel.getCityName());
        weatherTextView.setText(weatherDetailModel.getWeatherDesc());
    }


    private boolean checkPermission() {
        return ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[], @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Permission Granted", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getApplicationContext(), "You need to allow access permissions", Toast.LENGTH_SHORT).show();
            }
        }
    }

}