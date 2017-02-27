package com.davtyan.materialweather.main;

import com.davtyan.materialweather.api.WeatherApi;

import lombok.Setter;

public class MainModel implements MainMvp.Model {
    private final String location;
    private final WeatherApi weatherApi;

    private @Setter OnWeatherLoadedListener onWeatherLoadedListener;

    public MainModel(WeatherApi weatherApi, String location) {
        this.weatherApi = weatherApi;
        this.location = location;
    }

    @Override
    public void getTodayWeather() {
        new TodayWeatherTask(weatherApi, onWeatherLoadedListener).execute(location);
    }

    @Override
    public void forceRefresh() {
        new TodayWeatherTask(weatherApi, onWeatherLoadedListener).execute(location);
    }
}
