package com.davtyan.materialweather.main;

import com.davtyan.materialweather.api.Forecast;
import com.davtyan.materialweather.api.ForecastDaily;
import com.davtyan.materialweather.main.daily.DailyForecastViewHolder;

public class MainPresenter implements MainMvp.Presenter, MainMvp.Model.OnWeatherLoadedListener {
    private final MainMvp.View view;
    private final MainMvp.Model model;

    private Forecast forecast;

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
        ForecastDaily dailyForecast = forecast.getDaily().get(position);
        holder.setDate(dailyForecast.getDate());
        holder.setPrecipChance(dailyForecast.getPrecipProbability());
        holder.setTemps(dailyForecast.getTemperatureMin(), dailyForecast.getTemperatureMax());
        holder.setWindSpeed(dailyForecast.getWindSpeed());
        holder.setConditionIcon(dailyForecast.getIcon());
    }

    @Override
    public int getDailyItemsCount() {
        if (forecast == null) return 0;
        return forecast.getDaily().size();
    }

    @Override
    public void onWeatherLoaded(Forecast forecast) {
        updateView(forecast);
    }

    private void updateView(Forecast forecast) {
        this.forecast = forecast;

        view.updateLists();
        view.setCurrentTemp(forecast.getTemperature());
        view.setCurrentCondition(forecast.getCondition(), forecast.getIcon());
        view.setLocation(forecast.getLocation());
        view.setDailySummary(forecast.getDailySummary());
        view.setCurrentWindSpeed(forecast.getWindSpeed());
        view.setCurrentDate(forecast.getDate());
        view.setCurrentLowTemp(forecast.getTemperatureMin());
        view.setCurrentHighTemp(forecast.getTemperatureMax());
        view.setCurrentPrecipChance(forecast.getPrecipChance());
        view.setCurrentSummary(forecast.getTodaySummary());
        view.setCurrentIcon(forecast.getIcon());
    }
}
