package com.davtyan.materialweather.api;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Forecast {
    private long date;
    private String location;
    private String condition;
    private String icon;
    private String dailySummary;
    private String todaySummary;
    private double precipChance;
    private double temperature;
    private double temperatureMax;
    private double temperatureMin;
    private double feelsLikeTemperature;
    private double humidity;
    private double windSpeed;
    private List<ForecastDaily> daily;
}
