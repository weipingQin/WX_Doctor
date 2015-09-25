package com.common.image;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

import com.common.http.MyHttpClient;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class WebImage implements SmartImage {

	private static WebImageCache webImageCache;

	private String url;

	public WebImage(String url) {
		this.url = url;
	}

	public Bitmap getBitmapFromCache(Context context) {
		// Don't leak context
		if (webImageCache == null) {
			webImageCache = new WebImageCache(context);
		}

		Bitmap bitmap = null;
		if (url != null) {
			bitmap = webImageCache.get(url);
		}
		return bitmap;
	}

	public Bitmap getBitmapFromMemory(Context context) {
		// Don't leak context
		if (webImageCache == null) {
			webImageCache = new WebImageCache(context);
		}

		Bitmap bitmap = null;
		if (url != null) {
			bitmap = webImageCache.getBitmapFromMemory(url);
		}
		return bitmap;
	}

	public Bitmap getBitmap(Context context) {
		// Try getting bitmap from cache first
		Bitmap bitmap = getBitmapFromCache(context);
		if (bitmap == null && isNetwork(context)) {
			bitmap = getBitmapFromUrl(url);
			if (bitmap != null) {
				webImageCache.put(url, bitmap);
			}
		}
		return bitmap;
	}

	private Bitmap getBitmapFromUrl(String url) {
		Bitmap bitmap = null;

		try {
			HttpClient httpClient = MyHttpClient.getHttpClient();
			HttpRequestBase request = new HttpGet(url);
			HttpResponse httpResponse = httpClient.execute(request);
			bitmap = BitmapFactory.decodeStream(httpResponse.getEntity().getContent());
//			bitmap = ImageUtil.getOptimizedBitmapFromInputStream(httpResponse.getEntity().getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}

		return bitmap;
	}

	public static void removeFromCache(String url) {
		if (webImageCache != null) {
			webImageCache.remove(url);
		}
	}

	public static boolean isNetwork(Context context) {
		ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();
		if (activeNetInfo != null && activeNetInfo.isAvailable()) {
			return true;
		}
		return false;
	}
}