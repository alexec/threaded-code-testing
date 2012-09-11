package com.alexecollins.threadedcodetesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;

/**
 * @author alex.e.c
 */
public class ObservableFooTest implements Observer {

	private ObservableFoo sut;
	private CountDownLatch updateLatch; // used to react to event

	@Before
	public void setUp() throws Exception {
		updateLatch = new CountDownLatch(1);
		sut = new ObservableFoo();
		sut.addObserver(this);
		sut.start();
	}

	@Override
	public void update(final Observable o, final Object arg) {
		assert o == sut;
		updateLatch.countDown();
	}

	@After
	public void tearDown() throws Exception {
		sut.deleteObserver(this);
		sut.stop();
	}

	@Test(timeout = 100) // in case we never get a notification
	public void testGivenNewFooWhenIncrThenGetOne() throws Exception {
		sut.incr();
		updateLatch.await();
		assertEquals("foo", 1, sut.get());
	}
}
