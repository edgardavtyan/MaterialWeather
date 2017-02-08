package com.davtyan.materialweather.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class Geocoding {
    private final Geocoder geocoder;

    @Getter
    @RequiredArgsConstructor
    public class Coordinates {
        private final double latitude;
        private final double longitude;
    }

    public Geocoding(Context context) {
        geocoder = new Geocoder(context);
    }

    public Coordinates getCoordinatesFromLocation(String location) {
        try {
            Address address = geocoder.getFromLocationName(location, 1).get(0);
            return new Coordinates(address.getLatitude(), address.getLongitude());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
