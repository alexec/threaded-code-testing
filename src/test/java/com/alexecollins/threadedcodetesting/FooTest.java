package com.alexecollins.threadedcodetesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author alex.collins
 */
public class FooTest {

	private Foo sut; // system under test

	@Before
	public void setUp() throws Exception {
		sut = new Foo();
		sut.start();
	}

	@After
	public void tearDown() throws Exception {
		sut.stop();
	}

	@Test
	public void testGivenFooWhenIncrementGetOne() throws Exception {
		sut.incr();
		Thread.sleep(1000); // yuk - a slow test - don't do this
		assertEquals("foo", 1, sut.get());
	}
}
