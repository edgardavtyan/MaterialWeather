package com.davtyan.materialweather;

import android.widget.TextView;

import com.davtyan.materialweather.lib_test.FragmentTest2;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TodayWeatherFragmentTest extends FragmentTest2 {
    private static TodayWeatherFragment fragment;

    @Override
    public void beforeEach() {
        super.beforeEach();
        fragment = new TodayWeatherFragment();
        initFragment(fragment);
    }

    @Test
    public void set_date() {
        TextView dateView = (TextView) fragment.getView().findViewById(R.id.date);
        runOnUiThread(() -> fragment.setDate(1486490054000l));
        assertThat(dateView.getText()).isEqualTo("Tue 07/02/2017");
    }

    @Test
    public void set_temps() {
        TextView tempsView = (TextView) fragment.getView().findViewById(R.id.temps);
        runOnUiThread(() -> fragment.setTemps(17, 22));
        assertThat(tempsView.getText()).isEqualTo("17~22");
    }

    @Test
    public void set_wind_speed() {
        TextView windSpeedView = (TextView) fragment.getView().findViewById(R.id.wind_speed);
        runOnUiThread(() -> fragment.setWindSpeed(3.4));
        assertThat(windSpeedView.getText()).isEqualTo("3.4 m/s");
    }

    @Test
    public void set_rain_chance() {
        TextView rainView = (TextView) fragment.getView().findViewById(R.id.rain);
        runOnUiThread(() -> fragment.setPrecipitationChance(47));
        assertThat(rainView.getText()).isEqualTo("47 %");
    }

    @Test
    public void set_description() {
        TextView descriptionView = (TextView) fragment.getView().findViewById(R.id.description);
        runOnUiThread(() -> fragment.setDescription("description"));
        assertThat(descriptionView.getText()).isEqualTo("description");
    }
}
