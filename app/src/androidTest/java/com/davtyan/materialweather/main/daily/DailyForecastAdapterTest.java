package com.davtyan.materialweather.main.daily;

import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.davtyan.materialweather.lib_test.BaseTest;
import com.davtyan.materialweather.main.MainMvp;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DailyForecastAdapterTest extends BaseTest {
    private DailyForecastAdapter adapter;
    private MainMvp.Presenter presenter;

    @Override
    public void beforeEach() {
        super.beforeEach();
        presenter = mock(MainMvp.Presenter.class);
        adapter = new DailyForecastAdapter(presenter);
    }

    @Test
    public void create_correct_view_holder() {
        ViewGroup parent = new LinearLayout(context);
        assertThat(adapter.onCreateViewHolder(parent, 0)).isInstanceOf(DailyForecastViewHolder.class);
    }

    @Test
    public void get_item_count_from_presenter() {
        when(presenter.getDailyItemsCount()).thenReturn(1);
        assertThat(adapter.getItemCount()).isEqualTo(1);
    }

    @Test
    public void bind_view_holder_via_presenter() {
        DailyForecastViewHolder holder = mock(DailyForecastViewHolder.class);
        adapter.onBindViewHolder(holder, 2);
        verify(presenter).onBindDailyHolder(holder, 2);
    }
}
