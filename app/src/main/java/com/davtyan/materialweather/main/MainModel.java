package com.davtyan.materialweather.main;

import android.location.Address;
import android.os.AsyncTask;

import com.davtyan.materialweather.providers.darksky.DarkSkyWeatherProvider;

public class MainModel implements MainMvp.Model {
    private final String location;
    private final DarkSkyWeatherProvider darkSkyWeatherProvider;

    private Address address;
    private String weatherDataJsonString;

    private class TodayWeatherTask extends AsyncTask<Void, Void, TodayForecast> {

        private final Callback callback;

        public TodayWeatherTask(Callback callback) {
            this.callback = callback;
        }

        @Override
        protected TodayForecast doInBackground(Void... params) {
            return new TodayForecast(
                    darkSkyWeatherProvider.getForecastForToday(location),
                    darkSkyWeatherProvider.getFullLocation(location));
        }

        @Override
        protected void onPostExecute(TodayForecast todayForecast) {
            callback.onWeatherLoaded(todayForecast);
        }
    }

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
            new TodayWeatherTask(callback).execute();
        }
    }

    public interface Callback {
        void onWeatherLoaded(TodayForecast weatherData);
    }
}
