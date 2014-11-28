package nz.co.dav.guava.sample.eventbus.subscriber.complex;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import nz.co.dav.guava.sample.eventbus.events.SellEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

@Component
public class TradeSellAuditor {

	private List<SellEvent> sellEvents = Lists.newArrayList();

	@Autowired
	public TradeSellAuditor(@Qualifier("salesEventBus") EventBus eventBus) {
		checkArgument(eventBus != null, "EventBus can't be null");
		eventBus.register(this);
	}

	@Subscribe
	public void auditSell(SellEvent sellEvent) {
		sellEvents.add(sellEvent);
		System.out.println("Received TradeSellEvent " + sellEvent);
	}

	public List<SellEvent> getSellEvents() {
		return sellEvents;
	}
}
