package com.davtyan.materialweather.lib_test.assertions;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.test.InstrumentationRegistry;
import android.support.v4.content.ContextCompat;
import android.support.v7.view.ContextThemeWrapper;
import android.view.ViewGroup;

import com.davtyan.materialweather.R;

import org.assertj.core.api.AbstractAssert;

public class ViewGroupAssert extends AbstractAssert<ViewGroupAssert, ViewGroup> {
    private final ContextThemeWrapper context;

    public ViewGroupAssert(ViewGroup actual) {
        super(actual, ViewGroupAssert.class);
        context = new ContextThemeWrapper(
                InstrumentationRegistry.getInstrumentation().getTargetContext(),
                R.style.AppTheme);
    }

    public ViewGroupAssert hasBackgroundResource(@DrawableRes int drawableId) {
        isNotNull();

        Drawable expectedDrawable = ContextCompat.getDrawable(context, drawableId);
        Drawable actualDrawable = actual.getBackground();
        if (!DrawableAssert.areDrawablesEqual(expectedDrawable, actualDrawable)) {
            failWithMessage("Expected drawable to be equal");
        }

        return this;
    }
}
