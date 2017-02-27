package com.davtyan.materialweather.main;

import android.os.AsyncTask;

import com.davtyan.materialweather.api.Forecast;
import com.davtyan.materialweather.api.WeatherApi;

public class TodayWeatherTask extends AsyncTask<String, Void, Forecast> {

    private final WeatherApi weatherApi;
    private final MainMvp.Model.OnWeatherLoadedListener listener;

    public TodayWeatherTask(
            WeatherApi weatherApi,
            MainMvp.Model.OnWeatherLoadedListener listener) {
        this.weatherApi = weatherApi;
        this.listener = listener;
    }

    @Override
    protected Forecast doInBackground(String... location) {
        return weatherApi.getForecast(location[0]);
    }

    @Override
    protected void onPostExecute(Forecast todayForecast) {
        listener.onWeatherLoaded(todayForecast);
    }
}
