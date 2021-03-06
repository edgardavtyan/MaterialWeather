package com.davtyan.materialweather.lib_test;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.view.ContextThemeWrapper;

import com.davtyan.materialweather.App;
import com.davtyan.materialweather.R;

import org.junit.Before;

import static org.mockito.Mockito.spy;

public class BaseTest {
    protected Context context;
    protected Instrumentation instrumentation;
    protected App app;

    @Before
    public void beforeEach() {
        instrumentation = InstrumentationRegistry.getInstrumentation();
        context = spy(new ContextThemeWrapper(instrumentation.getTargetContext(), R.style.AppTheme));
        app = (App) context.getApplicationContext();
    }

    @SuppressWarnings("unchecked")
    protected <T> T startActivity(Class activityClass) {
        Intent intent = new Intent(context, activityClass);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return (T) instrumentation.startActivitySync(intent);
    }

    protected void runOnUiThread(Runnable runnable) {
        instrumentation.runOnMainSync(runnable);
    }
}
