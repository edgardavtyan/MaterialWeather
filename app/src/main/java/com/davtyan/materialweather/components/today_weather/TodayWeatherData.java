package com.davtyan.materialweather.components.today_weather;

import org.json.JSONException;
import org.json.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TodayWeatherData {
    private double windSpeed;
    private long date;
    private double currentTemp;
    private double lowTemp;
    private double highTemp;
    private int precipitationChance;
    private String description;
    private String condition;

    public TodayWeatherData(String jsonString) {
        try {
            JSONObject root = new JSONObject(jsonString);
            JSONObject currentWeather = root.getJSONObject("currently");
            date = currentWeather.getLong("time");
            windSpeed = currentWeather.getDouble("windSpeed");
            precipitationChance = (int) (currentWeather.getDouble("precipProbability") * 100);
            currentTemp = currentWeather.getDouble("temperature");
            condition = currentWeather.getString("summary");

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
