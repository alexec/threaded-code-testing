package com.alexecollins.threadedcodetesting;

import java.util.Observable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author alex.e.c
 */
public class ObservableFooRunnable extends Observable implements Runnable {
	private final Object ready = new Object();
	private volatile boolean cancelled;
	private final AtomicLong foo = new AtomicLong();

	@Override
	public void run() {
		try {
			synchronized (ready) {
				while (!cancelled) {
					ready.wait();
					if (cancelled) {break;}
					foo.incrementAndGet();
					setChanged();
					notifyObservers();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace(); // bad practise generally, but good enough for this example
		}
	}

	public void incr() {
		synchronized (ready) {
			ready.notifyAll();
		}
	}

	public long get() {
		return foo.get();
	}

	public void cancel() throws InterruptedException {
		cancelled = true;
		synchronized (ready) {
			ready.notifyAll();
		}
	}
}
