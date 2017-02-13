package com.davtyan.materialweather.providers.darksky;

import com.davtyan.materialweather.lib_test.BaseTest;

import org.junit.Test;

import java.io.File;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DarkSkyForecastCacheTest extends BaseTest {
    private DarkSkyForecastCache cache;
    private File forecastCacheFile;

    @Override
    public void beforeEach() {
        super.beforeEach();
        cache = new DarkSkyForecastCache(context);
        forecastCacheFile = new File(context.getCacheDir(), "forecast.json");
    }

    @Test
    public void get_fileExists_returnFileContents() {
        cache.save("123");
        assertThat(cache.get()).isEqualTo("123");
        assertThat(cache.exists()).isTrue();
    }

    @Test
    public void get_fileNotExists_throwRuntimeException() {
        forecastCacheFile.delete();
        assertThatThrownBy(() -> cache.get()).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void save_givenNullFile_throwRuntimeException() {
        assertThatThrownBy(() -> cache.save(null)).isInstanceOf(RuntimeException.class);
    }

    @Test
    public void getCachedFileDate_returnCorrectDate() {
        cache.save("123");
        long cacheFileDate = cache.getCachedFileDate();
        long nowDate = new Date().getTime();
        assertThat(nowDate - cacheFileDate).isLessThanOrEqualTo(1000);
    }
}
