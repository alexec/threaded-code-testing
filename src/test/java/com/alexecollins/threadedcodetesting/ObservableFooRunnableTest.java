package com.alexecollins.threadedcodetesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.assertEquals;

/**
 * @author alex.collins
 */
public class ObservableFooRunnableTest implements Observer {

	private ObservableFooRunnable sut;
	private CountDownLatch updateLatch;

	@Before
	public void setUp() throws Exception {
		updateLatch = new CountDownLatch(1);
		sut = new ObservableFooRunnable();
		sut.addObserver(this);
		new Thread(sut).start();
	}

	@After
	public void tearDown() throws Exception {
		sut.deleteObserver(this);
		sut.cancel();
	}

	@Test(timeout = 100)
	public void testGivenNewFooWhenIncrThenGetOne() throws Exception {
		sut.incr();
		updateLatch.await();
		assertEquals("foo", 1, sut.get());
	}

	public void update(final Observable o, final Object arg) {
		assert o == sut;
		updateLatch.countDown();
	}
}
