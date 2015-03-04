package nz.co.dav.guava.sample.eventbus.subscriber;

import nz.co.dav.guava.sample.eventbus.events.BuyEvent;

import com.google.common.eventbus.AllowConcurrentEvents;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.Subscribe;

public class SlowProcessSubscriber1 {

	public SlowProcessSubscriber1(AsyncEventBus asyncEventBus) {
		asyncEventBus.register(this);
	}

	@Subscribe
	@AllowConcurrentEvents
	public void handleEventConcurrent(BuyEvent event) {
		pause(3000l);
		System.out.println("event:{} " + event);
	}

	private void pause(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// Ignore
		}
	}
}
