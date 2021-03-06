package com.alexecollins.threadedcodetesting;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ExecutorService;

import static org.junit.Assert.assertEquals;

/**
 * @author alex.e.c
 */
public class FooTest {

	private Foo sut; // system under test
	private ExecutorService executorService;

	@Before
	public void setUp() throws Exception {
		executorService = new SynchronousExecutorService();
		sut = new Foo(executorService);
		sut.start();
	}

	@After
	public void tearDown() throws Exception {
		sut.stop();
		executorService.shutdown();
	}

	@Test
	public void testGivenFooWhenIncrementGetOne() throws Exception {
		sut.incr();
		assertEquals("foo", 1, sut.get());
	}
}
