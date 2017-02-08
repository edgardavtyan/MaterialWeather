package com.davtyan.materialweather.components.today_weather;

public class TodayWeatherUrl {
    private final String apiKey;

    public TodayWeatherUrl(String apiKey) {
        this.apiKey = apiKey;
    }

    public String build(double latitude, double longitude) {
        return String.format(
                "https://api.darksky.net/forecast/%s/%f,%f?units=si&exclude=minutely",
                apiKey, latitude, longitude);
    }
}
