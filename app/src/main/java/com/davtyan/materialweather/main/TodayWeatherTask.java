package com.davtyan.materialweather.main;

import android.os.AsyncTask;

import com.davtyan.materialweather.providers.darksky.DarkSkyWeatherProvider;

public class TodayWeatherTask extends AsyncTask<String, Void, TodayForecast> {

    private final DarkSkyWeatherProvider weatherProvider;
    private final Callback callback;

    public interface Callback {
        void onWeatherLoaded(TodayForecast weatherData);
    }

    public TodayWeatherTask(DarkSkyWeatherProvider weatherProvider, Callback callback) {
        this.weatherProvider = weatherProvider;
        this.callback = callback;
    }

    @Override
    protected TodayForecast doInBackground(String... location) {
        return weatherProvider.getForecastForToday(location[0]);
    }

    @Override
    protected void onPostExecute(TodayForecast todayForecast) {
        callback.onWeatherLoaded(todayForecast);
    }
}
