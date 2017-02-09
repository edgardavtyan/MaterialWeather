package com.davtyan.materialweather.components.today_weather;

public class TodayWeatherPresenter implements TodayWeatherMvp.Presenter {
    private final TodayWeatherMvp.View view;
    private final TodayWeatherMvp.Model model;

    public TodayWeatherPresenter(TodayWeatherMvp.View view, TodayWeatherMvp.Model model) {
        this.view = view;
        this.model = model;
    }

    @Override
    public void onCreate() {
        model.getTodayWeather(todayWeatherData -> {
            view.setWindSpeed(todayWeatherData.getWindSpeed());
            view.setDate(todayWeatherData.getDate());
            view.setTemps(todayWeatherData.getLowTemp(), todayWeatherData.getHighTemp());
            view.setPrecipitationChance(todayWeatherData.getPrecipitationChance());
            view.setDescription(todayWeatherData.getDescription());
            view.setCurrentTemp(todayWeatherData.getCurrentTemp());
        });
    }
}
