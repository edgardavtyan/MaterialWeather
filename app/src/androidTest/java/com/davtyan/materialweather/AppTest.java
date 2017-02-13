package com.davtyan.materialweather;

import com.davtyan.materialweather.lib_test.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AppTest extends BaseTest {
    private App app;

    @Override
    public void beforeEach() {
        super.beforeEach();
        app = new App();
    }

    @Test
    public void getTodayWeatherFactory_returnNewFactoryEachTime() {
        assertThat(app.getTodayWeatherFactory(context, null, "location"))
                .isNotSameAs(app.getTodayWeatherFactory(context, null, "location"));
    }
}
