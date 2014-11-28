package nz.co.dav.guava.sample.eventbus.publisher.simple;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Date;

import nz.co.dav.guava.sample.eventbus.events.TradeAccountEvent;
import nz.co.dav.guava.sample.eventbus.events.TradeType;
import nz.co.dav.guava.sample.model.TradeAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;

@Component
public class SimpleTradeExecutor {

	private EventBus eventBus;

	@Autowired
	public SimpleTradeExecutor(EventBus eventBus) {
		this.eventBus = checkNotNull(eventBus, "EventBus can't be null");
	}

	public void executeTrade(TradeAccount tradeAccount, double amount, TradeType tradeType) {
		TradeAccountEvent tradeAccountEvent = processTrade(tradeAccount, amount, tradeType);
		eventBus.post(tradeAccountEvent);
	}

	protected TradeAccountEvent processTrade(TradeAccount tradeAccount, double amount, TradeType tradeType) {
		Date executionTime = new Date();
		String message = String.format("Processed trade for %s of amount %n type %s @ %s", tradeAccount, amount, tradeType, executionTime);
		TradeAccountEvent tradeAccountEvent = new TradeAccountEvent(tradeAccount, amount, executionTime, tradeType);
		System.out.println(message);
		return tradeAccountEvent;
	}
}
