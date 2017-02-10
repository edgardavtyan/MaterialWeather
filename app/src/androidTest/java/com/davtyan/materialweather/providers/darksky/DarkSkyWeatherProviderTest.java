package com.davtyan.materialweather.providers.darksky;

import android.location.Address;

import com.davtyan.materialweather.lib_test.BaseTest;
import com.davtyan.materialweather.lib_test.utils.TestResources;
import com.davtyan.materialweather.main.TodayForecast;
import com.davtyan.materialweather.utils.Geocoding;
import com.davtyan.materialweather.utils.WebClient;

import org.junit.Test;

import java.util.Date;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DarkSkyWeatherProviderTest extends BaseTest {
    private DarkSkyWeatherProvider weatherProvider;
    private WebClient webClient;
    private Geocoding geocoding;
    private DarkSkyForecastCache cache;

    @Override
    public void beforeEach() {
        super.beforeEach();

        webClient = mock(WebClient.class);
        geocoding = mock(Geocoding.class);
        cache = mock(DarkSkyForecastCache.class);
        weatherProvider = new DarkSkyWeatherProvider(webClient, geocoding, cache, "123");

        Address address = new Address(Locale.getDefault());
        address.setCountryName("Country");
        address.setAdminArea("City");
        address.setLatitude(1);
        address.setLongitude(2);
        when(geocoding.getAddressFromLocation("location")).thenReturn(address);
    }

    @Test
    public void getForecastForToday_refreshTimeoutReached_getForecastFromApi() {
        when(cache.getCachedFileDate()).thenReturn(0l);
        when(webClient.getString(any())).thenReturn(TestResources.testJson);
        TodayForecast forecast = weatherProvider.getForecastForToday("location");
        assertThatForecastIsEqualToTestData(forecast);
    }

    @Test
    public void getForecastForToday_refreshTimeoutNotReached_getForecastFromCache() {
        when(cache.getCachedFileDate()).thenReturn(new Date().getTime());
        when(cache.exists()).thenReturn(true);
        when(cache.get()).thenReturn(TestResources.testJson);
        TodayForecast forecast = weatherProvider.getForecastForToday("location");
        assertThatForecastIsEqualToTestData(forecast);
    }

    @Test
    public void getForecastFromCache_returnCachedForecast() {
        when(cache.get()).thenReturn(TestResources.testJson);
        TodayForecast forecast = weatherProvider.getForecastFromCache("location");
        assertThatForecastIsEqualToTestData(forecast);
    }

    @Test
    public void getFullLocation_returnLocationAddress() {
        Address address = new Address(Locale.getDefault());
        when(geocoding.getAddressFromLocation("location")).thenReturn(address);
        assertThat(weatherProvider.getFullLocation("location")).isSameAs(address);
    }

    @Test
    public void isNonOutdatedCachedForecastAvailable_availableAndNonOutdated_returnTrue() {
        when(cache.exists()).thenReturn(true);
        when(cache.getCachedFileDate()).thenReturn(new Date().getTime());
        assertThat(weatherProvider.isNonOutdatedCachedForecastAvailable()).isTrue();
    }

    @Test
    public void isNonOutdatedCachedForecastAvailable_availableButOutdated_returnFalse() {
        when(cache.exists()).thenReturn(true);
        when(cache.getCachedFileDate()).thenReturn(0l);
        assertThat(weatherProvider.isNonOutdatedCachedForecastAvailable()).isFalse();
    }

    @Test
    public void isNonOutdatedCachedForecastAvailable_nonOutdatedButUnavailable_returnFalse() {
        when(cache.exists()).thenReturn(false);
        when(cache.getCachedFileDate()).thenReturn(new Date().getTime());
        assertThat(weatherProvider.isNonOutdatedCachedForecastAvailable()).isFalse();
    }

    @Test
    public void isNonOutdatedCachedForecastAvailable_notAvailableAndOutdated_returnFalse() {
        when(cache.exists()).thenReturn(false);
        when(cache.getCachedFileDate()).thenReturn(0l);
        assertThat(weatherProvider.isNonOutdatedCachedForecastAvailable()).isFalse();
    }

    private void assertThatForecastIsEqualToTestData(TodayForecast forecast) {
        assertThat(forecast.getWindSpeed()).isEqualTo(6.79);
        assertThat(forecast.getDate()).isEqualTo(1486754618000l);
        assertThat(forecast.getCurrentTemp()).isEqualTo(57.36);
        assertThat(forecast.getLowTemp()).isEqualTo(52.84);
        assertThat(forecast.getHighTemp()).isEqualTo(59.35);
        assertThat(forecast.getPrecipitationChance()).isEqualTo(0);
        assertThat(forecast.getDescription()).isEqualTo("Light rain in the morning.");
        assertThat(forecast.getCondition()).isEqualTo("Overcast");
        assertThat(forecast.getIcon()).isEqualTo("cloudy");
        assertThat(forecast.getLocation()).isEqualTo("Country, City");
    }
}
