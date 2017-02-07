package com.davtyan.materialweather;

import android.widget.TextView;

import com.davtyan.materialweather.lib_test.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TodayWeatherTest extends BaseTest {
    private static MainActivity activity;

    private TodayWeather todayWeather;

    @Override
    public void beforeEach() {
        super.beforeEach();

        if (activity == null) {
            activity = startActivity(MainActivity.class);
        }

        todayWeather = new TodayWeather(activity);
    }

    @Test
    public void set_date() {
        TextView dateView = (TextView) activity.findViewById(R.id.date);
        runOnUiThread(() -> todayWeather.setDate(1486490054000l));
        assertThat(dateView.getText()).isEqualTo("Tue 07/02/2017");
    }

    @Test
    public void set_temps() {
        TextView tempsView = (TextView) activity.findViewById(R.id.temps);
        runOnUiThread(() -> todayWeather.setTemps(17, 22));
        assertThat(tempsView.getText()).isEqualTo("17~22");
    }

    @Test
    public void set_wind_speed() {
        TextView windSpeedView = (TextView) activity.findViewById(R.id.wind_speed);
        runOnUiThread(() -> todayWeather.setWindSpeed(3.4));
        assertThat(windSpeedView.getText()).isEqualTo("3.4 m/s");
    }

    @Test
    public void set_rain_chance() {
        TextView rainView = (TextView) activity.findViewById(R.id.rain);
        runOnUiThread(() -> todayWeather.setPrecipitationChance(47));
        assertThat(rainView.getText()).isEqualTo("47 %");
    }

    @Test
    public void set_description() {
        TextView descriptionView = (TextView) activity.findViewById(R.id.description);
        runOnUiThread(() -> todayWeather.setDescription("description"));
        assertThat(descriptionView.getText()).isEqualTo("description");
    }
}
