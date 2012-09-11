package com.alexecollins.threadedcodetesting;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author alex.e.c
 */
public class SynchronousExecutorService extends AbstractExecutorService {
	private boolean shutdown;

	public void shutdown() {
		shutdown = true;
	}

	public List<Runnable> shutdownNow() {
		shutdown = true;
		return Collections.emptyList();
	}

	public boolean isShutdown() {
		shutdown = true;
		return shutdown;
	}

	public boolean isTerminated() {
		return shutdown;
	}

	public boolean awaitTermination(final long timeout, final TimeUnit unit) throws InterruptedException {
		return true;
	}

	public void execute(final Runnable command) {
		command.run();
	}
}
