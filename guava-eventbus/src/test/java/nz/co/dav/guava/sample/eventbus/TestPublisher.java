package nz.co.dav.guava.sample.eventbus;

import com.google.common.eventbus.EventBus;

public class TestPublisher {

	private EventBus eventBus;

	public TestPublisher() {
		super();
		eventBus = TestEventBusFactory.getEventBus();
		TestSubscriberFactory.getTestSubscriber();
		TestSubscriber1Factory.getTestSubscriber();
	}

	public void execute(final String message) {
		eventBus.post(new TestEvent(message));
	}

	public void execute1(final String message) {
		eventBus.post(new TestEvent1(message));
	}
}
