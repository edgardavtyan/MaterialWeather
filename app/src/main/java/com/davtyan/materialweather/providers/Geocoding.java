package com.davtyan.materialweather.providers;

import android.content.Context;

import com.doctoror.geocoder.Address;
import com.doctoror.geocoder.Geocoder;
import com.doctoror.geocoder.GeocoderException;

import java.util.Locale;

public class Geocoding {
    private final Geocoder geocoder;

    public Geocoding(Context context) {
        geocoder = new Geocoder(context, Locale.getDefault());
    }

    public LocationInfo getAddressFromLocation(String location) {
        try {
            Address address = geocoder.getFromLocationName(location, 1, true).get(0);
            LocationInfo locationInfo = new LocationInfo();
            locationInfo.setLongitude(address.getLocation().longitude);
            locationInfo.setLatitude(address.getLocation().latitude);
            locationInfo.setCountry(address.getCountry());
            locationInfo.setAdminArea(address.getAdministrativeAreaLevel2());
            return locationInfo;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
