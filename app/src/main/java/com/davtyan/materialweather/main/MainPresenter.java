package com.davtyan.materialweather.main;

import com.davtyan.materialweather.views.TodayWeatherCard;

public class MainPresenter implements MainMvp.Presenter {
    private final MainMvp.View view;
    private final MainMvp.Model model;

    public MainPresenter(MainMvp.View view, MainMvp.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onCreate() {
        model.getTodayWeather(todayWeatherData -> {
            view.setCurrentTemp(todayWeatherData.getCurrentTemp());
            view.setCurrentCondition(todayWeatherData.getCondition(), todayWeatherData.getIcon());
            view.setLocation(todayWeatherData.getLocation());

            TodayWeatherCard todayWeatherView = view.getTodayWeatherView();
            todayWeatherView.setWindSpeed(todayWeatherData.getWindSpeed());
            todayWeatherView.setDate(todayWeatherData.getDate());
            todayWeatherView.setTemps(todayWeatherData.getLowTemp(), todayWeatherData.getHighTemp());
            todayWeatherView.setPrecipitationChance(todayWeatherData.getPrecipitationChance());
            todayWeatherView.setDescription(todayWeatherData.getDescription());
        });
    }
}
