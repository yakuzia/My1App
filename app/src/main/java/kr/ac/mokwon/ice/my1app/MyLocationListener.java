package kr.ac.mokwon.ice.my1app;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;

/**
 * Created by yin on 2016-03-07.
 */
public class MyLocationListener implements LocationListener {
    public double latitude, longitude, altitude;
    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        altitude = location.getAltitude();

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
