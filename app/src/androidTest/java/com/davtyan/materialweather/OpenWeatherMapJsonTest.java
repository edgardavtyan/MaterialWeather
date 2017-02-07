package com.davtyan.materialweather;

import com.davtyan.materialweather.utils.WebClient;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OpenWeatherMapJsonTest {
    @Test
    public void validate_api_returns_correct_json() throws JSONException {
        String url = "http://api.openweathermap.org/data/2.5/weather" +
                "?q=London,uk" +
                "&appid=61319564ba4d7008fd72ac026fc1045b";
        WebClient webClient = new WebClient();

        JSONObject root = new JSONObject(webClient.getString(url));

        JSONObject coordinates = root.getJSONObject("coord");
        assertThat(coordinates.names().length()).isEqualTo(2);
        coordinates.getDouble("lon");
        coordinates.getDouble("lat");

        JSONObject weather = root.getJSONArray("weather").getJSONObject(0);
        weather.getInt("id");
        weather.getString("main");
        weather.getString("description");
        weather.getString("icon");

        root.getString("base");

        JSONObject main = root.getJSONObject("main");
        assertThat(main.names().length()).isEqualTo(5);
        main.getDouble("temp");
        main.getInt("pressure");
        main.getDouble("humidity");
        main.getDouble("temp_min");
        main.getDouble("temp_max");

        root.getInt("visibility");

        JSONObject wind = root.getJSONObject("wind");
        assertThat(wind.names().length()).isEqualTo(2);
        wind.getDouble("speed");
        wind.getInt("deg");

        JSONObject clouds = root.getJSONObject("clouds");
        assertThat(clouds.names().length()).isEqualTo(1);
        clouds.getInt("all");

        root.getInt("dt");

        JSONObject sys = root.getJSONObject("sys");
        assertThat(sys.names().length()).isEqualTo(6);
        sys.getInt("type");
        sys.getInt("id");
        sys.getDouble("message");
        sys.getString("country");
        sys.getInt("sunrise");
        sys.getInt("sunset");

        root.getInt("id");
        root.getString("name");
        root.getInt("cod");
    }
}
