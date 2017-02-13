package com.davtyan.materialweather.main;

import com.davtyan.materialweather.lib_test.BaseTest;
import com.davtyan.materialweather.lib_test.utils.TestResources;
import com.davtyan.materialweather.providers.LocationInfo;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TodayForecastTest extends BaseTest {
    private static LocationInfo locationInfo;

    @BeforeClass
    public static void beforeClass() {
        locationInfo = new LocationInfo();
        locationInfo.setCountry("Ukraine");
        locationInfo.setAdminArea("Kyiv");
    }

    @Test
    public void constructor_correctJson_initFieldsFromJson() {
        TodayForecast forecast = new TodayForecast(TestResources.testJson, locationInfo);

        assertThat(forecast.getWindSpeed()).isEqualTo(6.79);
        assertThat(forecast.getDate()).isEqualTo(1486754618000l);
        assertThat(forecast.getCurrentTemp()).isEqualTo(57.36);
        assertThat(forecast.getLowTemp()).isEqualTo(52.84);
        assertThat(forecast.getHighTemp()).isEqualTo(59.35);
        assertThat(forecast.getPrecipitationChance()).isEqualTo(0);
        assertThat(forecast.getDescription()).isEqualTo("Light rain in the morning.");
        assertThat(forecast.getCondition()).isEqualTo("Overcast");
        assertThat(forecast.getIcon()).isEqualTo("cloudy");
        assertThat(forecast.getLocation()).isEqualTo("Ukraine, Kyiv");
    }

    @Test
    public void constructor_invalidJson_throwRuntimeException() {
        assertThatThrownBy(() -> new TodayForecast("", null)).isInstanceOf(RuntimeException.class);
    }
}
