package com.davtyan.materialweather.lib_test.utils;

import java.io.InputStream;
import java.util.Scanner;

public class TestResources {

    public static final String testJson;

    static {
        testJson = getResource("test-data.json");
    }

    private static String getResource(String name) {
        InputStream stream = TestResources.class.getClassLoader().getResourceAsStream(name);
        Scanner s = new Scanner(stream).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }
}