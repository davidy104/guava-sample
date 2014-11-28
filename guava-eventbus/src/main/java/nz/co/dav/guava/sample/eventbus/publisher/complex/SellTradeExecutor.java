package nz.co.dav.guava.sample.eventbus.publisher.complex;

import java.util.Date;

import nz.co.dav.guava.sample.eventbus.events.SellEvent;
import nz.co.dav.guava.sample.eventbus.events.TradeAccountEvent;
import nz.co.dav.guava.sample.eventbus.events.TradeType;
import nz.co.dav.guava.sample.model.TradeAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;

@Component
public class SellTradeExecutor {

	private EventBus eventBus;

	@Autowired
	public SellTradeExecutor(@Qualifier("salesEventBus") EventBus eventBus) {
		this.eventBus = eventBus;
	}

	public void executeSaleTrade(TradeAccount tradeAccount, double amount) {
		TradeAccountEvent tradeAccountEvent = processTrade(tradeAccount, amount, TradeType.SELL);
		eventBus.post(tradeAccountEvent);
	}

	private TradeAccountEvent processTrade(TradeAccount tradeAccount, double amount, TradeType tradeType) {
		Date executionTime = new Date();
		String message = String.format("Processed sale for %s of amount %n  @ %s", tradeAccount, amount, tradeType, executionTime);
		TradeAccountEvent tradeAccountEvent;
		tradeAccountEvent = new SellEvent(tradeAccount, amount, executionTime);
		System.out.println(message);
		return tradeAccountEvent;
	}
}
