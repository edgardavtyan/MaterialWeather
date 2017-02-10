package com.davtyan.materialweather.main;

import android.os.AsyncTask;

import com.davtyan.materialweather.providers.darksky.DarkSkyWeatherProvider;

public class TodayWeatherTask extends AsyncTask<String, Void, TodayForecast> {

    private final DarkSkyWeatherProvider weatherProvider;
    private final MainModel.Callback callback;

    public TodayWeatherTask(DarkSkyWeatherProvider weatherProvider, MainModel.Callback callback) {
        this.weatherProvider = weatherProvider;
        this.callback = callback;
    }

    @Override
    protected TodayForecast doInBackground(String... location) {
        return new TodayForecast(
                weatherProvider.getForecastForToday(location[0]),
                weatherProvider.getFullLocation(location[0]));
    }

    @Override
    protected void onPostExecute(TodayForecast todayForecast) {
        callback.onWeatherLoaded(todayForecast);
    }
}
