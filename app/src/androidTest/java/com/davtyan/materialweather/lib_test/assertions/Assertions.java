package com.davtyan.materialweather.lib_test.assertions;

import android.view.ViewGroup;
import android.widget.ImageView;

public class Assertions {
    public static ViewGroupAssert assertThat(ViewGroup viewGroup) {
        return new ViewGroupAssert(viewGroup);
    }

    public static ImageViewAssert assertThat(ImageView imageView) {
        return new ImageViewAssert(imageView);
    }
}
