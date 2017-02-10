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
        model.getTodayWeather(this::updateView);
    }

    @Override
    public void onRefresh() {
        model.forceRefresh(this::updateView);
    }

    private void updateView(TodayForecast forecast) {
        view.setCurrentTemp(forecast.getCurrentTemp());
        view.setCurrentCondition(forecast.getCondition(), forecast.getIcon());
        view.setLocation(forecast.getLocation());

        TodayWeatherCard todayWeatherView = view.getTodayWeatherView();
        todayWeatherView.setWindSpeed(forecast.getWindSpeed());
        todayWeatherView.setDate(forecast.getDate());
        todayWeatherView.setTemps(forecast.getLowTemp(), forecast.getHighTemp());
        todayWeatherView.setPrecipitationChance(forecast.getPrecipitationChance());
        todayWeatherView.setDescription(forecast.getDescription());
    }
}
