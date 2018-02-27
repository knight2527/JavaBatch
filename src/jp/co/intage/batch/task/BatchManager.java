package jp.co.intage.batch.task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BatchManager {

	private static ExecutorService executorService;
	
	private static final int MAX_THEAD_POOL_NUM = 5;

	static {
		executorService = Executors.newFixedThreadPool(MAX_THEAD_POOL_NUM);
	}
	
	public static void addTask(Runnable runable) {
		executorService.execute(runable);
	}

	public static ExecutorService getService() {
		return executorService;
	}
	
	public static boolean isShutdown() {
		return executorService.isShutdown();
	}
	
	public static void restart() {
		executorService = Executors.newFixedThreadPool(MAX_THEAD_POOL_NUM);
	}
	
	public static void shutdown() {
		executorService.shutdown();
	}
}
