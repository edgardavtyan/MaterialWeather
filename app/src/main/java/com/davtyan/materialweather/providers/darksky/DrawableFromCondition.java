package com.davtyan.materialweather.providers.darksky;

import android.support.annotation.DrawableRes;

import com.davtyan.materialweather.R;

import java.util.HashMap;

public class DrawableFromCondition {
    private static final HashMap<String, Integer> map = new HashMap<>();

    static {
        map.put("clear-day", R.drawable.bmp_clear_day);
        map.put("clear-night", R.drawable.bmp_clear_night);
        map.put("rain", R.drawable.bmp_rain);
        map.put("snow", R.drawable.bmp_snow);
        map.put("sleet", R.drawable.bmp_sleet);
        map.put("wind", R.drawable.bmp_wind);
        map.put("fog", R.drawable.bmp_fog);
        map.put("cloudy", R.drawable.bmp_cloudy);
        map.put("partly-cloudy-day", R.drawable.bmp_partly_cloudy);
        map.put("partly-cloudy-night", R.drawable.bmp_partly_cloudy);
    }

    @DrawableRes
    public static int get(String condition) {
        return map.get(condition);
    }
}
