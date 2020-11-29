package com.electrolux.weather.presentation.services;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

public class LocationService {
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Context context;
    private OnLocationReceived callBack;

    public interface OnLocationReceived {
        void onSuccess(double latitude, double longitude);

        void missingPermission();
    }

    public LocationService(Context context) {
        this.context = context;
        this.fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context);
        this.callBack = callBack;
    }

    public void getCurrentLocation(OnLocationReceived callBack) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            callBack.missingPermission();
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                callBack.onSuccess(location.getLatitude(), location.getLongitude());
            }
        });
    }
}
