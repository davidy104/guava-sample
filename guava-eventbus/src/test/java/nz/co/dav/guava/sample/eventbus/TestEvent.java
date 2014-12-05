package nz.co.dav.guava.sample.eventbus;

public class TestEvent {

	private String message;

	public TestEvent(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "TestEvent [message=" + message + "]";
	}

}
