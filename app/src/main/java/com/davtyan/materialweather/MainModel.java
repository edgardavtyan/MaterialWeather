package com.davtyan.materialweather;

import android.location.Address;
import android.os.AsyncTask;

import com.davtyan.materialweather.components.today_weather.TodayWeatherData;
import com.davtyan.materialweather.weather_providers.darksky.DarkSkyWeatherProvider;

public class MainModel implements MainMvp.Model {
    private final String location;
    private final DarkSkyWeatherProvider darkSkyWeatherProvider;

    private Address address;
    private String weatherDataJsonString;

    private class TodayWeatherTask extends AsyncTask<Void, Void, TodayWeatherData> {

        private final Callback callback;

        public TodayWeatherTask(Callback callback) {
            this.callback = callback;
        }

        @Override
        protected TodayWeatherData doInBackground(Void... params) {
            return new TodayWeatherData(
                    darkSkyWeatherProvider.getForecastForToday(location),
                    darkSkyWeatherProvider.getFullLocation(location));
        }

        @Override
        protected void onPostExecute(TodayWeatherData todayWeatherData) {
            callback.onWeatherLoaded(todayWeatherData);
        }
    }

    public MainModel(DarkSkyWeatherProvider darkSkyWeatherProvider, String location) {
        this.darkSkyWeatherProvider = darkSkyWeatherProvider;
        this.location = location;
    }

    @Override
    public void getTodayWeather(Callback callback) {
        new TodayWeatherTask(callback).execute();
    }

    public interface Callback {
        void onWeatherLoaded(TodayWeatherData weatherData);
    }
}
