package com.davtyan.materialweather.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;

public class Geocoding {
    private final Geocoder geocoder;

    public Geocoding(Context context) {
        geocoder = new Geocoder(context);
    }

    public Address getAddressFromLocation(String location) {
        try {
            return geocoder.getFromLocationName(location, 1).get(0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
