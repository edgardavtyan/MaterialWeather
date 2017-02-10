package com.davtyan.materialweather;

import android.app.Application;
import android.content.Context;

import com.davtyan.materialweather.main.MainFactory;
import com.davtyan.materialweather.main.MainMvp;

import lombok.Setter;

public class App extends Application {
    private @Setter MainFactory mainFactory;

    public MainFactory getTodayWeatherFactory(
            Context context,
            MainMvp.View view,
            String location) {
        if (mainFactory == null)
            return new MainFactory(context, view, location);
        return mainFactory;
    }
}
