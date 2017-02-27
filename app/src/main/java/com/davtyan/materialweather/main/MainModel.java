package com.davtyan.materialweather.main;

import com.davtyan.materialweather.api.WeatherApi;
import com.davtyan.materialweather.utils.NetworkInfo;

import lombok.Setter;

public class MainModel implements MainMvp.Model {
    private final NetworkInfo networkInfo;
    private final String location;
    private final WeatherApi weatherApi;

    private @Setter OnWeatherLoadedListener onWeatherLoadedListener;

    public MainModel(WeatherApi weatherApi, NetworkInfo networkInfo, String location) {
        this.weatherApi = weatherApi;
        this.networkInfo = networkInfo;
        this.location = location;
    }

    @Override
    public void getTodayWeather() {
        if (networkInfo.isConnectedToNetwork()) {
            new TodayWeatherTask(weatherApi, onWeatherLoadedListener).execute(location);
        } else {
            onWeatherLoadedListener.onWeatherLoaded(weatherApi.getForecastFromCache());
        }
    }

    @Override
    public long getLastUpdateTime() {
        return weatherApi.getLastUpdateTime();
    }

    @Override
    public void forceRefresh() {
        new TodayWeatherTask(weatherApi, onWeatherLoadedListener).execute(location);
    }
}
