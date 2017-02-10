package com.davtyan.materialweather.lib_test.assertions;

import android.view.ViewGroup;

public class Assertions {
    public static ViewGroupAssert assertThat(ViewGroup viewGroup) {
        return new ViewGroupAssert(viewGroup);
    }
}
