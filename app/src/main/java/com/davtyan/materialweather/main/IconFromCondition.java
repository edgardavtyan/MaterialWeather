package com.davtyan.materialweather.main;

import android.support.annotation.DrawableRes;

import com.davtyan.materialweather.R;

import java.util.HashMap;

public class IconFromCondition {
    private static final HashMap<String, Integer> map = new HashMap<>();

    static {
        map.put("clear-day", R.drawable.ic_clear_day);
        map.put("clear-night", R.drawable.ic_clear_night);
        map.put("rain", R.drawable.ic_rain);
        map.put("snow", R.drawable.ic_snow);
        map.put("sleet", R.drawable.ic_sleet);
        map.put("wind", R.drawable.ic_wind);
        map.put("fog", R.drawable.ic_fog);
        map.put("cloudy", R.drawable.ic_cloudy);
        map.put("partly-cloudy-day", R.drawable.ic_partly_cloudy_day);
        map.put("partly-cloudy-night", R.drawable.ic_partly_cloudy_night);
    }

    @DrawableRes
    public static int get(String condition) {
        return map.get(condition);
    }
}
