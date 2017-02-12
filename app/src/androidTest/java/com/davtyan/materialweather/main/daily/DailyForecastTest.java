package com.davtyan.materialweather.main.daily;

import com.davtyan.materialweather.lib_test.BaseTest;
import com.davtyan.materialweather.lib_test.utils.TestResources;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DailyForecastTest extends BaseTest {
    @Test
    public void convert_valid_json_to_fields() {
        DailyForecast forecast = new DailyForecast(TestResources.testDailyJson);
        assertThat(forecast.getDate()).isEqualTo(1486713600l);
        assertThat(forecast.getHumidity()).isEqualTo(0.9);
        assertThat(forecast.getIcon()).isEqualTo("rain");
        assertThat(forecast.getMaxTemp()).isEqualTo(59.35);
        assertThat(forecast.getMinTemp()).isEqualTo(52.84);
        assertThat(forecast.getPrecipChance()).isEqualTo(0.63);
        assertThat(forecast.getWindSpeed()).isEqualTo(5.86);
    }

    @Test
    public void throw_excption_given_invalid_json() {
        assertThatThrownBy(() -> new DailyForecast("123")).isInstanceOf(RuntimeException.class);
    }
}
