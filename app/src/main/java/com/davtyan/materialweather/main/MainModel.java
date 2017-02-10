package com.davtyan.materialweather.main;

import com.davtyan.materialweather.providers.darksky.DarkSkyWeatherProvider;

public class MainModel implements MainMvp.Model {
    private final String location;
    private final DarkSkyWeatherProvider weatherProvider;

    public MainModel(DarkSkyWeatherProvider weatherProvider, String location) {
        this.weatherProvider = weatherProvider;
        this.location = location;
    }

    @Override
    public void getTodayWeather(TodayWeatherTask.Callback callback) {
        if (weatherProvider.isCachedForecastAvailable()) {
            TodayForecast forecast = weatherProvider.getForecastFromCache(location);
            callback.onWeatherLoaded(forecast);
        } else {
            new TodayWeatherTask(weatherProvider, callback).execute(location);
        }
    }

    @Override
    public void forceRefresh(TodayWeatherTask.Callback callback) {
        new TodayWeatherTask(weatherProvider, callback).execute(location);
    }
}
