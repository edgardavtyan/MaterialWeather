package com.davtyan.materialweather.main;

import com.davtyan.materialweather.providers.darksky.DarkSkyWeatherProvider;

public class MainModel implements MainMvp.Model {
    private final String location;
    private final DarkSkyWeatherProvider darkSkyWeatherProvider;

    public MainModel(DarkSkyWeatherProvider darkSkyWeatherProvider, String location) {
        this.darkSkyWeatherProvider = darkSkyWeatherProvider;
        this.location = location;
    }

    @Override
    public void getTodayWeather(Callback callback) {
        if (darkSkyWeatherProvider.isCachedForecastAvailable()) {
            TodayForecast forecast = new TodayForecast(
                    darkSkyWeatherProvider.getForecastFromCache(),
                    darkSkyWeatherProvider.getFullLocation(location));
            callback.onWeatherLoaded(forecast);
        } else {
            new TodayWeatherTask(darkSkyWeatherProvider, callback).execute(location);
        }
    }

    public interface Callback {
        void onWeatherLoaded(TodayForecast weatherData);
    }
}
