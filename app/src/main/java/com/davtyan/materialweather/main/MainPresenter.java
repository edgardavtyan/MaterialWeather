package com.davtyan.materialweather.main;

import com.davtyan.materialweather.main.daily.DailyForecast;
import com.davtyan.materialweather.main.daily.DailyForecastViewHolder;

public class MainPresenter implements MainMvp.Presenter, MainMvp.Model.OnWeatherLoadedListener {
    private final MainMvp.View view;
    private final MainMvp.Model model;

    private TodayForecast forecast;

    public MainPresenter(MainMvp.View view, MainMvp.Model model) {
        this.view = view;
        this.model = model;
        this.model.setOnWeatherLoadedListener(this);
    }

    @Override
    public void onCreate() {
        model.getTodayWeather();
    }

    @Override
    public void onRefresh() {
        model.forceRefresh();
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

    @Override
    public void onWeatherLoaded(TodayForecast forecast) {
        updateView(forecast);
    }

    private void updateView(TodayForecast forecast) {
        this.forecast = forecast;

        view.updateLists();

        view.setCurrentTemp(forecast.getCurrentTemp());
        view.setCurrentCondition(forecast.getCondition(), forecast.getIcon());
        view.setLocation(forecast.getLocation());
        view.setDailySummary(forecast.getDailySummary());

        view.setCurrentWindSpeed(forecast.getWindSpeed());
        view.setCurrentDate(forecast.getDate());
        view.setCurrentLowTemp(forecast.getLowTemp());
        view.setCurrentHighTemp(forecast.getHighTemp());
        view.setCurrentPrecipitationChance(forecast.getPrecipitationChance());
        view.setCurrentSummary(forecast.getDescription());
        view.setCurrentIcon(forecast.getIcon());
    }
}
