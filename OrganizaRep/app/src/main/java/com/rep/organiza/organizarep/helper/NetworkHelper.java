package com.rep.organiza.organizarep.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.Serializable;

public class NetworkHelper implements Serializable {

    public static final int STATUS_INTERNET_WIFI = 0;
    public static final int STATUS_INTERNET_MOBILE = 1;
    public static final int STATUS_INTERNET_OFFLINE = 2;

    public static boolean isOnline(Context context) {
        if (context == null) {
            return false;
        }

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static int getNetworkCode(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if (wifi.isConnectedOrConnecting()) {
            return STATUS_INTERNET_WIFI;
        } else if (mobile.isConnectedOrConnecting()) {
            return STATUS_INTERNET_MOBILE;
        } else {
            return STATUS_INTERNET_OFFLINE;
        }
    }

}
