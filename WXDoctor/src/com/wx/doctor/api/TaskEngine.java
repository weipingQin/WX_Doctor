package com.wx.doctor.api;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


public class TaskEngine implements IEngine {
	private static final String TAG = "TaskEngine";

	private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
	private static final int CORE_POOL_SIZE = CPU_COUNT + 1;
	private static final int MAXIMUM_POOL_SIZE = CPU_COUNT * 2 + 1;
	private static final int KEEP_ALIVE = 1;
	private static final int MAXNUM_TASK = 128;

	private final BlockingQueue<Runnable> mPoolWorkQueue;

	public ExecutorService mThreadPoolExecutor;

	private final ExecutorService mDefaultExecutor;

	private static TaskEngine mSingleton;

	private TaskEngine() {
		this.mPoolWorkQueue = new LinkedBlockingQueue<Runnable>(MAXNUM_TASK);
		this.mThreadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, KEEP_ALIVE, TimeUnit.SECONDS, this.mPoolWorkQueue, sThreadFactory);
		this.mDefaultExecutor = this.mThreadPoolExecutor;

	}

	public static TaskEngine getInstance() {
		if (mSingleton == null) {
			synchronized (TaskEngine.class) {
				if (mSingleton == null) {
					mSingleton = new TaskEngine();
				}
			}
		}

		return mSingleton;
	}

	@Override
	public void submit(ITask task) {
		this.mDefaultExecutor.execute(task);
	}

	@Override
	public void shutdown() {
		if (this.mDefaultExecutor != null) {
			this.mDefaultExecutor.shutdownNow();
		}
	}

	private static final ThreadFactory sThreadFactory = new ThreadFactory() {
		private final AtomicInteger mCount = new AtomicInteger(1);

		@Override
		public Thread newThread(Runnable r) {
			return new Thread(r, TAG + " #" + this.mCount.getAndIncrement());
		}
	};

}
