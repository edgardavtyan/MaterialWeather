package com.davtyan.materialweather.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.davtyan.materialweather.App;
import com.davtyan.materialweather.R;
import com.davtyan.materialweather.main.daily.DailyForecastAdapter;
import com.davtyan.materialweather.providers.darksky.IconFromCondition;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MainMvp.View {

    @BindView(R.id.current_date) TextView currentDateView;
    @BindView(R.id.current_temp) TextView currentTempView;
    @BindView(R.id.current_low_temp) TextView currentLowTempView;
    @BindView(R.id.current_high_temp) TextView currentHighTempView;
    @BindView(R.id.current_wind_speed) TextView currentWindView;
    @BindView(R.id.current_precipitation_chance) TextView currentPrecipitationChanceView;
    @BindView(R.id.current_summary) TextView currentSummaryView;
    @BindView(R.id.current_icon) ImageView currentConditionIconView;

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.main_wrapper) LinearLayout mainWrapper;
    @BindView(R.id.current_condition) TextView currentConditionView;

    @BindView(R.id.daily_forecasts) RecyclerView dailyList;
    @BindView(R.id.daily_summary) TextView dailySummaryView;

    @BindView(R.id.credit_icons) TextView creditIconsView;
    @BindView(R.id.credit_weather) TextView creditWeatherView;

    private MainMvp.Presenter presenter;
    private DailyForecastAdapter dailyForecastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        App app = (App) getApplicationContext();
        MainFactory factory = app.getTodayWeatherFactory(this, this, "Kyiv");
        presenter = factory.getPresenter();

        dailyForecastAdapter = factory.getDailyForecastAdapter();
        dailyList.setLayoutManager(new LinearLayoutManager(this));
        dailyList.setAdapter(dailyForecastAdapter);
        dailyList.setNestedScrollingEnabled(false);

        creditIconsView.setMovementMethod(LinkMovementMethod.getInstance());
        creditWeatherView.setMovementMethod(LinkMovementMethod.getInstance());

        presenter.onCreate();

        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                presenter.onRefresh();
                return true;
            default:
                return false;
        }
    }

    @Override
    public void setCurrentTemp(double temp) {
        currentTempView.setText(getString(R.string.pattern_temp_unit, temp));
    }

    @Override
    public void setCurrentCondition(String condition, String icon) {
        currentConditionView.setText(condition);
    }

    @Override
    public void setLocation(String location) {
        toolbar.setTitle(location);
    }

    @Override
    public void setDailySummary(String summary) {
        dailySummaryView.setText(summary);
    }

    @Override
    public void updateLists() {
        dailyForecastAdapter.notifyDataSetChangedNonFinal();
    }

    @Override
    public void setCurrentWindSpeed(double windSpeed) {
        currentWindView.setText(getString(R.string.pattern_wind, windSpeed));
    }

    @Override
    public void setCurrentDate(long date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("E dd/MM/yy");
        currentDateView.setText(dateFormat.format(new Date(date)));
    }

    @Override
    public void setCurrentLowTemp(double lowTemp) {
        currentLowTempView.setText(getString(R.string.pattern_temp_no_unit, lowTemp));
    }

    @Override
    public void setCurrentHighTemp(double highTemp) {
        currentHighTempView.setText(getString(R.string.pattern_temp_no_unit, highTemp));
    }

    @Override
    public void setCurrentPrecipitationChance(int precipitationChance) {
        currentPrecipitationChanceView.setText(getString(R.string.pattern_precipitation, precipitationChance));
    }

    @Override
    public void setCurrentSummary(String summary) {
        currentSummaryView.setText(summary);
    }

    @Override
    public void setCurrentIcon(String icon) {
        currentConditionIconView.setImageResource(IconFromCondition.get(icon));
    }
}
