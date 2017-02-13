package com.davtyan.materialweather.main;

import android.os.AsyncTask;

import com.davtyan.materialweather.providers.darksky.DarkSkyWeatherProvider;

public class TodayWeatherTask extends AsyncTask<String, Void, TodayForecast> {

    private final DarkSkyWeatherProvider weatherProvider;
    private final MainMvp.Model.OnWeatherLoadedListener listener;

    public TodayWeatherTask(
            DarkSkyWeatherProvider weatherProvider,
            MainMvp.Model.OnWeatherLoadedListener listener) {
        this.weatherProvider = weatherProvider;
        this.listener = listener;
    }

    @Override
    protected TodayForecast doInBackground(String... location) {
        return weatherProvider.getForecastForToday(location[0]);
    }

    @Override
    protected void onPostExecute(TodayForecast todayForecast) {
        listener.onWeatherLoaded(todayForecast);
    }
}
