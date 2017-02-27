package com.davtyan.materialweather.api;

import com.davtyan.materialweather.utils.WebClient;
import com.google.gson.Gson;

public class WeatherApi {
    private static final String URL = "https://material-weather.herokuapp.com";

    private final WebClient webClient;
    private final Gson gson;

    public WeatherApi(WebClient webClient) {
        this.webClient = webClient;
        this.gson = new Gson();
    }

    public Forecast getForecast(String location) {
        byte[] forecastBytes = webClient.getBytes(URL);
        String forecastString = new String(forecastBytes);
        Forecast forecast = gson.fromJson(forecastString, Forecast.class);
        return forecast;
    }
}
