package com.davtyan.materialweather.main;

import android.annotation.SuppressLint;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.davtyan.materialweather.R;
import com.davtyan.materialweather.lib_test.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static com.davtyan.materialweather.lib_test.assertions.Assertions.assertThat;

@SuppressLint("StaticFieldLeak")
public class MainActivityTest extends BaseTest {
    private static MainActivity activity;
    private static MainMvp.Presenter presenter;

    @Override
    public void beforeEach() {
        super.beforeEach();

        if (activity == null) {
            presenter = mock(MainMvp.Presenter.class);

            MainFactory factory = mock(MainFactory.class);
            when(factory.getPresenter()).thenReturn(presenter);

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
    public void setCurrentCondition_setBackground() {
        LinearLayout mainWrapper = (LinearLayout) activity.findViewById(R.id.main_wrapper);
        runOnUiThread(() -> activity.setCurrentCondition("condition", "clear-day"));
        assertThat(mainWrapper).hasBackgroundResource(R.drawable.bmp_clear_day);
    }

    @Test
    public void setCurrentLocation_setText() {
        TextView currentLocationView = (TextView) activity.findViewById(R.id.location);
        runOnUiThread(() -> activity.setLocation("location"));
        assertThat(currentLocationView.getText()).isEqualTo("location");
    }
}
