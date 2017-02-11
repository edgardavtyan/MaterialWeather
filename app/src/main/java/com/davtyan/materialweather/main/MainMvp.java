package com.davtyan.materialweather.main;

import com.davtyan.materialweather.main.daily.DailyForecastViewHolder;
import com.davtyan.materialweather.views.TodayWeatherCard;

public interface MainMvp {
    interface Model {
        void getTodayWeather(TodayWeatherTask.Callback callback);
        void forceRefresh(TodayWeatherTask.Callback callback);
    }

    interface View {
        TodayWeatherCard getTodayWeatherView();
        void setCurrentTemp(double temp);
        void setCurrentCondition(String condition, String icon);
        void setLocation(String location);
        void setDailySummary(String summary);
        void updateLists();
    }

    interface Presenter {
        void onCreate();
        void onRefresh();
        void onBindDailyHolder(DailyForecastViewHolder holder, int position);
        int getDailyItemsCount();
    }
}
