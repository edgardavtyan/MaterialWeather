package com.davtyan.materialweather.providers.darksky;

import com.davtyan.materialweather.R;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DrawableFromConditionTest {
    @Test
    public void constructor_coverage() {
        new DrawableFromCondition();
    }

    @Test
    public void return_correct_icon_for_condition() {
        assertThat(DrawableFromCondition.get("clear-day")).isEqualTo(R.drawable.bmp_clear_day);
        assertThat(DrawableFromCondition.get("clear-night")).isEqualTo(R.drawable.bmp_clear_night);
        assertThat(DrawableFromCondition.get("rain")).isEqualTo(R.drawable.bmp_rain);
        assertThat(DrawableFromCondition.get("snow")).isEqualTo(R.drawable.bmp_snow);
        assertThat(DrawableFromCondition.get("sleet")).isEqualTo(R.drawable.bmp_sleet);
        assertThat(DrawableFromCondition.get("wind")).isEqualTo(R.drawable.bmp_wind);
        assertThat(DrawableFromCondition.get("fog")).isEqualTo(R.drawable.bmp_fog);
        assertThat(DrawableFromCondition.get("cloudy")).isEqualTo(R.drawable.bmp_cloudy);
        assertThat(DrawableFromCondition.get("partly-cloudy-day")).isEqualTo(R.drawable.bmp_partly_cloudy_day);
        assertThat(DrawableFromCondition.get("partly-cloudy-night")).isEqualTo(R.drawable.bmp_partly_cloudy_day);
    }
}