package com.davtyan.materialweather.providers.darksky;

import android.content.Context;

import com.davtyan.materialweather.main.TodayForecast;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

public class DarkSkyForecastCache {
    private final File forecastCacheFile;
    private final Gson gson;

    public DarkSkyForecastCache(Context context, Gson gson) {
        forecastCacheFile = new File(context.getCacheDir(), "forecast.json");
        this.gson = gson;
    }

    public void save(TodayForecast forecast) {
        try {
            FileOutputStream stream = new FileOutputStream(forecastCacheFile);
            String forecastJson = gson.toJson(forecast, TodayForecast.class);
            stream.write(forecastJson.getBytes(), 0, forecastJson.getBytes().length);
            stream.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public TodayForecast get() {
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

            return gson.fromJson(stringBuilder.toString(), TodayForecast.class);
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
