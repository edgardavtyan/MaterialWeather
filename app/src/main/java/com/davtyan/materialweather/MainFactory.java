package com.davtyan.materialweather;

import android.content.Context;

import com.davtyan.materialweather.R;
import com.davtyan.materialweather.MainModel;
import com.davtyan.materialweather.MainMvp;
import com.davtyan.materialweather.MainPresenter;
import com.davtyan.materialweather.utils.Geocoding;
import com.davtyan.materialweather.utils.WebClient;
import com.davtyan.materialweather.weather_providers.darksky.DarkSkyWeatherProvider;

public class MainFactory {
    private final Context context;
    private final MainMvp.View view;
    private final String location;
    private final String apiKey;
    private WebClient webClient;
    private Geocoding geocoding;
    private MainMvp.Model model;
    private MainMvp.Presenter presenter;
    private DarkSkyWeatherProvider darkSkyWeatherProvider;

    public MainFactory(Context context, MainMvp.View view, String location) {
        this.context = context;
        this.view = view;
        this.location = location;
        this.apiKey = context.getString(R.string.apikey_darksky);
    }

    public MainMvp.Model getModel() {
        if (model == null)
            model = new MainModel(getDarkSkyWeatherProvider(), location);
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
