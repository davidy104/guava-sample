package nz.co.dav.guava.sample.eventbus.async;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;

import nz.co.dav.guava.sample.eventbus.EventBusTestBase;
import nz.co.dav.guava.sample.eventbus.subscriber.SlowProcessSubscriber;
import nz.co.dav.guava.sample.eventbus.subscriber.SlowProcessSubscriber1;

import org.junit.Before;
import org.junit.Test;

import com.google.common.eventbus.AsyncEventBus;

public class AsyncEventBusTest extends EventBusTestBase {
	private AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newCachedThreadPool());
	private int numberLongEvents = 10;
	private CountDownLatch doneSignal = new CountDownLatch(numberLongEvents);
	private SlowProcessSubscriber slowProcessSubscriber;
	private SlowProcessSubscriber1 slowProcessSubscriber1;

	@Before
	public void setUp() {
//		slowProcessSubscriber = new SlowProcessSubscriber(asyncEventBus, doneSignal);
		slowProcessSubscriber1 = new SlowProcessSubscriber1(asyncEventBus);
	}
	
	@Test
	public void testAsyncEventSubscriber1()throws Exception {
		for (int i = 0; i < numberLongEvents; i++) {
			asyncEventBus.post(buyEventBuilder().build());
		}
		System.out.println("main process go ahead");
		Thread.sleep(30000);
	}

	/**
	 * Handler for BuyEvent has @AllowConcurrentEvents and each invocation of
	 * the handler takes 300 MS, but done in parallel s only takes approximately
	 * 300 MS to run
	 */
	@Test
	public void testAsyncEventSubscriber() throws Exception {
		long start = System.currentTimeMillis();
		for (int i = 0; i < numberLongEvents; i++) {
			asyncEventBus.post(buyEventBuilder().build());
		}
		doneSignal.await();
		long elapsed = System.currentTimeMillis() - start;
		assertTrue(elapsed >= 300l && elapsed < 500l);
	}

	/**
	 * Handler for SellEvent does not @AllowConcurrentEvents and each invocation
	 * of the handler takes 300ms, even though using AsyncEventBus it takes a
	 * full 3 seconds to run, so all calls are serial!
	 */
	@Test
	public void testNonAsyncEventSubscriber() throws Exception {

		long start = System.currentTimeMillis();
		for (int i = 0; i < numberLongEvents; i++) {
			asyncEventBus.post(sellEventBuilder().build());
		}
		doneSignal.await();
		long elapsed = System.currentTimeMillis() - start;
		assertTrue(elapsed >= 3000l && elapsed < 5000l);
	}
}
