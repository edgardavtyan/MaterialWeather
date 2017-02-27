package com.davtyan.materialweather.main;

import com.davtyan.materialweather.api.WeatherApi;
import com.davtyan.materialweather.main.daily.DailyForecastAdapter;
import com.davtyan.materialweather.utils.WebClient;

public class MainFactory {
    private final MainMvp.View view;
    private final String location;
    private WebClient webClient;
    private MainMvp.Model model;
    private MainMvp.Presenter presenter;
    private WeatherApi weatherApi;
    private DailyForecastAdapter dailyForecastAdapter;

    public MainFactory(MainMvp.View view, String location) {
        this.view = view;
        this.location = location;
    }

    public MainMvp.Model getModel() {
        if (model == null)
            model = new MainModel(getWeatherApi(), location);
        return model;
    }

    public MainMvp.Presenter getPresenter() {
        if (presenter == null)
            presenter = new MainPresenter(view, getModel());
        return presenter;
    }

    public WebClient getWebClient() {
        if (webClient == null)
            webClient = new WebClient();
        return webClient;
    }

    public WeatherApi getWeatherApi() {
        if (weatherApi == null)
            weatherApi = new WeatherApi(getWebClient());
        return weatherApi;
    }

    public DailyForecastAdapter getDailyForecastAdapter() {
        if (dailyForecastAdapter == null)
            dailyForecastAdapter = new DailyForecastAdapter(getPresenter());
        return dailyForecastAdapter;
    }
}
