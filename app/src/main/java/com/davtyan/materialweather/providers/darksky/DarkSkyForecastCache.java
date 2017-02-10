package com.davtyan.materialweather.providers.darksky;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import lombok.Cleanup;

public class DarkSkyForecastCache {
    private final File forecastCacheFile;

    public DarkSkyForecastCache(Context context) {
        forecastCacheFile = new File(context.getCacheDir(), "forecast.json");
    }

    public void save(String forecast) {
        try {
            @Cleanup FileOutputStream stream = new FileOutputStream(forecastCacheFile);
            stream.write(forecast.getBytes(), 0, forecast.getBytes().length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String get() {
        try {
            @Cleanup FileInputStream stream = new FileInputStream(forecastCacheFile);
            @Cleanup BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder stringBuilder = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            return stringBuilder.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public long getCachedFileDate() {
        return forecastCacheFile.lastModified();
    }

    public boolean exists() {
        return forecastCacheFile.exists();
    }
}
