package nz.co.dav.guava.sample.eventbus.publisher.complex;

import java.util.Date;

import nz.co.dav.guava.sample.eventbus.events.BuyEvent;
import nz.co.dav.guava.sample.eventbus.events.TradeAccountEvent;
import nz.co.dav.guava.sample.eventbus.events.TradeType;
import nz.co.dav.guava.sample.model.TradeAccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.common.eventbus.EventBus;

@Component
public class BuyTradeExecutor {

    private EventBus eventBus;

    @Autowired
    public BuyTradeExecutor(@Qualifier("buysEventBus")EventBus eventBus) {
        this.eventBus = eventBus;
    }

    public void executeBuyTrade(TradeAccount tradeAccount, double amount) {
        TradeAccountEvent tradeAccountEvent = processTrade(tradeAccount, amount, TradeType.BUY);
        eventBus.post(tradeAccountEvent);
    }

    private TradeAccountEvent processTrade(TradeAccount tradeAccount, double amount, TradeType tradeType) {
        Date executionTime = new Date();
        String message = String.format("Processed buy for %s of amount %n  @ %s", tradeAccount, amount, tradeType, executionTime);
        TradeAccountEvent tradeAccountEvent;
        tradeAccountEvent = new BuyEvent(tradeAccount, amount, executionTime);
        System.out.println(message);
        return tradeAccountEvent;
    }
}
