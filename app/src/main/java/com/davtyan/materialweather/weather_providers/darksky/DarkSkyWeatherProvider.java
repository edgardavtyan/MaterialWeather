package com.davtyan.materialweather.weather_providers.darksky;

import android.location.Address;

import com.davtyan.materialweather.utils.Geocoding;
import com.davtyan.materialweather.utils.WebClient;

public class DarkSkyWeatherProvider {
    private final WebClient webClient;
    private final Geocoding geocoding;
    private final String apiKey;

    public DarkSkyWeatherProvider(WebClient webClient, Geocoding geocoding, String apiKey) {
        this.webClient = webClient;
        this.geocoding = geocoding;
        this.apiKey = apiKey;
    }

    public String getForecastForToday(String location) {
        Address address = geocoding.getAddressFromLocation(location);
        return webClient.getString(getUrl(address.getLatitude(), address.getLongitude()));
    }

    public Address getFullLocation(String location) {
        return geocoding.getAddressFromLocation(location);
    }

    private String getUrl(double latitude, double longitude) {
        return String.format(
                "https://api.darksky.net/forecast/%s/%f,%f?units=si&exclude=minutely",
                apiKey, latitude, longitude);
    }
}
