package com.alexecollins.threadedcodetesting;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author alex.e.c
 */
public class FooThread extends Thread {
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
					undertakeWork();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace(); // bad practise generally, but good enough for this example
		}
	}

	void undertakeWork() {
		foo.incrementAndGet();
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
