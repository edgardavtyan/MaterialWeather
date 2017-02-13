package com.davtyan.materialweather.providers;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;

public class Geocoding {
    private final Geocoder geocoder;

    public Geocoding(Context context) {
        geocoder = new Geocoder(context);
    }

    public LocationInfo getAddressFromLocation(String location) {
        try {
            Address address = geocoder.getFromLocationName(location, 1).get(0);
            LocationInfo locationInfo = new LocationInfo();
            locationInfo.setLongitude(address.getLongitude());
            locationInfo.setLatitude(address.getLatitude());
            locationInfo.setCountry(address.getCountryName());
            locationInfo.setAdminArea(address.getAdminArea());
            return locationInfo;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
