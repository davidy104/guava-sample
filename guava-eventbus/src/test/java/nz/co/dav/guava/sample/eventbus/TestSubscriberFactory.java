package nz.co.dav.guava.sample.eventbus;

public class TestSubscriberFactory {
	private static TestSubscriber testSubscriber = new TestSubscriber();

	public static TestSubscriber getTestSubscriber() {
		return testSubscriber;
	}
}
