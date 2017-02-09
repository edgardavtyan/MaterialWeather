package com.davtyan.materialweather.components.today_weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.davtyan.materialweather.App;
import com.davtyan.materialweather.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodayWeatherFragment extends Fragment implements TodayWeatherMvp.View {
    @BindView(R.id.date) TextView dateView;
    @BindView(R.id.weather_icon) ImageView weatherIconView;
    @BindView(R.id.temps) TextView tempsView;
    @BindView(R.id.temp_current) TextView currentTempView;
    @BindView(R.id.wind_speed) TextView windSpeedView;
    @BindView(R.id.rain) TextView rainView;
    @BindView(R.id.description) TextView descriptionView;

    private TodayWeatherMvp.Presenter presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        App app = (App) getContext().getApplicationContext();
        TodayWeatherFactory factory = app.getTodayWeatherFactory(getContext(), this, "Kyiv");
        presenter = factory.getPresenter();
    }

    @Nullable
    @Override
    public View onCreateView(
            LayoutInflater inflater,
            @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.card_today, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onCreate();
    }

    @Override
    public void setDate(long timeMS) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("E dd/MM/yyy", Locale.getDefault());
        dateView.setText(dateFormat.format(new Date(timeMS)));
    }

    @Override
    public void setTemps(double lowTemp, double highTemp) {
        tempsView.setText(getString(R.string.today_pattern_temps, lowTemp, highTemp));
    }

    @Override
    public void setCurrentTemp(double currentTemp) {
        currentTempView.setText(getString(R.string.today_pattern_current_temp, currentTemp));
    }

    @Override
    public void setWindSpeed(double windSpeed) {
        windSpeedView.setText(getString(R.string.today_pattern_wind, windSpeed));
    }

    @Override
    public void setPrecipitationChance(int chance) {
        rainView.setText(getString(R.string.today_pattern_rain, chance));
    }

    @Override
    public void setDescription(String description) {
        descriptionView.setText(description);
    }
}
