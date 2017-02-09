package com.davtyan.materialweather.views;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.davtyan.materialweather.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindDimen;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TodayWeatherCard extends CardView {
    @BindDimen(R.dimen.space_small) int spaceSmall;

    @BindView(R.id.date) TextView dateView;
    @BindView(R.id.weather_icon) ImageView weatherIconView;
    @BindView(R.id.temps) TextView tempsView;
    @BindView(R.id.wind_speed) TextView windSpeedView;
    @BindView(R.id.rain) TextView rainView;
    @BindView(R.id.description) TextView descriptionView;

    public TodayWeatherCard(Context context) {
        super(context);
        init();
    }

    public TodayWeatherCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TodayWeatherCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setDate(long timeMS) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("E dd/MM/yyy", Locale.getDefault());
        dateView.setText(dateFormat.format(new Date(timeMS)));
    }

    public void setTemps(double lowTemp, double highTemp) {
        tempsView.setText(getContext().getString(R.string.today_pattern_temps, lowTemp, highTemp));
    }

    public void setWindSpeed(double windSpeed) {
        windSpeedView.setText(getContext().getString(R.string.today_pattern_wind, windSpeed));
    }

    public void setPrecipitationChance(int chance) {
        rainView.setText(getContext().getString(R.string.today_pattern_rain, chance));
    }

    public void setDescription(String description) {
        descriptionView.setText(description);
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.card_today, this, true);
        ButterKnife.bind(this);

        CardView.LayoutParams layoutParams = new CardView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(spaceSmall, spaceSmall, spaceSmall, spaceSmall);
        setLayoutParams(layoutParams);
    }
}
