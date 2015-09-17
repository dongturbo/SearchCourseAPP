package com.example.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;


public class CheckedWifi {
	public static boolean CheckNetworkState(Context context) {
		ConnectivityManager connManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		State mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
				.getState();
		if (mWifi == State.CONNECTED)
			return true;
		else
			return false;
	}
}
