package com.davtyan.materialweather.providers.darksky;

import com.davtyan.materialweather.main.TodayForecast;
import com.davtyan.materialweather.main.daily.DailyForecast;
import com.davtyan.materialweather.providers.Geocoding;
import com.davtyan.materialweather.providers.LocationInfo;
import com.davtyan.materialweather.utils.WebClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class DarkSkyWeatherProvider {
    private static final int HOURS_UNTIL_REFRESH = 1;

    private final WebClient webClient;
    private final Geocoding geocoding;
    private final DarkSkyForecastCache cache;
    private final String apiKey;

    public DarkSkyWeatherProvider(
            WebClient webClient,
            Geocoding geocoding,
            DarkSkyForecastCache cache,
            String apiKey) {
        this.webClient = webClient;
        this.geocoding = geocoding;
        this.cache = cache;
        this.apiKey = apiKey;
    }

    public TodayForecast getForecastForToday(String location) {
        if (isNonOutdatedCachedForecastAvailable()) {
            return cache.get();
        } else {
            LocationInfo address = geocoding.getAddressFromLocation(location);
            String forecast = webClient.getString(getUrl(address.getLatitude(), address.getLongitude()));
            cache.save(parseJson(forecast, address));
            return parseJson(forecast, getFullLocation(location));
        }
    }

    public LocationInfo getFullLocation(String location) {
        return geocoding.getAddressFromLocation(location);
    }

    public TodayForecast getForecastFromCache() {
        return cache.get();
    }

    public boolean isNonOutdatedCachedForecastAvailable() {
        long dateNow = new Date().getTime();
        long dateCache = cache.getCachedFileDate();
        long dateDiffHours = TimeUnit.MILLISECONDS.toHours(dateNow - dateCache);
        return cache.exists() && dateDiffHours < HOURS_UNTIL_REFRESH;
    }

    private String getUrl(double latitude, double longitude) {
        return String.format(
                "https://api.darksky.net/forecast/%s/%f,%f?units=si&exclude=minutely",
                apiKey, latitude, longitude);
    }

    private TodayForecast parseJson(String jsonString, LocationInfo address) {
        try {
            JSONObject root = new JSONObject(jsonString);
            JSONObject currentWeather = root.getJSONObject("currently");
            TodayForecast forecast = new TodayForecast();
            forecast.setDate(currentWeather.getLong("time") * 1000);
            forecast.setWindSpeed(currentWeather.getDouble("windSpeed"));
            forecast.setPrecipitationChance((int) (currentWeather.getDouble("precipProbability") * 100));
            forecast.setCurrentTemp(currentWeather.getDouble("temperature"));
            forecast.setCondition(currentWeather.getString("summary"));
            forecast.setIcon(currentWeather.getString("icon"));
            forecast.setLocation(String.format("%s, %s", address.getCountry(), address.getAdminArea()));
            forecast.setDailySummary(root.getJSONObject("daily").getString("summary"));

            JSONObject dailyFirstDay = root
                    .getJSONObject("daily")
                    .getJSONArray("data")
                    .getJSONObject(0);
            forecast.setDescription(dailyFirstDay.getString("summary"));
            forecast.setLowTemp(dailyFirstDay.getDouble("temperatureMin"));
            forecast.setHighTemp(dailyFirstDay.getDouble("temperatureMax"));

            List<DailyForecast> dailyForecasts = new ArrayList<>();
            JSONArray dailyForecastsJson = root.getJSONObject("daily").getJSONArray("data");
            for (int i = 0; i < dailyForecastsJson.length(); i++) {
                dailyForecasts.add(new DailyForecast(dailyForecastsJson.getJSONObject(i).toString()));
            }
            forecast.setDailyForecasts(dailyForecasts);

            return forecast;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
