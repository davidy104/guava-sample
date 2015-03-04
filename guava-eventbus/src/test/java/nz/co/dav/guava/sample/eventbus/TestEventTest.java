package nz.co.dav.guava.sample.eventbus;

import java.util.List;

import org.junit.Test;

public class TestEventTest {

	@Test
	public void test() {
		TestPublisher publisher = new TestPublisher();
		publisher.execute("this is test message");
		
		publisher.execute1("this is test message for event1");

		TestSubscriber testSubscriber = TestSubscriberFactory.getTestSubscriber();
		TestSubscriber1 testSubscriber1 = TestSubscriber1Factory.getTestSubscriber();
		
		String processedMessage = testSubscriber.getProcessedMessasge();
		System.out.println("processedMessage:{} " + processedMessage);
		List<TestEvent> testEvents = testSubscriber.getTestEvents();
		for (TestEvent testEvent : testEvents) {
			System.out.println("testEvent:{} " + testEvent);
		}
		
		processedMessage = testSubscriber1.getProcessedMessasge();
		System.out.println("processedMessage:{} " + processedMessage);
		List<TestEvent1> test1Events = testSubscriber1.getTestEvents();
		for (TestEvent1 testEvent : test1Events) {
			System.out.println("testEvent1:{} " + testEvent);
		}
	}

}
