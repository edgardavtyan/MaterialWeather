package com.davtyan.materialweather.components.today_weather;

public interface TodayWeatherMvp {
    interface Model {
        void getTodayWeather(TodayWeatherModel.Callback callback);
    }

    interface View {
        void setDate(long timeMS);
        void setTemps(double lowTemp, double highTemp);
        void setWindSpeed(double windSpeed);
        void setPrecipitationChance(int chance);
        void setDescription(String description);
    }

    interface Presenter {
        void onCreate();
    }
}
