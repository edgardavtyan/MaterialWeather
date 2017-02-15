package com.davtyan.materialweather.main;

import com.davtyan.materialweather.main.daily.DailyForecast;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class TodayForecast {
    private double windSpeed;
    private long date;
    private double currentTemp;
    private double lowTemp;
    private double highTemp;
    private int precipitationChance;
    private String description;
    private String dailySummary;
    private String condition;
    private String icon;
    private String location;

    private List<DailyForecast> dailyForecasts;
}
