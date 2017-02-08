package com.davtyan.materialweather.components.today_weather;

import com.davtyan.materialweather.lib_test.BaseTest;

import static org.assertj.core.api.Assertions.assertThat;

public class TodayWeatherUrlTest extends BaseTest {
    private TodayWeatherUrl url;

    @Override
    public void beforeEach() {
        super.beforeEach();
        url = new TodayWeatherUrl("111");
    }

    private void build_correct_url() {
        assertThat(url.build(22.33, 44.55))
                .isEqualTo("https://api.darksky.net/forecast/111/22.33,44.55?exclude=minutely,flags");
    }
}
