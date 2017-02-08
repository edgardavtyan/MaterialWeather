package com.davtyan.materialweather.weather_providers.darksky;

import com.davtyan.materialweather.components.today_weather.TodayWeatherData;
import com.davtyan.materialweather.utils.Geocoding;
import com.davtyan.materialweather.utils.WebClient;

public class DarkSkyWeatherProvider {
    private final WebClient webClient;
    private final Geocoding geocoding;
    private final String apiKey;

    public DarkSkyWeatherProvider(WebClient webClient, Geocoding geocoding, String apiKey) {
        this.webClient = webClient;
        this.geocoding = geocoding;
        this.apiKey = apiKey;
    }

    public String getForecastForToday(String location) {
        Geocoding.Coordinates coordinates = geocoding.getCoordinatesFromLocation(location);
        return webClient.getString(getUrl(coordinates.getLatitude(), coordinates.getLongitude()));
    }

    private String getUrl(double latitude, double longitude) {
        return String.format(
                "https://api.darksky.net/forecast/%s/%f,%f?units=si&exclude=minutely",
                apiKey, latitude, longitude);
    }
}
