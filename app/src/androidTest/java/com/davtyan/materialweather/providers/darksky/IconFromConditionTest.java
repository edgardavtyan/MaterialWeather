package com.davtyan.materialweather.providers.darksky;

import com.davtyan.materialweather.R;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class IconFromConditionTest {
    @Test
    public void constructor_coverage() {
        new IconFromCondition();
    }

    @Test
    public void return_correct_icon_for_condition() {
        assertThat(IconFromCondition.get("clear-day")).isEqualTo(R.drawable.ic_clear_day);
        assertThat(IconFromCondition.get("clear-night")).isEqualTo(R.drawable.ic_clear_night);
        assertThat(IconFromCondition.get("rain")).isEqualTo(R.drawable.ic_rain);
        assertThat(IconFromCondition.get("snow")).isEqualTo(R.drawable.ic_snow);
        assertThat(IconFromCondition.get("sleet")).isEqualTo(R.drawable.ic_sleet);
        assertThat(IconFromCondition.get("wind")).isEqualTo(R.drawable.ic_wind);
        assertThat(IconFromCondition.get("fog")).isEqualTo(R.drawable.ic_fog);
        assertThat(IconFromCondition.get("cloudy")).isEqualTo(R.drawable.ic_cloudy);
        assertThat(IconFromCondition.get("partly-cloudy-day")).isEqualTo(R.drawable.ic_partly_cloudy_day);
        assertThat(IconFromCondition.get("partly-cloudy-night")).isEqualTo(R.drawable.ic_partly_cloudy_night);
    }
}
