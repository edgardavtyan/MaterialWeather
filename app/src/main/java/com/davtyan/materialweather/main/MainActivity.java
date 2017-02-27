package com.davtyan.materialweather.main;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;

import com.davtyan.materialweather.App;
import com.davtyan.materialweather.R;
import com.davtyan.materialweather.databinding.ActivityMainBinding;
import com.davtyan.materialweather.main.daily.DailyForecastAdapter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements MainMvp.View {

    private ActivityMainBinding views;
    private MainMvp.Presenter presenter;
    private DailyForecastAdapter dailyForecastAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        views = DataBindingUtil.setContentView(this, R.layout.activity_main);

        App app = (App) getApplicationContext();
        MainFactory factory = app.getTodayWeatherFactory(this, this, "Kyiv");
        presenter = factory.getPresenter();

        dailyForecastAdapter = factory.getDailyForecastAdapter();
        views.dailyForecasts.setLayoutManager(new LinearLayoutManager(this));
        views.dailyForecasts.setAdapter(dailyForecastAdapter);
        views.dailyForecasts.setNestedScrollingEnabled(false);

        views.creditIcons.setMovementMethod(LinkMovementMethod.getInstance());
        views.creditWeather.setMovementMethod(LinkMovementMethod.getInstance());

        presenter.onCreate();

        setSupportActionBar(views.toolbar);
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
    public void setLocation(String location) {
        views.toolbar.setTitle(location);
    }

    @Override
    public void setDailySummary(String summary) {
        views.dailySummary.setText(summary);
    }

    @Override
    public void updateLists() {
        dailyForecastAdapter.notifyDataSetChangedNonFinal();
    }

    @Override
    public void setCurrentCondition(String condition, String icon) {
        views.current.condition.setText(condition);
    }

    @Override
    public void setCurrentTemp(double temp) {
        views.current.temp.setText(getString(R.string.pattern_temp_unit, temp));
    }

    @Override
    public void setCurrentWindSpeed(double windSpeed) {
        views.current.windSpeed.setText(getString(R.string.pattern_wind, windSpeed));
    }

    @Override
    public void setCurrentDate(long date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("E dd/MM/yy");
        views.current.date.setText(dateFormat.format(new Date(date)));
    }

    @Override
    public void setCurrentLowTemp(double lowTemp) {
        views.current.tempMin.setText(getString(R.string.pattern_temp_no_unit, lowTemp));
    }

    @Override
    public void setCurrentHighTemp(double highTemp) {
        views.current.tempMax.setText(getString(R.string.pattern_temp_no_unit, highTemp));
    }

    @Override
    public void setCurrentPrecipChance(double precipChance) {
        views.current.precipChance.setText(getString(R.string.pattern_precip, (int) (precipChance * 100)));
    }

    @Override
    public void setCurrentSummary(String summary) {
        views.current.summary.setText(summary);
    }

    @Override
    public void setCurrentIcon(String icon) {
        views.current.icon.setImageResource(IconFromCondition.get(icon));
    }
}
