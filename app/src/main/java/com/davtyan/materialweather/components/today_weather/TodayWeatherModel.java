package com.davtyan.materialweather.components.today_weather;

import android.content.Context;
import android.os.AsyncTask;

import com.davtyan.materialweather.R;
import com.davtyan.materialweather.utils.Geocoding;
import com.davtyan.materialweather.utils.WebClient;

public class TodayWeatherModel implements TodayWeatherMvp.Model {
    private final WebClient webClient;
    private final TodayWeatherUrl url;
    private final Geocoding geocoding;
    private final String location;

    private Geocoding.Coordinates coordinates;
    private String weatherDataJsonString;

    private class TodayWeatherTask extends AsyncTask<Void, Void, TodayWeatherData> {

        private final Callback callback;

        public TodayWeatherTask(Callback callback) {
            this.callback = callback;
        }

        @Override
        protected TodayWeatherData doInBackground(Void... params) {
            coordinates = geocoding.getCoordinatesFromLocation(location);
            String urlString = url.build(coordinates.getLatitude(), coordinates.getLongitude());
            weatherDataJsonString = webClient.getString(urlString);
            return new TodayWeatherData(weatherDataJsonString);
        }

        @Override
        protected void onPostExecute(TodayWeatherData todayWeatherData) {
            callback.onWeatherLoaded(todayWeatherData);
        }
    }

    public TodayWeatherModel(
            Context context,
            WebClient webClient,
            Geocoding geocoding,
            String location) {
        this.webClient = webClient;
        this.geocoding = geocoding;
        this.location = location;
        this.url = new TodayWeatherUrl(context.getString(R.string.apikey_darksky));
    }

    @Override
    public void getTodayWeather(Callback callback) {
        new TodayWeatherTask(callback).execute();
    }

    public interface Callback {
        void onWeatherLoaded(TodayWeatherData weatherData);
    }
}
