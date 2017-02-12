package com.davtyan.materialweather.main.daily;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;

@Getter
public class DailyForecast {
    private final String icon;
    private final long date;
    private final double precipChance;
    private final double minTemp;
    private final double maxTemp;
    private final double humidity;
    private final double windSpeed;

    public DailyForecast(String jsonString) {
        try {
            JSONObject json = new JSONObject(jsonString);
            date = json.getLong("time");
            icon = json.getString("icon");
            precipChance = json.getDouble("precipProbability");
            minTemp = json.getDouble("temperatureMin");
            maxTemp = json.getDouble("temperatureMax");
            humidity = json.getDouble("humidity");
            windSpeed = json.getDouble("windSpeed");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
