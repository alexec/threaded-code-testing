package com.alexecollins.threadedcodetesting;

import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author alex.e.c
 */
public class ObservableFoo extends Observable {
	private final AtomicLong foo = new AtomicLong();
	private ExecutorService executorService;

	public void start() {
		executorService = Executors.newSingleThreadExecutor();
	}

	public void stop() {
		executorService.shutdown();
	}

	public void incr() {
		executorService.submit(new Runnable() {
			@Override
			public void run() {
				foo.incrementAndGet();
				setChanged();
				notifyObservers(); // lazy use of observable
			}
		});
	}

	public long get() {
		return foo.get();
	}
}
