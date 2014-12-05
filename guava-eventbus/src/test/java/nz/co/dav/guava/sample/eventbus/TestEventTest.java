package nz.co.dav.guava.sample.eventbus;

import java.util.List;

import org.junit.Test;

public class TestEventTest {

	@Test
	public void test() {
		TestPublisher publisher = new TestPublisher();
		publisher.execute("this is test message");

		TestSubscriber testSubscriber = TestSubscriberFactory.getTestSubscriber();
		String processedMessage = testSubscriber.getProcessedMessasge();
		System.out.println("processedMessage:{} " + processedMessage);
		List<TestEvent> testEvents = testSubscriber.getTestEvents();
		for (TestEvent testEvent : testEvents) {
			System.out.println("testEvent:{} " + testEvent);
		}
	}

}
