package nz.co.dav.guava.sample.eventbus;

public class TestSubscriber1Factory {
	private static TestSubscriber1 testSubscriber = new TestSubscriber1();

	public static TestSubscriber1 getTestSubscriber() {
		return testSubscriber;
	}
}
