package com.davtyan.materialweather.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ForecastDaily {
    long date;
    String icon;
    long sunriseTime;
    long sunsetTime;
    double precipIntensity;
    double precipProbability;
    double temperatureMax;
    double temperatureMin;
    double humidity;
    double windSpeed;
    double pressure;
}
