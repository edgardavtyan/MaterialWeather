package com.davtyan.materialweather.main;

import com.davtyan.materialweather.api.Forecast;
import com.davtyan.materialweather.lib_test.BaseTest;
import com.davtyan.materialweather.main.daily.DailyForecast;
import com.davtyan.materialweather.main.daily.DailyForecastViewHolder;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainPresenterTest extends BaseTest {
    private MainMvp.View view;
    private MainMvp.Model model;
    private MainPresenter presenter;

    @Override
    public void beforeEach() {
        super.beforeEach();
        view = mock(MainMvp.View.class);
        model = mock(MainMvp.Model.class);
        presenter = new MainPresenter(view, model);
    }

    @Test
    public void onCreate_updateModel() {
        presenter.onCreate();
        verify(model).getTodayWeather();
    }

    @Test
    public void onRefresh_updateWeather() {
        presenter.onRefresh();
        verify(model).forceRefresh();
    }

    @Test
    public void getDailyItemsCount_weatherNotUpdated_returnZero() {
        assertThat(presenter.getDailyItemsCount()).isZero();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void getDailyItemsCount_weatherUpdated_returnDailyForecastsCount() {
        List dailyForecasts = mock(List.class);
        when(dailyForecasts.size()).thenReturn(3);

        Forecast forecast = mock(Forecast.class);
        when(forecast.getDaily()).thenReturn(dailyForecasts);

        presenter.onWeatherLoaded(forecast);

        assertThat(presenter.getDailyItemsCount()).isEqualTo(3);
    }

    @Test
    @SuppressWarnings("unchecked")
    public void onBindDailyHolder_initHolderView() {
        DailyForecast dailyForecast = mock(DailyForecast.class);
        when(dailyForecast.getDate()).thenReturn(1l);
        when(dailyForecast.getHumidity()).thenReturn(2.3);
        when(dailyForecast.getIcon()).thenReturn("icon");
        when(dailyForecast.getMaxTemp()).thenReturn(3.4);
        when(dailyForecast.getMinTemp()).thenReturn(4.5);
        when(dailyForecast.getPrecipChance()).thenReturn(5.6);
        when(dailyForecast.getWindSpeed()).thenReturn(6.7);

        List dailyForecasts = mock(List.class);
        when(dailyForecasts.get(0)).thenReturn(dailyForecast);

        Forecast forecast = mock(Forecast.class);
        when(forecast.getDaily()).thenReturn(dailyForecasts);

        DailyForecastViewHolder holder = mock(DailyForecastViewHolder.class);

        presenter.onWeatherLoaded(forecast);
        presenter.onBindDailyHolder(holder, 0);

        verify(holder).setDate(1l);
        verify(holder).setTemps(4.5, 3.4);
        verify(holder).setPrecipChance(5.6);
        verify(holder).setWindSpeed(6.7);
        verify(holder).setConditionIcon("icon");
    }

    @Test
    public void onWeatherLoaded_updateView() {
        Forecast forecast = mock(Forecast.class);
        when(forecast.getWindSpeed()).thenReturn(1.1);
        when(forecast.getDate()).thenReturn(2l);
        when(forecast.getTemperature()).thenReturn(3.3);
        when(forecast.getTemperatureMin()).thenReturn(4.4);
        when(forecast.getTemperatureMax()).thenReturn(5.5);
        when(forecast.getPrecipChance()).thenReturn(6.6);
        when(forecast.getCondition()).thenReturn("condition");
        when(forecast.getTodaySummary()).thenReturn("description");
        when(forecast.getIcon()).thenReturn("icon");
        when(forecast.getLocation()).thenReturn("location");

        presenter.onWeatherLoaded(forecast);

        verify(view, timeout(1000)).setCurrentTemp(3.3);
        verify(view, timeout(1000)).setCurrentCondition("condition", "icon");
        verify(view, timeout(1000)).setLocation("location");
        verify(view, timeout(1000)).setCurrentWindSpeed(1.1);
        verify(view, timeout(1000)).setCurrentDate(2l);
        verify(view, timeout(1000)).setCurrentLowTemp(4.4);
        verify(view, timeout(1000)).setCurrentHighTemp(5.5);
        verify(view, timeout(1000)).setCurrentPrecipChance(6);
        verify(view, timeout(1000)).setCurrentSummary("description");
    }
}
