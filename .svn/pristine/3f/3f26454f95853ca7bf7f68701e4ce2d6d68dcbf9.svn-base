package com.common.image;

import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import android.util.AttributeSet;

public class SmartImageView extends ImageView {
	private static final int LOADING_THREADS = 4;
	private static ExecutorService threadPool = Executors.newFixedThreadPool(LOADING_THREADS);

	private SmartImageTask currentTask;
    private Bitmap mBitmap;

	public SmartImageView(Context context) {
		super(context);
	}

	public SmartImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public SmartImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	// Helpers to set image by URL
	public void setImageUrl(String url) {
		setImage(new WebImage(url));
	}

	public void setImageUrl(String url, final Integer fallbackResource) {
		setImage(new WebImage(url), fallbackResource);
	}

	public void setImageUrl(String url, final Integer fallbackResource, final Integer loadingResource) {
		setImage(new WebImage(url), fallbackResource, loadingResource);
	}

	// Set image using SmartImage object
	public void setImage(final SmartImage image) {
		setImage(image, null, null);
	}

	public void setImage(final SmartImage image, final Integer fallbackResource) {
		setImage(image, fallbackResource, fallbackResource);
	}

	@SuppressWarnings("deprecation")
	public void setImage(final SmartImage image, final Integer fallbackResource, final Integer loadingResource) {
		Bitmap cacheBitmap = null;
		if (image instanceof WebImage) {
			// 倘若内存中已存在改bitmap,则不再设定loading图片.
			cacheBitmap = ((WebImage) image).getBitmapFromMemory(getContext());
		}
		if (cacheBitmap != null) {
			setImageBitmap(cacheBitmap);
            mBitmap = cacheBitmap;
//			setBackgroundDrawable(new BitmapDrawable(cacheBitmap));
		} else {
			// Set a loading resource
			if (loadingResource != null) {
				setImageResource(loadingResource);
//				setBackgroundResource(loadingResource);
			}

			// Cancel any existing tasks for this image view
			if (currentTask != null) {
				currentTask.cancel();
				currentTask = null;
			}

			// Set up the new task
			currentTask = new SmartImageTask(getContext(), image);
			currentTask.setOnCompleteHandler(new SmartImageTask.OnCompleteHandler() {
				@SuppressWarnings("deprecation")
				@Override
				public void onComplete(Bitmap bitmap) {
					if (bitmap != null) {
						setImageBitmap(bitmap);
                        mBitmap = bitmap;
//						setBackgroundDrawable(new BitmapDrawable(bitmap));
					} else {
						// Set fallback resource
						if (fallbackResource != null) {
							setImageResource(fallbackResource);
//							setBackgroundResource(fallbackResource);
						}
					}
				}
			});

			// Run the task in a threadpool
			threadPool.execute(currentTask);
		}
	}

    public void recycle() {
        if (mBitmap != null && !mBitmap.isRecycled()) {
            mBitmap = null;
            System.gc();
        }
    }

	public static void cancelAllTasks() {
		threadPool.shutdownNow();
		threadPool = Executors.newFixedThreadPool(LOADING_THREADS);
	}
}