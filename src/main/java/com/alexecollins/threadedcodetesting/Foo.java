package com.alexecollins.threadedcodetesting;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author alex.e.c
 */
public class Foo {
	private final AtomicLong foo = new AtomicLong();
	private final ExecutorService executorService;

	public Foo(ExecutorService executorService) {
		this.executorService = executorService;
	}

	public void start() {
		// nop
	}


	public void stop() {
		// nop
	}

	public void incr() {
		executorService.submit(new Runnable() {
			public void run() {
				foo.incrementAndGet();
			}
		});
	}

	public long get() {
		return foo.get();
	}
}
