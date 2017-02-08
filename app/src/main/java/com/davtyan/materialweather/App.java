package com.davtyan.materialweather;

import android.app.Application;
import android.content.Context;

import com.davtyan.materialweather.components.today_weather.TodayWeatherFactory;
import com.davtyan.materialweather.components.today_weather.TodayWeatherMvp;

public class App extends Application {
    private TodayWeatherFactory todayWeatherFactory;

    public TodayWeatherFactory getTodayWeatherFactory(
            Context context,
            TodayWeatherMvp.View view,
            String location) {
        if (todayWeatherFactory == null)
            return new TodayWeatherFactory(context, view, location);
        return todayWeatherFactory;
    }
}
