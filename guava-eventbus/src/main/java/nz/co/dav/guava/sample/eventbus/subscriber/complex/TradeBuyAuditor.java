package nz.co.dav.guava.sample.eventbus.subscriber.complex;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import nz.co.dav.guava.sample.eventbus.events.BuyEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

@Component
public class TradeBuyAuditor {

	private List<BuyEvent> buyEvents = Lists.newArrayList();

	@Autowired
	public TradeBuyAuditor(@Qualifier("buysEventBus") EventBus eventBus) {
		checkArgument(eventBus != null, "EventBus can't be null");
		eventBus.register(this);
	}

	@Subscribe
	public void auditBuy(BuyEvent buyEvent) {
		buyEvents.add(buyEvent);
		System.out.println("Received TradeBuyEvent " + buyEvent);
	}

	public List<BuyEvent> getBuyEvents() {
		return buyEvents;
	}
}
