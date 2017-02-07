package com.davtyan.materialweather.lib_test;

import android.support.v4.app.Fragment;

public class FragmentTest2 extends BaseTest {
	public void initFragment(Fragment fragment) {
		TestActivity testActivity = startActivity(TestActivity.class);
		testActivity
				.getSupportFragmentManager()
				.beginTransaction()
				.add(android.R.id.content, fragment)
				.commit();
		instrumentation.waitForIdleSync();
	}
}
