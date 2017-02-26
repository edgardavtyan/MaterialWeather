package com.davtyan.materialweather.main;

import android.annotation.SuppressLint;
import android.support.annotation.IdRes;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.davtyan.materialweather.R;
import com.davtyan.materialweather.lib_test.BaseTest;
import com.davtyan.materialweather.main.daily.DailyForecastAdapter;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

@SuppressLint("StaticFieldLeak")
public class MainActivityTest extends BaseTest {
    private static MainActivity activity;
    private static MainMvp.Presenter presenter;
    private static DailyForecastAdapter adapter;

    @Override
    public void beforeEach() {
        super.beforeEach();

        if (activity == null) {
            presenter = mock(MainMvp.Presenter.class);
            adapter = mock(DailyForecastAdapter.class);

            MainFactory factory = mock(MainFactory.class);
            when(factory.getPresenter()).thenReturn(presenter);
            when(factory.getDailyForecastAdapter()).thenReturn(adapter);

            app.setMainFactory(factory);

            activity = startActivity(MainActivity.class);
        }
    }

    @Test
    public void onCreate_callPresenter() {
        verify(presenter).onCreate();
    }

    @Test
    public void setCurrentTemp_setTextWithPattern() {
        TextView currentTempView = (TextView) activity.findViewById(R.id.current_temp);
        runOnUiThread(() -> activity.setCurrentTemp(1.1));
        assertThat(currentTempView.getText()).isEqualTo("1°");
    }

    @Test
    public void setCurrentCondition_setText() {
        TextView currentConditionView = (TextView) activity.findViewById(R.id.current_condition);
        runOnUiThread(() -> activity.setCurrentCondition("condition", "clear-day"));
        assertThat(currentConditionView.getText()).isEqualTo("condition");
    }

    @Test
    public void setCurrentLocation_setToolbarText() {
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        runOnUiThread(() -> activity.setLocation("location"));
        assertThat(toolbar.getTitle()).isEqualTo("location");
    }

    @Test
    public void setDailySummary_setText() {
        TextView dailySummaryView = (TextView) activity.findViewById(R.id.daily_summary);
        runOnUiThread(() -> activity.setDailySummary("daily summary"));
        assertThat(dailySummaryView.getText()).isEqualTo("daily summary");
    }

    @Test
    public void onOptionsMenuItemSelected_refreshMenuItem_callPresenter() {
        clickOnMenuItemWithId(R.id.menu_refresh);
        verify(presenter).onRefresh();
    }

    @Test
    public void onOptionsMenuItemSelected_otherMenuItem_doNothing() {
        clickOnMenuItemWithId(-1);
        verifyNoMoreInteractions(presenter);
    }

    @Test
    public void updateLists_notifyDailyAdapter() {
        activity.updateLists();
        verify(adapter).notifyDataSetChangedNonFinal();
    }

    @Test
    public void testViewBinding() {
        MainActivity_ViewBinding viewBinding = new MainActivity_ViewBinding<>(activity, activity.getWindow().getDecorView());
        viewBinding.unbind();
        assertThatThrownBy(viewBinding::unbind).isInstanceOf(IllegalStateException.class);
        activity = null;
    }

    private void clickOnMenuItemWithId(@IdRes int id) {
        MenuItem item = mock(MenuItem.class);
        when(item.getItemId()).thenReturn(id);
        activity.onOptionsItemSelected(item);
    }
}
