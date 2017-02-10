package com.davtyan.materialweather.main;

import android.location.Address;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;

@Getter
public class TodayForecast {
    private final double windSpeed;
    private final long date;
    private final double currentTemp;
    private final double lowTemp;
    private final double highTemp;
    private final int precipitationChance;
    private final String description;
    private final String condition;
    private final String icon;
    private final String location;

    public TodayForecast(String jsonString, Address address) {
        try {
            JSONObject root = new JSONObject(jsonString);
            JSONObject currentWeather = root.getJSONObject("currently");
            date = currentWeather.getLong("time") * 1000;
            windSpeed = currentWeather.getDouble("windSpeed");
            precipitationChance = (int) (currentWeather.getDouble("precipProbability") * 100);
            currentTemp = currentWeather.getDouble("temperature");
            condition = currentWeather.getString("summary");
            icon = currentWeather.getString("icon");
            location = String.format("%s, %s", address.getCountryName(), address.getAdminArea());

            JSONObject dailyFirstDay = root
                    .getJSONObject("daily")
                    .getJSONArray("data")
                    .getJSONObject(0);
            description = dailyFirstDay.getString("summary");
            lowTemp = dailyFirstDay.getDouble("temperatureMin");
            highTemp = dailyFirstDay.getDouble("temperatureMax");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
