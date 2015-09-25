package com.wx.doctor.api;

/**
 * Represent a task that can be executed. Often used to run code in a worker
 * thread.
 * 
 * 
 */
public interface ITask extends Runnable {
	/**
	 * Indicates the current status of the task. Each status will be set only
	 * once during the lifetime of a task.
	 */
	public interface Status {
		/**
		 * Indicates that the task has not been executed yet.
		 */
		public final static int PENDING = 1;
		/**
		 * Indicates that the task is running.
		 */
		public final static int RUNNING = 2;
		/**
		 * Indicates that the task has finished.
		 */
		public final static int FINISHED = 3;
	}

	/**
	 * Return the current status of the task.
	 * 
	 * @return
	 */
	int getStatus();

	/**
	 * Cancel the current task.
	 */
	void cancel();

	/**
	 * Returns true if this task has been canceled.
	 */
	boolean isCanceled();

	/**
	 * Set the priority of thread.
	 * 
	 * @param priority
	 */
	void setThreadPriority(int priority);

	/**
	 * Override this method to perform a computation on a background thread
	 */
	@Override
	public void run();

}
