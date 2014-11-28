package nz.co.dav.guava.sample.eventbus;

import nz.co.dav.guava.sample.eventbus.events.BuyEvent;
import nz.co.dav.guava.sample.eventbus.events.SellEvent;
import nz.co.dav.guava.sample.eventbus.events.TradeAccountEvent;

import com.google.common.eventbus.EventBus;

public abstract class EventBusTestBase {

	protected TradeAccountEvent.Builder getTradeAccountEventBuilder() {
		return new TradeAccountEvent.Builder();
	}

	protected BuyEvent.Builder buyEventBuilder() {
		return new BuyEvent.Builder();
	}

	protected SellEvent.Builder sellEventBuilder() {
		return new SellEvent.Builder();
	}

	protected EventBus getEventBus() {
		return new EventBus();
	}

}
