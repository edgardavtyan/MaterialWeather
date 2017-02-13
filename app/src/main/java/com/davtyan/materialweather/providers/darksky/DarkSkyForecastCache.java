package com.davtyan.materialweather.providers.darksky;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class DarkSkyForecastCache {
    private final File forecastCacheFile;

    public DarkSkyForecastCache(Context context) {
        forecastCacheFile = new File(context.getCacheDir(), "forecast.json");
    }

    public void save(String forecast) {
        try {
            FileOutputStream stream = new FileOutputStream(forecastCacheFile);
            stream.write(forecast.getBytes(), 0, forecast.getBytes().length);
            stream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String get() {
        try {
            FileInputStream stream = new FileInputStream(forecastCacheFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            stream.close();
            reader.close();

            return stringBuilder.toString();
        } catch (Exception e) {
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
