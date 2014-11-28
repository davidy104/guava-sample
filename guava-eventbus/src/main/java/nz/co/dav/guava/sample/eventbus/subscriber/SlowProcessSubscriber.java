package nz.co.dav.guava.sample.eventbus.subscriber;

import java.util.concurrent.CountDownLatch;

import nz.co.dav.guava.sample.eventbus.events.BuyEvent;
import nz.co.dav.guava.sample.eventbus.events.SellEvent;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;

public class SlowProcessSubscriber {

	private CountDownLatch doneSignal;

	public SlowProcessSubscriber(AsyncEventBus asyncEventBus, CountDownLatch doneSignal) {
		asyncEventBus.register(this);
		this.doneSignal = doneSignal;
	}

	@Subscribe
	@AllowConcurrentEvents
	public void handleEventConcurrent(BuyEvent event) {
		pause(300l);
		doneSignal.countDown();
	}

	@Subscribe
	public void handleEventNonConcurrent(SellEvent event) {
		pause(300l);
		doneSignal.countDown();
	}

	private void pause(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// Ignore
		}
	}
}
