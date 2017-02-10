package com.davtyan.materialweather.main;

import android.location.Address;

import com.davtyan.materialweather.lib_test.BaseTest;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.util.Locale;
import java.util.Scanner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TodayForecastTest extends BaseTest {
    private static String json;
    private static Address address;

    @BeforeClass
    public static void beforeClass() {
        InputStream stream = TodayForecastTest.class.getClassLoader().getResourceAsStream("test-data.json");
        Scanner s = new Scanner(stream).useDelimiter("\\A");
        json = s.hasNext() ? s.next() : "";

        address = new Address(Locale.getDefault());
        address.setCountryName("Ukraine");
        address.setAdminArea("Kyiv");
    }

    @Test
    public void constructor_correctJson_initFieldsFromJson() {
        TodayForecast forecast = new TodayForecast(json, address);

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