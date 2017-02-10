package com.davtyan.materialweather.main;

import com.davtyan.materialweather.lib_test.BaseTest;
import com.davtyan.materialweather.views.TodayWeatherCard;

import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainPresenterTest extends BaseTest {
    private TodayWeatherCard todayWeatherCard;
    private MainMvp.View view;
    private MainMvp.Model model;
    private MainMvp.Presenter presenter;

    @Override
    public void beforeEach() {
        super.beforeEach();
        todayWeatherCard = mock(TodayWeatherCard.class);
        view = mock(MainMvp.View.class);
        when(view.getTodayWeatherView()).thenReturn(todayWeatherCard);
        model = mock(MainMvp.Model.class);
        presenter = new MainPresenter(view, model);
    }

    @Test
    public void onCreate_setViewValuesFromForecast() {
        TodayForecast forecast = mock(TodayForecast.class);
        when(forecast.getWindSpeed()).thenReturn(1.1);
        when(forecast.getDate()).thenReturn(2l);
        when(forecast.getCurrentTemp()).thenReturn(3.3);
        when(forecast.getLowTemp()).thenReturn(4.4);
        when(forecast.getHighTemp()).thenReturn(5.5);
        when(forecast.getPrecipitationChance()).thenReturn(6);
        when(forecast.getCondition()).thenReturn("condition");
        when(forecast.getDescription()).thenReturn("description");
        when(forecast.getIcon()).thenReturn("icon");
        when(forecast.getLocation()).thenReturn("location");

        doAnswer(invocation -> {
            TodayWeatherTask.Callback callback = (TodayWeatherTask.Callback) invocation.getArguments()[0];
            callback.onWeatherLoaded(forecast);
            return null;
        }).when(model).getTodayWeather(any());

        presenter.onCreate();

        verify(view).setCurrentTemp(3.3);
        verify(view).setCurrentCondition("condition", "icon");
        verify(view).setLocation("location");
        verify(todayWeatherCard).setWindSpeed(1.1);
        verify(todayWeatherCard).setDate(2l);
        verify(todayWeatherCard).setTemps(4.4, 5.5);
        verify(todayWeatherCard).setPrecipitationChance(6);
        verify(todayWeatherCard).setDescription("description");
    }

    @Test
    public void onRefresh_updateWeather() {
        TodayForecast forecast = mock(TodayForecast.class);
        when(forecast.getWindSpeed()).thenReturn(1.1);
        when(forecast.getDate()).thenReturn(2l);
        when(forecast.getCurrentTemp()).thenReturn(3.3);
        when(forecast.getLowTemp()).thenReturn(4.4);
        when(forecast.getHighTemp()).thenReturn(5.5);
        when(forecast.getPrecipitationChance()).thenReturn(6);
        when(forecast.getCondition()).thenReturn("condition");
        when(forecast.getDescription()).thenReturn("description");
        when(forecast.getIcon()).thenReturn("icon");
        when(forecast.getLocation()).thenReturn("location");

        doAnswer(invocation -> {
            TodayWeatherTask.Callback callback = (TodayWeatherTask.Callback) invocation.getArguments()[0];
            callback.onWeatherLoaded(forecast);
            return null;
        }).when(model).forceRefresh(any());

        presenter.onRefresh();

        verify(model).forceRefresh(any());
        verify(view).setCurrentTemp(3.3);
        verify(view).setCurrentCondition("condition", "icon");
        verify(view).setLocation("location");
        verify(todayWeatherCard).setWindSpeed(1.1);
        verify(todayWeatherCard).setDate(2l);
        verify(todayWeatherCard).setTemps(4.4, 5.5);
        verify(todayWeatherCard).setPrecipitationChance(6);
        verify(todayWeatherCard).setDescription("description");
    }
}
