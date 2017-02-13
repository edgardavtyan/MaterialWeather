package com.davtyan.materialweather.main;

import com.davtyan.materialweather.lib_test.BaseTest;
import com.davtyan.materialweather.providers.darksky.DarkSkyWeatherProvider;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainModelTest extends BaseTest {
    private static final String LOCATION = "Location";

    private MainMvp.Model model;
    private DarkSkyWeatherProvider weatherProvider;

    @Override
    public void beforeEach() {
        super.beforeEach();
        weatherProvider = mock(DarkSkyWeatherProvider.class);
        model = new MainModel(weatherProvider, LOCATION);
    }

    @Test
    public void getTodayWeather_getForecastFromCache() {
        TodayForecast forecast = mock(TodayForecast.class);
        when(weatherProvider.getForecastFromCache(LOCATION)).thenReturn(forecast);
        when(weatherProvider.isNonOutdatedCachedForecastAvailable()).thenReturn(true);

        MainMvp.Model.OnWeatherLoadedListener listener = mock(MainMvp.Model.OnWeatherLoadedListener.class);
        model.setOnWeatherLoadedListener(listener);
        model.getTodayWeather();
        verify(listener, timeout(1000)).onWeatherLoaded(forecast);
    }

    @Test
    public void getTodayWeather_getForecastFromProvider() {
        TodayForecast forecast = createMocKForecastWithCacheEnabled(false);
        MainMvp.Model.OnWeatherLoadedListener listener = mock(MainMvp.Model.OnWeatherLoadedListener.class);
        model.setOnWeatherLoadedListener(listener);
        model.getTodayWeather();
        verify(listener, timeout(1000)).onWeatherLoaded(forecast);
    }

    @Test
    public void forceRefresh_getForecastFromProvider() {
        TodayForecast forecast = createMocKForecastWithCacheEnabled(false);
        MainMvp.Model.OnWeatherLoadedListener listener = mock(MainMvp.Model.OnWeatherLoadedListener.class);
        model.setOnWeatherLoadedListener(listener);
        model.forceRefresh();
        verify(listener, timeout(1000)).onWeatherLoaded(forecast);
    }

    private TodayForecast createMocKForecastWithCacheEnabled(boolean enabled) {
        TodayForecast forecast = mock(TodayForecast.class);
        when(weatherProvider.getForecastForToday(LOCATION)).thenReturn(forecast);
        when(weatherProvider.isNonOutdatedCachedForecastAvailable()).thenReturn(enabled);
        return forecast;
    }
}
