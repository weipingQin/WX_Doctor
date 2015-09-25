package com.common.image;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;

public class WebImageCache {
	private static final String DISK_CACHE_PATH = "/web_image_cache/";

	private static LruCache<String, Bitmap> memoryCache;
	private String diskCachePath;
	private boolean diskCacheEnabled = false;
	private ExecutorService writeThread;

	public WebImageCache(Context context) {
		final int memClass = ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getMemoryClass();

		final int cacheSize = 1024 * 1024 * memClass / 8;

		if (memoryCache == null) {
			memoryCache = new LruCache<String, Bitmap>(cacheSize) {
				protected int sizeOf(String key, Bitmap bitmap) {
					// The cache size will be measured in bytes rather than
					// number of items.
					return bitmap.getRowBytes() * bitmap.getHeight();
				}
			};
		}

		// Set up disk cache store
		Context appContext = context.getApplicationContext();
		diskCachePath = appContext.getCacheDir().getAbsolutePath() + DISK_CACHE_PATH;
		File outFile = new File(diskCachePath);
		outFile.mkdirs();

		diskCacheEnabled = outFile.exists();

		// Set up threadpool for image fetching tasks
		writeThread = Executors.newSingleThreadExecutor();
	}

	public Bitmap get(final String url) {
		Bitmap bitmap = null;

		// Check for image in memory
		bitmap = getBitmapFromMemory(url);

		// Check for image on disk cache
		if (bitmap == null) {
			bitmap = getBitmapFromDisk(url);

			// Write bitmap back into memory cache
			if (bitmap != null) {
				cacheBitmapToMemory(url, bitmap);
			}
		}

		return bitmap;
	}

	public void put(String url, Bitmap bitmap) {
		cacheBitmapToMemory(url, bitmap);
		cacheBitmapToDisk(url, bitmap);
	}

	public void remove(String url) {
		if (url == null) {
			return;
		}

		// Remove from memory cache
		memoryCache.remove(getCacheKey(url));

		// Remove from file cache
		File f = new File(diskCachePath, url);
		if (f.exists() && f.isFile()) {
			f.delete();
		}
	}

	public void clear() {
		// Remove everything from memory cache
		memoryCache.evictAll();
		clearCacheOnDisk();
	}

	public void clearCacheOnDisk() {
		// Remove everything from file cache
		File cachedFileDir = new File(diskCachePath);
		if (cachedFileDir.exists() && cachedFileDir.isDirectory()) {
			File[] cachedFiles = cachedFileDir.listFiles();
			for (File f : cachedFiles) {
				if (f.exists() && f.isFile()) {
					f.delete();
				}
			}
		}
	}

	private void cacheBitmapToMemory(final String url, final Bitmap bitmap) {
		if (getBitmapFromMemory(url) == null)
			memoryCache.put(getCacheKey(url), bitmap);
	}

	private void cacheBitmapToDisk(final String url, final Bitmap bitmap) {
		writeThread.execute(new Runnable() {
			@Override
			public void run() {
				if (diskCacheEnabled) {
					BufferedOutputStream ostream = null;
					try {
						ostream = new BufferedOutputStream(new FileOutputStream(new File(diskCachePath, getCacheKey(url))), 2 * 1024);
						bitmap.compress(CompressFormat.PNG, 100, ostream);
					} catch (FileNotFoundException e) {
						e.printStackTrace();
					} finally {
						try {
							if (ostream != null) {
								ostream.flush();
								ostream.close();
							}
						} catch (IOException e) {
						}
					}
				}
			}
		});
	}

	public Bitmap getBitmapFromMemory(String url) {
		return (Bitmap) memoryCache.get(getCacheKey(url));
	}

	private Bitmap getBitmapFromDisk(String url) {
		Bitmap bitmap = null;
		if (diskCacheEnabled) {
			String filePath = getFilePath(url);
			File file = new File(filePath);
			if (file.exists()) {
				try {
					bitmap = BitmapFactory.decodeFile(filePath);
					// bitmap = ImageUtil.getOptimizedBitmapFromFile(filePath);
				} catch (OutOfMemoryError err) {
					err.printStackTrace();
				}
			}
		}
		return bitmap;
	}

	private String getFilePath(String url) {
		return diskCachePath + getCacheKey(url);
	}

	private String getCacheKey(String url) {
		if (url == null) {
			throw new RuntimeException("Null url passed in");
		} else {
			return url.replaceAll("[.:/,%?&=]", "+").replaceAll("[+]+", "+");
		}
	}
}