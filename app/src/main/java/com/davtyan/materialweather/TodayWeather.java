package com.davtyan.materialweather;

import android.content.res.Resources;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodayWeather {
    @BindView(R.id.date) TextView dateView;
    @BindView(R.id.weather_icon) ImageView weatherIconView;
    @BindView(R.id.temps) TextView tempsView;
    @BindView(R.id.wind_speed) TextView windSpeedView;
    @BindView(R.id.rain) TextView rainView;
    @BindView(R.id.description) TextView descriptionView;
    @BindView(R.id.activity_main) LinearLayout activityMainView;

    private final SimpleDateFormat dateFormat;
    private final Resources res;

    public TodayWeather(MainActivity activity) {
        ButterKnife.bind(this, activity);
        dateFormat = new SimpleDateFormat("E dd/MM/yyy", Locale.getDefault());
        res = activity.getResources();
    }

    public void setDate(long timeMS) {
        dateView.setText(dateFormat.format(new Date(timeMS)));
    }

    public void setTemps(int lowTemp, int highTemp) {
        tempsView.setText(res.getString(R.string.today_pattern_temps, lowTemp, highTemp));
    }

    public void setWindSpeed(double windSpeed) {
        windSpeedView.setText(res.getString(R.string.today_pattern_wind, windSpeed));
    }

    public void setPrecipitationChance(int chance) {
        rainView.setText(res.getString(R.string.today_pattern_rain, chance));
    }

    public void setDescription(String description) {
        descriptionView.setText(description);
    }
}
