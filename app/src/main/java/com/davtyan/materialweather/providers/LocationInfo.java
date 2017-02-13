package com.davtyan.materialweather.providers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationInfo {
    private double latitude;
    private double longitude;
    private String country;
    private String adminArea;
}
