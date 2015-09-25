package com.common.http;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;

public class NetworkControl {

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectivityManager.getActiveNetworkInfo();
		return info != null;
	}

	public static NetworkType getNetworkType(Context context) {
		NetworkType netType = new NetworkType();

		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = connectivityManager.getActiveNetworkInfo();
		if (info == null || !info.isAvailable())
			return netType;
		String type = info.getTypeName();
		if (type.equalsIgnoreCase("WIFI")) {
			netType.setType(NetworkType.NET_TYPE_WIFI);
		} else if (type.equalsIgnoreCase("MOBILE")) {
			// GPRS
			netType.setType(NetworkType.NET_TYPE_MOBILE_NET);
			String proxyHost = android.net.Proxy.getDefaultHost();
			if (!TextUtils.isEmpty(proxyHost)) {
				// WAP
				netType.setType(NetworkType.NET_TYPE_MOBILE_WAP);
				netType.setProxy(proxyHost);
				netType.setPort(android.net.Proxy.getDefaultPort());
			}
		}
		return netType;
	}
	
}
