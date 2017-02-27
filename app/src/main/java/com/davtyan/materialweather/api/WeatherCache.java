package com.davtyan.materialweather.api;

import android.content.Context;

import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class WeatherCache {
    private static final String CACHE_FILE = "forecast.json";
    private static final long HOURS_UNTIL_OUTDATED = 3;

    private final File cacheFile;
    private final Gson gson;

    public WeatherCache(Context context, Gson gson) {
        cacheFile = new File(context.getCacheDir(), CACHE_FILE);
        this.gson = gson;
    }

    public void save(Forecast forecast) {
        try {
            byte[] forecastBytes = gson.toJson(forecast, Forecast.class).getBytes();
            FileOutputStream stream = new FileOutputStream(cacheFile);
            stream.write(forecastBytes);
            stream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Forecast get() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            FileInputStream inputStream = new FileInputStream(cacheFile);
            byte[] buffer = new byte[4096];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }

            String forecastString = new String(outputStream.toByteArray());
            return gson.fromJson(forecastString, Forecast.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isOutdated() {
        if (!cacheFile.exists()) return true;
        long dateNowHours = TimeUnit.MILLISECONDS.toHours(new Date().getTime());
        long dateCacheHours = TimeUnit.MILLISECONDS.toHours(cacheFile.lastModified());
        boolean isOutdated = (dateNowHours - dateCacheHours) > HOURS_UNTIL_OUTDATED;
        return isOutdated;
    }

    public long getLastUpdateTime() {
        return cacheFile.lastModified();
    }
}
