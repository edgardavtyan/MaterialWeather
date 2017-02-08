package com.davtyan.materialweather.components.today_weather;

import android.os.AsyncTask;

import com.davtyan.materialweather.utils.Geocoding;
import com.davtyan.materialweather.weather_providers.darksky.DarkSkyWeatherProvider;

public class TodayWeatherModel implements TodayWeatherMvp.Model {
    private final String location;
    private final DarkSkyWeatherProvider darkSkyWeatherProvider;

    private Geocoding.Coordinates coordinates;
    private String weatherDataJsonString;

    private class TodayWeatherTask extends AsyncTask<Void, Void, TodayWeatherData> {

        private final Callback callback;

        public TodayWeatherTask(Callback callback) {
            this.callback = callback;
        }

        @Override
        protected TodayWeatherData doInBackground(Void... params) {
            return new TodayWeatherData(darkSkyWeatherProvider.getForecastForToday(location));
        }

        @Override
        protected void onPostExecute(TodayWeatherData todayWeatherData) {
            callback.onWeatherLoaded(todayWeatherData);
        }
    }

    public TodayWeatherModel(DarkSkyWeatherProvider darkSkyWeatherProvider, String location) {
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
