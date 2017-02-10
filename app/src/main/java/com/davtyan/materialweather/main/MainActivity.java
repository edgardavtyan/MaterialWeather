package com.davtyan.materialweather.main;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.davtyan.materialweather.App;
import com.davtyan.materialweather.R;
import com.davtyan.materialweather.providers.darksky.DrawableFromCondition;
import com.davtyan.materialweather.views.TodayWeatherCard;

import butterknife.BindView;
import butterknife.ButterKnife;
import lombok.Getter;

public class MainActivity extends AppCompatActivity implements MainMvp.View {

    @BindView(R.id.today_weather) @Getter TodayWeatherCard todayWeatherView;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.main_wrapper) LinearLayout mainWrapper;
    @BindView(R.id.current_temp) TextView currentTempView;
    @BindView(R.id.current_condition) TextView currentConditionView;
    @BindView(R.id.location) TextView locationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        App app = (App) getApplicationContext();
        MainFactory factory = app.getTodayWeatherFactory(this, this, "Kyiv");
        MainMvp.Presenter presenter = factory.getPresenter();
        presenter.onCreate();

        toolbar.setTitleTextAppearance(this, R.style.Toolbar_Title);

        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }

    @Override
    public void setCurrentTemp(double temp) {
        currentTempView.setText(getString(R.string.today_pattern_current_temp, temp));
    }

    @Override
    public void setCurrentCondition(String condition, String icon) {
        currentConditionView.setText(condition);
        mainWrapper.setBackgroundResource(DrawableFromCondition.get(icon));
    }

    @Override
    public void setLocation(String location) {
        locationView.setText(location);
    }
}
