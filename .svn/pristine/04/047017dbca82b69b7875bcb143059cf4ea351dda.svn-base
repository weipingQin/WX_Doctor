package com.wx.doctor.api;

/**
 * A task often used to run a background task.
 * 
 * 
 */
public abstract class SilentTask implements ITask {
	private static final String TAG = "SilentTask";

	protected volatile int mThreadPriority;
	protected volatile boolean mCanceled = false;
	protected int mStatus = Status.PENDING;

	public SilentTask() {
		this.mThreadPriority = android.os.Process.THREAD_PRIORITY_BACKGROUND;
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
			//	Logger.i(TAG, e + "");
			}
		}

	}

	/**
	 * Override this method to handle result from a worker thread.
	 * 
	 * @return
	 */
	public abstract void runInBackground();

	@Override
	public int getStatus() {
		return this.mStatus;
	}

	@Override
	public boolean isCanceled() {
		return this.mCanceled;
	}

	@Override
	public final void cancel() {
		this.mCanceled = true;
	}

	@Override
	public void setThreadPriority(int priority) {
		this.mThreadPriority = priority;
	}
}
