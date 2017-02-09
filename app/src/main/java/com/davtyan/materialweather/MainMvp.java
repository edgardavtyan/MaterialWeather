package com.davtyan.materialweather;

import com.davtyan.materialweather.views.TodayWeatherCard;

public interface MainMvp {
    interface Model {
        void getTodayWeather(MainModel.Callback callback);
    }

    interface View {
        TodayWeatherCard getTodayWeatherView();
    }

    interface Presenter {
        void onCreate();
    }
}
