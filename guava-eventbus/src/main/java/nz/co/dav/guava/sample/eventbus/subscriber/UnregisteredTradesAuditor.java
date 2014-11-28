package nz.co.dav.guava.sample.eventbus.subscriber;

import java.util.List;

import nz.co.dav.guava.sample.eventbus.events.BuyEvent;
import nz.co.dav.guava.sample.eventbus.events.SellEvent;

import com.google.common.collect.Lists;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class UnregisteredTradesAuditor {

	private List<BuyEvent> buyEvents = Lists.newArrayList();
	private List<SellEvent> sellEvents = Lists.newArrayList();
	private EventBus eventBus;

	public UnregisteredTradesAuditor(EventBus eventBus) {
		this.eventBus = eventBus;
		this.eventBus.register(this);
	}

	public void unregister() {
		this.eventBus.unregister(this);
	}

	@Subscribe
	public void auditSell(SellEvent sellEvent) {
		sellEvents.add(sellEvent);
		System.out.println("Received TradeSellEvent " + sellEvent);
	}

	@Subscribe
	public void auditBuy(BuyEvent buyEvent) {
		buyEvents.add(buyEvent);
		System.out.println("Received TradeBuyEvent " + buyEvent);
	}

	public List<BuyEvent> getBuyEvents() {
		return buyEvents;
	}

	public List<SellEvent> getSellEvents() {
		return sellEvents;
	}
}
