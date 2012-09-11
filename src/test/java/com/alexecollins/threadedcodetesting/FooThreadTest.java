package com.alexecollins.threadedcodetesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author alex.e.c
 */
public class FooThreadTest {

	private FooThread sut;

	@Before
	public void setUp() throws Exception {
		sut = new FooThread();
	}

	@After
	public void tearDown() throws Exception {
		sut.cancel();
	}

	@Test
	public void testGivenNewFooWhenIncrThenGetOne() throws Exception {
		sut.incr();
		sut.undertakeWork();
		assertEquals("foo", 1, sut.get());
	}
}
