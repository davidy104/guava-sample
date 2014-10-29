package nz.co.dav.guava.sample.thread.ratelimiter;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.junit.Test;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimiterTest {

	@Test
	public void rateLimiterTest() throws Exception {
		final CountDownLatch waitSignal = new CountDownLatch(10);
		final ProtectedResource protectedResource = new ProtectedResource();
		Runnable expensiveThread = new Runnable() {
			@Override
			public void run() {
				protectedResource.expensiveOperation();
				waitSignal.countDown();
			}
		};
		Executor executor = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10; i++) {
			executor.execute(expensiveThread);
		}
		waitSignal.await();
		assertThat(waitSignal.getCount(), is(0L));
	}

	private class ProtectedResource {
		private RateLimiter rateLimiter = RateLimiter.create(2.0);

		public void expensiveOperation() {
			rateLimiter.acquire();
			System.out.println("Expensive operation run by " + Thread.currentThread() + " @ [" + new Date() + "]");
		}

	}
}
