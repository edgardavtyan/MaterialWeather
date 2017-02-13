package com.davtyan.materialweather.providers;

import com.davtyan.materialweather.lib_test.BaseTest;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class GeocodingTest extends BaseTest {
    private Geocoding geocoding;

    @Override
    public void beforeEach() {
        super.beforeEach();
        geocoding = new Geocoding(context);
    }

    @Test
    public void getAddressFromLocation_returnCorrectAddress() {
        LocationInfo locationInfo = geocoding.getAddressFromLocation("Kyiv");
        assertThat(locationInfo.getLatitude()).isEqualTo(50.4501);
        assertThat(locationInfo.getLongitude()).isEqualTo(30.5234);
        assertThat(locationInfo.getCountry()).isEqualTo("Ukraine");
        assertThat(locationInfo.getAdminArea()).isEqualTo("Kyiv City");
    }

    @Test
    public void getAddressFromLocation_nullLocation_throwRuntimeException() {
        assertThatThrownBy(() -> geocoding.getAddressFromLocation(null)).isInstanceOf(RuntimeException.class);
    }
}
