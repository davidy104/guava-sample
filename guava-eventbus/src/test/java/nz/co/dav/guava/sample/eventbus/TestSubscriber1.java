package nz.co.dav.guava.sample.eventbus;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;

public class TestSubscriber1 {

	private List<TestEvent1> testEvents = Lists.newArrayList();

	private String processedMessasge;

	public TestSubscriber1() {
		super();
		TestEventBusFactory.getEventBus().register(this);
	}

	@Subscribe
	public void auditTestEvent(TestEvent1 testEvent) {
		testEvents.add(testEvent);
		processedMessasge = "event1 message, processed: " + testEvent.getMessage();
	}

	public List<TestEvent1> getTestEvents() {
		return testEvents;
	}

	public String getProcessedMessasge() {
		return processedMessasge;
	}

}
