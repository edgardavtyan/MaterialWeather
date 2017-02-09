package com.davtyan.materialweather;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.davtyan.materialweather.views.TodayWeatherCard;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;

public class MainActivity extends AppCompatActivity implements MainMvp.View {

    @BindView(R.id.today_weather) @Getter TodayWeatherCard todayWeatherView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        App app = (App) getApplicationContext();
        MainFactory factory = app.getTodayWeatherFactory(this, this, "Kyiv");
        MainMvp.Presenter presenter = factory.getPresenter();
        presenter.onCreate();
    }
}
