package com.alexecollins.threadedcodetesting;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 *
 *
 * @author alex.e.c
 */
public class Foo {
	private final AtomicLong foo = new AtomicLong();
	private ExecutorService executorService;

	public void start() {
		executorService = Executors.newSingleThreadExecutor();
	}

	public void incr() {
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				foo.incrementAndGet();
			}
		});
	}

	public void stop() {
		executorService.shutdown();
	}

	public long get() {
		return foo.get();
	}
}
