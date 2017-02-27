package com.davtyan.materialweather.main;

import android.content.Context;

import com.davtyan.materialweather.api.WeatherApi;
import com.davtyan.materialweather.api.WeatherCache;
import com.davtyan.materialweather.main.daily.DailyForecastAdapter;
import com.davtyan.materialweather.utils.NetworkInfo;
import com.davtyan.materialweather.utils.WebClient;
import com.google.gson.Gson;

public class MainFactory {
    private final Context context;
    private final MainMvp.View view;
    private final String location;
    private WebClient webClient;
    private MainMvp.Model model;
    private MainMvp.Presenter presenter;
    private WeatherApi weatherApi;
    private DailyForecastAdapter dailyForecastAdapter;
    private Gson gson;
    private WeatherCache weatherCache;
    private NetworkInfo networkInfo;

    public MainFactory(Context context, MainMvp.View view, String location) {
        this.context = context;
        this.view = view;
        this.location = location;
    }

    public MainMvp.Model getModel() {
        if (model == null)
            model = new MainModel(getWeatherApi(), getNetworkInfo(), location);
        return model;
    }

    public NetworkInfo getNetworkInfo() {
        if (networkInfo == null)
            networkInfo = new NetworkInfo(context);
        return networkInfo;
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
            weatherApi = new WeatherApi(getWebClient(), getWeatherCache());
        return weatherApi;
    }

    public WeatherCache getWeatherCache() {
        if (weatherCache == null)
            weatherCache = new WeatherCache(context, getGson());
        return weatherCache;
    }

    public Gson getGson() {
        if (gson == null)
            gson = new Gson();
        return gson;
    }

    public DailyForecastAdapter getDailyForecastAdapter() {
        if (dailyForecastAdapter == null)
            dailyForecastAdapter = new DailyForecastAdapter(getPresenter());
        return dailyForecastAdapter;
    }
}
