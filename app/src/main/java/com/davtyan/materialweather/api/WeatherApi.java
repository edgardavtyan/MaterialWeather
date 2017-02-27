package com.davtyan.materialweather.api;

import com.davtyan.materialweather.utils.WebClient;
import com.google.gson.Gson;

public class WeatherApi {

    private static final String URL_BASE = "https://material-weather.herokuapp.com/";

    private final WebClient webClient;
    private final WeatherCache cache;
    private final Gson gson;

    public WeatherApi(WebClient webClient, WeatherCache cache) {
        this.webClient = webClient;
        this.cache = cache;
        this.gson = new Gson();
    }

    public Forecast getForecast(String location) {
        if (!cache.isOutdated()) return cache.get();

        String url = URL_BASE + location;
        String forecastString = new String(webClient.getBytes(url));
        Forecast forecast = gson.fromJson(forecastString, Forecast.class);
        cache.save(forecast);
        return forecast;
    }

    public Forecast getForecastFromCache() {
        return cache.get();
    }

    public long getLastUpdateTime() {
        return cache.getLastUpdateTime();
    }
}
