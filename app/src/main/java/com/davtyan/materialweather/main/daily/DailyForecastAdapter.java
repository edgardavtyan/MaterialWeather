package com.davtyan.materialweather.main.daily;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davtyan.materialweather.R;
import com.davtyan.materialweather.lib.testable.TestableAdapter;
import com.davtyan.materialweather.main.MainMvp;

public class DailyForecastAdapter extends TestableAdapter<DailyForecastViewHolder> {
    private MainMvp.Presenter presenter;

    public DailyForecastAdapter(MainMvp.Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public DailyForecastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem_daily, parent, false);
        return new DailyForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DailyForecastViewHolder holder, int position) {
        presenter.onBindDailyHolder(holder, position);
    }

    @Override
    public int getItemCount() {
        return presenter.getDailyItemsCount();
    }
}
