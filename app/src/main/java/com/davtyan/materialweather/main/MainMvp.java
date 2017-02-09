package com.davtyan.materialweather.main;

import com.davtyan.materialweather.views.TodayWeatherCard;

public interface MainMvp {
    interface Model {
        void getTodayWeather(MainModel.Callback callback);
    }

    interface View {
        TodayWeatherCard getTodayWeatherView();
        void setCurrentTemp(double temp);
        void setCurrentCondition(String condition);
        void setLocation(String location);
    }

    interface Presenter {
        void onCreate();
    }
}
