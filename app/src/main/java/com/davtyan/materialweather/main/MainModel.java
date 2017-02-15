package com.davtyan.materialweather.main;

import com.davtyan.materialweather.providers.darksky.DarkSkyWeatherProvider;

import lombok.Setter;

public class MainModel implements MainMvp.Model {
    private final String location;
    private final DarkSkyWeatherProvider weatherProvider;

    private @Setter OnWeatherLoadedListener onWeatherLoadedListener;

    public MainModel(DarkSkyWeatherProvider weatherProvider, String location) {
        this.weatherProvider = weatherProvider;
        this.location = location;
    }

    @Override
    public void getTodayWeather() {
        if (weatherProvider.isNonOutdatedCachedForecastAvailable()) {
            TodayForecast forecast = weatherProvider.getForecastFromCache();
            onWeatherLoadedListener.onWeatherLoaded(forecast);
        } else {
            new TodayWeatherTask(weatherProvider, onWeatherLoadedListener).execute(location);
        }
    }

    @Override
    public void forceRefresh() {
        new TodayWeatherTask(weatherProvider, onWeatherLoadedListener).execute(location);
    }
}
