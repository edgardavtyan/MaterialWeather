package com.davtyan.materialweather.components.today_weather;

import android.content.Context;

import com.davtyan.materialweather.R;
import com.davtyan.materialweather.utils.Geocoding;
import com.davtyan.materialweather.utils.WebClient;
import com.davtyan.materialweather.weather_providers.darksky.DarkSkyWeatherProvider;

public class TodayWeatherFactory {
    private final Context context;
    private final TodayWeatherMvp.View view;
    private final String location;
    private final String apiKey;
    private WebClient webClient;
    private Geocoding geocoding;
    private TodayWeatherMvp.Model model;
    private TodayWeatherMvp.Presenter presenter;
    private DarkSkyWeatherProvider darkSkyWeatherProvider;

    public TodayWeatherFactory(Context context, TodayWeatherMvp.View view, String location) {
        this.context = context;
        this.view = view;
        this.location = location;
        this.apiKey = context.getString(R.string.apikey_darksky);
    }

    public TodayWeatherMvp.Model getModel() {
        if (model == null)
            model = new TodayWeatherModel(getDarkSkyWeatherProvider(), location);
        return model;
    }

    public TodayWeatherMvp.Presenter getPresenter() {
        if (presenter == null)
            presenter = new TodayWeatherPresenter(view, getModel());
        return presenter;
    }

    public WebClient getWebClient() {
        if (webClient == null)
            webClient = new WebClient();
        return webClient;
    }

    public Geocoding getGeocoding() {
        if (geocoding == null)
            geocoding = new Geocoding(context);
        return geocoding;
    }

    public DarkSkyWeatherProvider getDarkSkyWeatherProvider() {
        if (darkSkyWeatherProvider == null)
            darkSkyWeatherProvider = new DarkSkyWeatherProvider(getWebClient(), getGeocoding(), apiKey);
        return darkSkyWeatherProvider;
    }
}
