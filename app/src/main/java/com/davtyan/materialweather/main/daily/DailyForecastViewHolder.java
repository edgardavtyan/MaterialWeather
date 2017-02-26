package com.davtyan.materialweather.main.daily;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.davtyan.materialweather.R;
import com.davtyan.materialweather.providers.darksky.IconFromCondition;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DailyForecastViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.date) TextView dateView;
    @BindView(R.id.icon) ImageView iconView;
    @BindView(R.id.precipitation_chance) TextView precipChanceView;
    @BindView(R.id.wind_speed) TextView windSpeedView;
    @BindView(R.id.min_temp) TextView minTempView;
    @BindView(R.id.max_temp) TextView maxTempView;

    private final Context context;

    public DailyForecastViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        context = itemView.getContext();
    }

    public void setDate(long dateMS) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E");
        Date date = new Date(dateMS * 1000);
        dateView.setText(simpleDateFormat.format(date));
    }

    public void setPrecipitationChance(double chance) {
        precipChanceView.setText(context.getString(R.string.pattern_precipitation, (int) (chance * 100)));
    }

    public void setWindSpeed(double windSpeed) {
        windSpeedView.setText(context.getString(R.string.pattern_wind, windSpeed));
    }

    public void setTemps(double minTemp, double maxTemp) {
        minTempView.setText(context.getString(R.string.pattern_temp_no_unit, minTemp));
        maxTempView.setText(context.getString(R.string.pattern_temp_no_unit, maxTemp));
    }

    public void setConditionIcon(String icon) {
        iconView.setImageResource(IconFromCondition.get(icon));
    }
}
