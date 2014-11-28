package nz.co.dav.guava.sample.eventbus.subscriber;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class DeadEventSubscriber {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(DeadEventSubscriber.class);
	private List<DeadEvent> deadEvents = Lists.newArrayList();

	public DeadEventSubscriber(EventBus eventBus) {
		eventBus.register(this);
	}

	@Subscribe
	public void handleUnsubscribedEvent(DeadEvent deadEvent) {
		LOGGER.warn("No subscribers for " + deadEvent.getEvent());
		deadEvents.add(deadEvent);
	}

	public List<DeadEvent> getDeadEvents() {
		return deadEvents;
	}
}
