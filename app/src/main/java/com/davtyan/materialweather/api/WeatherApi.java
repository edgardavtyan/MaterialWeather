package com.davtyan.materialweather.api;

import com.davtyan.materialweather.utils.WebClient;
import com.google.gson.Gson;

public class WeatherApi {

    private final WebClient webClient;
    private final Gson gson;

    public WeatherApi(WebClient webClient) {
        this.webClient = webClient;
        this.gson = new Gson();
    }

    public Forecast getForecast(String location) {
        String url = "https://material-weather.herokuapp.com/" + location;
        byte[] forecastBytes = webClient.getBytes(url);
        String forecastString = new String(forecastBytes);
        Forecast forecast = gson.fromJson(forecastString, Forecast.class);
        return forecast;
    }
}
