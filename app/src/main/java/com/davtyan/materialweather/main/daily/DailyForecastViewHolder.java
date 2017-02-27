package com.davtyan.materialweather.main.daily;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.davtyan.materialweather.R;
import com.davtyan.materialweather.databinding.ListitemDailyBinding;
import com.davtyan.materialweather.main.IconFromCondition;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DailyForecastViewHolder extends RecyclerView.ViewHolder {

    private final Context context;
    private final ListitemDailyBinding views;

    public DailyForecastViewHolder(View itemView) {
        super(itemView);
        views = ListitemDailyBinding.bind(itemView);
        context = itemView.getContext();
    }

    public void setDate(long dateMS) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E");
        Date date = new Date(dateMS * 1000);
        views.date.setText(simpleDateFormat.format(date));
    }

    public void setPrecipChance(double chance) {
        views.precipChance.setText(context.getString(R.string.pattern_precip, (int) (chance * 100)));
    }

    public void setWindSpeed(double windSpeed) {
        views.windSpeed.setText(context.getString(R.string.pattern_wind, windSpeed));
    }

    public void setTemps(double minTemp, double maxTemp) {
        views.minTemp.setText(context.getString(R.string.pattern_temp_no_unit, minTemp));
        views.maxTemp.setText(context.getString(R.string.pattern_temp_no_unit, maxTemp));
    }

    public void setConditionIcon(String icon) {
        views.icon.setImageResource(IconFromCondition.get(icon));
    }
}
