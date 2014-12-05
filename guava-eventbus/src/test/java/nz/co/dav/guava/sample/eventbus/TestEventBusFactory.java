package nz.co.dav.guava.sample.eventbus;

import com.google.common.eventbus.EventBus;

public class TestEventBusFactory {
	private static EventBus eventBus = new EventBus();

	public static EventBus getEventBus() {
		return eventBus;
	}
}
