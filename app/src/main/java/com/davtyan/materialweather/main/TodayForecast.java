package com.davtyan.materialweather.main;

import android.location.Address;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodayForecast {
    private double windSpeed;
    private long date;
    private double currentTemp;
    private double lowTemp;
    private double highTemp;
    private int precipitationChance;
    private String description;
    private String condition;
    private String icon;
    private String location;

    public TodayForecast(String jsonString, Address address) {
        try {
            JSONObject root = new JSONObject(jsonString);
            JSONObject currentWeather = root.getJSONObject("currently");
            date = currentWeather.getLong("time");
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
