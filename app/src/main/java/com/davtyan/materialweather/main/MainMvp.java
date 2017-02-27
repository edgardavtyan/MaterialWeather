package com.davtyan.materialweather.main;

import com.davtyan.materialweather.api.Forecast;
import com.davtyan.materialweather.main.daily.DailyForecastViewHolder;

public interface MainMvp {
    interface Model {
        interface OnWeatherLoadedListener {
            void onWeatherLoaded(Forecast forecast);
        }

        void setOnWeatherLoadedListener(OnWeatherLoadedListener listener);
        void getTodayWeather();
        void forceRefresh();
    }

    interface View {
        void setCurrentTemp(double temp);
        void setCurrentCondition(String condition, String icon);
        void setLocation(String location);
        void setDailySummary(String summary);
        void updateLists();
        void setCurrentWindSpeed(double windSpeed);
        void setCurrentDate(long date);
        void setCurrentLowTemp(double lowTemp);
        void setCurrentHighTemp(double highTemp);
        void setCurrentPrecipitationChance(double precipitationChance);
        void setCurrentSummary(String summary);
        void setCurrentIcon(String icon);
    }

    interface Presenter {
        void onCreate();
        void onRefresh();
        void onBindDailyHolder(DailyForecastViewHolder holder, int position);
        int getDailyItemsCount();
    }
}
