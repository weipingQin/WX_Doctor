package com.wx.doctor.api;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;


/**
 * A task often needed to update UI. thread.
 * 
 * 
 */
public abstract class UITask extends SilentTask {
	private static final String TAG = "UITask";

	private static final int MESSAGE_FINISH = 0x1;
	private final Handler mUIHandler;

	public UITask() {
		this.mThreadPriority = android.os.Process.THREAD_PRIORITY_DISPLAY;
		this.mUIHandler = new InternalHandler(Looper.getMainLooper());
	}

	@Override
	public void run() {
		android.os.Process.setThreadPriority(this.mThreadPriority);
		this.mStatus = Status.RUNNING;

		if (!this.mCanceled) {
			try {
				this.runInBackground();
			} catch (Exception e) {
				// for running safely
				//Logger.i(TAG, e + "");
			}
			try {
				this.deliverUIThread();
			} catch (Exception e) {
				//Logger.i(TAG, e + "");
			}
		}
	}

	/**
	 * Override this method to handle result from a worker thread.
	 * 
	 * @return
	 */
	@Override
	public abstract void runInBackground();

	/**
	 * Override this method to handle result from the UI thread.
	 * 
	 * @param result
	 */
	public abstract void runInUIThread();

	private void finish() {
		this.runInUIThread();
		this.mStatus = Status.FINISHED;
	}

	private void deliverUIThread() {
		Message message = this.mUIHandler.obtainMessage(MESSAGE_FINISH);
		message.sendToTarget();
	}

	private class InternalHandler extends Handler {
		private InternalHandler(Looper looper) {
			super(looper);
		}

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MESSAGE_FINISH:
				UITask.this.finish();
				break;
			}
		}
	}

}
