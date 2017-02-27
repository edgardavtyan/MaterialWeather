package com.davtyan.materialweather.utils;

import android.content.Context;
import android.net.ConnectivityManager;

public class NetworkInfo {
    private final ConnectivityManager connectivityManager;

    public NetworkInfo(Context context) {
        connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    public boolean isConnectedToNetwork() {
        android.net.NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null &&
               networkInfo.isConnectedOrConnecting();
    }
}
