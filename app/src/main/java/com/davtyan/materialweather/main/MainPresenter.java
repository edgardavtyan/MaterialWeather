package com.davtyan.materialweather.main;

import com.davtyan.materialweather.main.daily.DailyForecast;
import com.davtyan.materialweather.main.daily.DailyForecastViewHolder;
import com.davtyan.materialweather.views.TodayWeatherCard;

public class MainPresenter implements MainMvp.Presenter {
    private final MainMvp.View view;
    private final MainMvp.Model model;

    private TodayForecast forecast;

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

    @Override
    public void onBindDailyHolder(DailyForecastViewHolder holder, int position) {
        DailyForecast dailyForecast = forecast.getDailyForecasts().get(position);
        holder.setDate(dailyForecast.getDate());
        holder.setPrecipitationChance(dailyForecast.getPrecipChance());
        holder.setTemps(dailyForecast.getMinTemp(), dailyForecast.getMaxTemp());
        holder.setWindSpeed(dailyForecast.getWindSpeed());
        holder.setConditionIcon(dailyForecast.getIcon());
    }

    @Override
    public int getDailyItemsCount() {
        if (forecast == null) return 0;
        return forecast.getDailyForecasts().size();
    }

    private void updateView(TodayForecast forecast) {
        this.forecast = forecast;

        view.updateLists();

        view.setCurrentTemp(forecast.getCurrentTemp());
        view.setCurrentCondition(forecast.getCondition(), forecast.getIcon());
        view.setLocation(forecast.getLocation());
        view.setDailySummary(forecast.getDailySummary());

        TodayWeatherCard todayWeatherView = view.getTodayWeatherView();
        todayWeatherView.setWindSpeed(forecast.getWindSpeed());
        todayWeatherView.setDate(forecast.getDate());
        todayWeatherView.setTemps(forecast.getLowTemp(), forecast.getHighTemp());
        todayWeatherView.setPrecipitationChance(forecast.getPrecipitationChance());
        todayWeatherView.setDescription(forecast.getDescription());
        todayWeatherView.setIcon(forecast.getIcon());
    }
}
