package com.davtyan.materialweather.views;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.davtyan.materialweather.R;
import com.davtyan.materialweather.lib_test.BaseTest;

import org.junit.Test;

import static com.davtyan.materialweather.lib_test.assertions.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TodayWeatherCardTest extends BaseTest {
    private TodayWeatherCard todayWeatherCard;
    private LayoutInflater inflater;

    @Override
    public void beforeEach() {
        super.beforeEach();
        todayWeatherCard = new TodayWeatherCard(context, null, 0);
        inflater = spy(LayoutInflater.from(context));
        when(context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).thenReturn(inflater);
    }

    @Test
    public void one_arg_constructor_inflates_view_with_correct_params() {
        TodayWeatherCard card = new TodayWeatherCard(context);
        verify(inflater).inflate(R.layout.card_today, card, true);
    }

    @Test
    public void two_args_constructor_inflates_view_with_correct_params() {
        TodayWeatherCard card = new TodayWeatherCard(context, null);
        verify(inflater).inflate(R.layout.card_today, card, true);
    }

    @Test
    public void three_args_constructor_inflates_view_with_correct_params() {
        TodayWeatherCard card = new TodayWeatherCard(context, null, 0);
        verify(inflater).inflate(R.layout.card_today, card, true);
    }

    @Test
    public void set_date() {
        TextView dateView = (TextView) todayWeatherCard.findViewById(R.id.date);
        todayWeatherCard.setDate(1486924586123l);
        assertThat(dateView.getText()).isEqualTo("Sun 12/02/2017");
    }

    @Test
    public void set_temps() {
        TextView tempsView = (TextView) todayWeatherCard.findViewById(R.id.temps);
        todayWeatherCard.setTemps(12.3, 23.4);
        assertThat(tempsView.getText()).isEqualTo("12° / 23°");
    }

    @Test
    public void set_wind_speed() {
        TextView windSpeedView = (TextView) todayWeatherCard.findViewById(R.id.wind_speed);
        todayWeatherCard.setWindSpeed(34.5);
        assertThat(windSpeedView.getText()).isEqualTo("34.5 m/s");
    }

    @Test
    public void set_precipitation_chance() {
        TextView precipitationChanceView = (TextView) todayWeatherCard.findViewById(R.id.rain);
        todayWeatherCard.setPrecipitationChance(45);
        assertThat(precipitationChanceView.getText()).isEqualTo("45%");
    }

    @Test
    public void set_description() {
        TextView descriptionView = (TextView) todayWeatherCard.findViewById(R.id.description);
        todayWeatherCard.setDescription("description");
        assertThat(descriptionView.getText()).isEqualTo("description");
    }

    @Test
    public void set_icon() {
        ImageView iconView = (ImageView) todayWeatherCard.findViewById(R.id.weather_icon);
        todayWeatherCard.setIcon("clear-night");
        assertThat(iconView).hasImageResource(R.drawable.ic_clear_night);
    }

    @Test
    public void testViewBinding() {
        TodayWeatherCard_ViewBinding viewBinding = new TodayWeatherCard_ViewBinding<>(todayWeatherCard, todayWeatherCard);
        viewBinding.unbind();
        assertThatThrownBy(viewBinding::unbind).isInstanceOf(IllegalStateException.class);
    }
}
