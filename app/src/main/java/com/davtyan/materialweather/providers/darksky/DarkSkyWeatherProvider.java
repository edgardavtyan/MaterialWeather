package com.davtyan.materialweather.providers.darksky;

import android.location.Address;
import android.util.Log;

import com.davtyan.materialweather.main.TodayForecast;
import com.davtyan.materialweather.utils.Geocoding;
import com.davtyan.materialweather.utils.WebClient;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DarkSkyWeatherProvider {
    private static final int HOURS_UNTIL_REFRESH = 1;

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

    public TodayForecast getForecastForToday(String location) {
        if (isNonOutdatedCachedForecastAvailable()) {
            Log.d(getClass().getSimpleName(), "cache");
            return new TodayForecast(cache.get(), getFullLocation(location));
        } else {
            Address address = geocoding.getAddressFromLocation(location);
            String forecast = webClient.getString(getUrl(address.getLatitude(), address.getLongitude()));
            cache.save(forecast);
            Log.d(getClass().getSimpleName(), "api");
            return new TodayForecast(forecast, getFullLocation(location));
        }
    }

    public Address getFullLocation(String location) {
        return geocoding.getAddressFromLocation(location);
    }

    public TodayForecast getForecastFromCache(String location) {
        Log.d(getClass().getSimpleName(), "fromCache");
        return new TodayForecast(cache.get(), getFullLocation(location));
    }

    public boolean isNonOutdatedCachedForecastAvailable() {
        long dateNow = new Date().getTime();
        long dateCache = cache.getCachedFileDate();
        long dateDiffHours = TimeUnit.MILLISECONDS.toHours(dateNow - dateCache);
        return cache.exists() && dateDiffHours < HOURS_UNTIL_REFRESH;
    }

    private String getUrl(double latitude, double longitude) {
        return String.format(
                "https://api.darksky.net/forecast/%s/%f,%f?units=si&exclude=minutely",
                apiKey, latitude, longitude);
    }
}
