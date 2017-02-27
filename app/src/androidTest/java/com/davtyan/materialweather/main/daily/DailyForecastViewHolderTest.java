package com.davtyan.materialweather.main.daily;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.davtyan.materialweather.R;
import com.davtyan.materialweather.lib_test.BaseTest;

import org.junit.Test;

import static com.davtyan.materialweather.lib_test.assertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class DailyForecastViewHolderTest extends BaseTest {
    private DailyForecastViewHolder holder;
    private View itemView;

    @Override
    public void beforeEach() {
        super.beforeEach();
        itemView = LayoutInflater.from(context).inflate(R.layout.listitem_daily, null, false);
        holder = new DailyForecastViewHolder(itemView);
    }

    @Test
    public void set_date() {
        TextView dateView = (TextView) itemView.findViewById(R.id.date);
        holder.setDate(1486932696l);
        assertThat(dateView.getText()).isEqualTo("Sun");
    }

    @Test
    public void set_condition_icon() {
        ImageView iconView = (ImageView) itemView.findViewById(R.id.icon);
        holder.setConditionIcon("clear-night");
        assertThat(iconView).hasImageResource(R.drawable.ic_clear_night);
    }

    @Test
    public void set_precipitation_chance() {
        TextView precipitationChanceView = (TextView) itemView.findViewById(R.id.precipitation_chance);
        holder.setPrecipChance(0.12);
        assertThat(precipitationChanceView.getText()).isEqualTo("12%");
    }

    @Test
    public void set_wind_speed() {
        TextView windSpeedView = (TextView) itemView.findViewById(R.id.wind_speed);
        holder.setWindSpeed(23.4);
        assertThat(windSpeedView.getText()).isEqualTo("23.4 m/s");
    }

    @Test
    public void set_temps() {
        TextView minTempView = (TextView) itemView.findViewById(R.id.min_temp);
        TextView maxTempView = (TextView) itemView.findViewById(R.id.max_temp);
        holder.setTemps(34.5, 45.6);
        assertThat(minTempView.getText()).isEqualTo("34°");
        assertThat(maxTempView.getText()).isEqualTo("46°");
    }

    @Test
    public void testViewBinding() {
        DailyForecastViewHolder_ViewBinding viewBinding = new DailyForecastViewHolder_ViewBinding<>(holder, itemView);
        viewBinding.unbind();
        assertThatThrownBy(viewBinding::unbind).isInstanceOf(IllegalStateException.class);
    }
}
