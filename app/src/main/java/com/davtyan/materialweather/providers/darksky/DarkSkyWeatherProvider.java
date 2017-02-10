package com.davtyan.materialweather.providers.darksky;

import android.location.Address;

import com.davtyan.materialweather.utils.Geocoding;
import com.davtyan.materialweather.utils.WebClient;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DarkSkyWeatherProvider {
    private static final int HOURS_UNTIL_REFRESH = 3;

    private final WebClient webClient;
    private final Geocoding geocoding;
    private final DarkSkyForecastCache cache;
    private final String apiKey;

    public DarkSkyWeatherProvider(
            WebClient webClient,
            Geocoding geocoding,
            DarkSkyForecastCache cache,
            String apiKey) {
        this.webClient = webClient;
        this.geocoding = geocoding;
        this.cache = cache;
        this.apiKey = apiKey;
    }

    public String getForecastForToday(String location) {
        long dateNow = new Date().getTime();
        long dateCache = cache.getCachedFileDate();
        long dateDiffHours = TimeUnit.MILLISECONDS.toHours(dateNow - dateCache);
        if (dateDiffHours > HOURS_UNTIL_REFRESH) {
            Address address = geocoding.getAddressFromLocation(location);
            String forecast = webClient.getString(getUrl(address.getLatitude(), address.getLongitude()));
            cache.save(forecast);
            return forecast;
        } else {
            return cache.get();
        }
    }

    public Address getFullLocation(String location) {
        return geocoding.getAddressFromLocation(location);
    }

    public String getForecastFromCache() {
        return cache.get();
    }

    public boolean isCachedForecastAvailable() {
        return cache.exists();
    }

    private String getUrl(double latitude, double longitude) {
        return String.format(
                "https://api.darksky.net/forecast/%s/%f,%f?units=si&exclude=minutely",
                apiKey, latitude, longitude);
    }
}
