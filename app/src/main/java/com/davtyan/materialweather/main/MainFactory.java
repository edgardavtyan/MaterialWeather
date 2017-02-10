package com.davtyan.materialweather.main;

import android.content.Context;

import com.davtyan.materialweather.R;
import com.davtyan.materialweather.providers.darksky.DarkSkyForecastCache;
import com.davtyan.materialweather.utils.Geocoding;
import com.davtyan.materialweather.utils.WebClient;
import com.davtyan.materialweather.providers.darksky.DarkSkyWeatherProvider;

public class MainFactory {
    private final Context context;
    private final MainMvp.View view;
    private final String location;
    private final String apiKey;
    private WebClient webClient;
    private Geocoding geocoding;
    private DarkSkyForecastCache cache;
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

    public DarkSkyForecastCache getCache() {
        if (cache == null)
            cache = new DarkSkyForecastCache(context);
        return cache;
    }

    public DarkSkyWeatherProvider getDarkSkyWeatherProvider() {
        if (darkSkyWeatherProvider == null)
            darkSkyWeatherProvider = new DarkSkyWeatherProvider(
                    getWebClient(),
                    getGeocoding(),
                    getCache(),
                    apiKey);
        return darkSkyWeatherProvider;
    }
}
