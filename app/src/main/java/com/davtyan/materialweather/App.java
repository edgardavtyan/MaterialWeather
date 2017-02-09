package com.davtyan.materialweather;

import android.app.Application;
import android.content.Context;

public class App extends Application {
    private MainFactory mainFactory;

    public MainFactory getTodayWeatherFactory(
            Context context,
            MainMvp.View view,
            String location) {
        if (mainFactory == null)
            return new MainFactory(context, view, location);
        return mainFactory;
    }
}
