package com.davtyan.materialweather.lib_test.utils;

import com.davtyan.materialweather.main.TodayForecast;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestResources {

    public static final String testJson;
    public static final String testDailyJson;
    public static final TodayForecast forecast;

    static {
        testJson = getResource("test-data.json");
        testDailyJson = getResource("test-daily.json");

        forecast = new TodayForecast();
        forecast.setWindSpeed(6.79);
        forecast.setDate(1486754618000l);
        forecast.setCurrentTemp(57.36);
        forecast.setLowTemp(52.84);
        forecast.setHighTemp(59.35);
        forecast.setPrecipitationChance(0);
        forecast.setDescription("Light rain in the morning.");
        forecast.setCondition("Overcast");
        forecast.setIcon("cloudy");
        forecast.setLocation("Country, City");
        forecast.setDailySummary("Light rain throughout the week, with temperatures peaking at 66°F on Tuesday.");

        List dailyForecasts = mock(List.class);
        when(dailyForecasts.size()).thenReturn(8);
        forecast.setDailyForecasts(dailyForecasts);
    }

    private static String getResource(String name) {
        InputStream stream = TestResources.class.getClassLoader().getResourceAsStream(name);
        Scanner s = new Scanner(stream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}
