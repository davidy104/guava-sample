package nz.co.dav.guava.sample.eventbus.subscriber.simple;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import nz.co.dav.guava.sample.eventbus.events.TradeAccountEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

@Component
public class SimpleTradeAuditor {

	private List<TradeAccountEvent> tradeEvents = Lists.newArrayList();

	@Autowired
	public SimpleTradeAuditor(EventBus eventBus) {
		checkNotNull(eventBus, "EventBus can't be null");
		eventBus.register(this);
	}

	@Subscribe
	public void auditTrade(TradeAccountEvent tradeAccountEvent) {
		tradeEvents.add(tradeAccountEvent);
		System.out.println("Received trade " + tradeAccountEvent);
	}

	public List<TradeAccountEvent> getTradeEvents() {
		return tradeEvents;
	}
}
