package com.wx.doctor.api;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.wx.doctor.MyApplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;


@SuppressLint("SimpleDateFormat")
public class CommonUtils {
	
	/**
	 * show Toast
	 * 
	 * @param msg
	 *            msg
	 * @param tag
	 *            0:LENGTH_SHORT 1:LENGTH_LONG
	 */
	public static void showT(String msg, int tag) {
		if (tag == 0) {
			Toast.makeText(MyApplication.getmContext(), msg, Toast.LENGTH_SHORT).show();
		} else if (tag == 1) {
			Toast.makeText(MyApplication.getmContext(), msg, Toast.LENGTH_LONG).show();
		}
	}

	/**
	 * show Toast (default short)
	 * 
	 * @param msg
	 *            msg
	 */
	public static void showT(String msg) {
		Toast.makeText(MyApplication.getmContext(), msg, Toast.LENGTH_SHORT).show();
	}

	/**
	 * show Toast (default short)
	 * 
	 * @param resId
	 *            resId
	 */
	public static void showT(int resId) {
		Toast.makeText(MyApplication.getmContext(), resId, Toast.LENGTH_SHORT).show();
	}

	@SuppressWarnings("unchecked")
	public static final <E extends View> E getView(View parent, int id) {
		try {
			return (E) parent.findViewById(id);
		} catch (ClassCastException ex) {
			//Logger.e("Could not cast View to concrete class \n" + ex.getMessage());
			throw ex;
		}
	}

	/**
	 * 判断当前是否有可用的网络以及网络类型 0：无网络 1：WIFI 2：CMWAP 3：CMNET
	 * 
	 * @param context
	 * @return
	 */
	public static int getNetworkState(Context context) {
		ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return 0;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						NetworkInfo netWorkInfo = info[i];
						if (netWorkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
							return 1;
						} else if (netWorkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
							String extraInfo = netWorkInfo.getExtraInfo();
							if ("cmwap".equalsIgnoreCase(extraInfo) || "cmwap:gsm".equalsIgnoreCase(extraInfo)) {
								return 2;
							}
							return 3;
						}
					}
				}
			}
		}
		return 0;
	}

	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager == null) {
			return false;
		}
		if (connectivityManager.getActiveNetworkInfo() == null) {
			return false;
		}
		return connectivityManager.getActiveNetworkInfo().isAvailable();
	}
	
	public static boolean isNetworkAvailable() {
		ConnectivityManager connectivityManager = (ConnectivityManager) MyApplication.getmContext().getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivityManager == null) {
			return false;
		}
		if (connectivityManager.getActiveNetworkInfo() == null) {
			return false;
		}
		return connectivityManager.getActiveNetworkInfo().isAvailable();
	}

	/**
	 * get current time
	 * 
	 * @return format to yyyy-MM-dd HH:mm:ss
	 */
	public static String getStringDate() {
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateString = formatter.format(currentTime);
		return dateString;
	}

	/**
	 * dp to px
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * px to dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	
	/**
	 * 转成MD5值
	 * 
	 * @param str
	 * @return
	 */
	public static String toMD5(String str) {
		byte[] hash;
		try {
			hash = MessageDigest.getInstance("MD5").digest(str.getBytes("UTF-8"));
			StringBuilder hex = new StringBuilder(hash.length * 2);
			for (byte b : hash) {
				if ((b & 0xFF) < 0x10)
					hex.append("0");
				hex.append(Integer.toHexString(b & 0xFF));
			}
			return hex.toString();
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("Huh, MD5 should be supported?", e);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Huh, UTF-8 should be supported?", e);
		}
	}

}
