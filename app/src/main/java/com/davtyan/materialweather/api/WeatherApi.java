package com.davtyan.materialweather.api;

import com.davtyan.materialweather.utils.WebClient;
import com.google.gson.Gson;

public class WeatherApi {

    private final WebClient webClient;
    private final WeatherCache cache;
    private final Gson gson;

    public WeatherApi(WebClient webClient, WeatherCache cache) {
        this.webClient = webClient;
        this.cache = cache;
        this.gson = new Gson();
    }

    public Forecast getForecast(String location) {
        if (cache.isOutdated()) {
            String url = "https://material-weather.herokuapp.com/" + location;
            byte[] forecastBytes = webClient.getBytes(url);
            String forecastString = new String(forecastBytes);
            Forecast forecast = gson.fromJson(forecastString, Forecast.class);
            cache.save(forecast);
            return forecast;
        } else {
            return cache.get();
        }
    }
}
