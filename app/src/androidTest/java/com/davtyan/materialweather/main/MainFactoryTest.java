package com.davtyan.materialweather.main;

import com.davtyan.materialweather.lib_test.FactoryTest;

import org.junit.Test;

public class MainFactoryTest extends FactoryTest {
    private MainFactory factory;

    @Override
    public void beforeEach() {
        super.beforeEach();
        factory = new MainFactory(context, null, "Location");
    }

    @Test
    public void test_factory_methods() throws Exception {
        testFactoryMethod(factory::getCache);
        testFactoryMethod(factory::getDailyForecastAdapter);
        testFactoryMethod(factory::getDarkSkyWeatherProvider);
        testFactoryMethod(factory::getGeocoding);
        testFactoryMethod(factory::getModel);
        testFactoryMethod(factory::getPresenter);
        testFactoryMethod(factory::getWebClient);
    }
}
