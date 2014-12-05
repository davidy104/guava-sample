package nz.co.dav.guava.sample.eventbus;

import java.util.List;

import com.google.common.collect.Lists;
import com.google.common.eventbus.Subscribe;

public class TestSubscriber {

	private List<TestEvent> testEvents = Lists.newArrayList();

	private String processedMessasge;

	public TestSubscriber() {
		super();
		TestEventBusFactory.getEventBus().register(this);
	}

	@Subscribe
	public void auditTestEvent(TestEvent testEvent) {
		testEvents.add(testEvent);
		processedMessasge = "yeyeyeye, processed: " + testEvent.getMessage();
	}

	public List<TestEvent> getTestEvents() {
		return testEvents;
	}

	public String getProcessedMessasge() {
		return processedMessasge;
	}

}
