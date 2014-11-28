package nz.co.dav.guava.sample.eventbus.subscriber;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import nz.co.dav.guava.sample.eventbus.EventBusTestBase;
import nz.co.dav.guava.sample.eventbus.events.TradeAccountEvent;
import nz.co.dav.guava.sample.eventbus.events.TradeType;
import nz.co.dav.guava.sample.eventbus.subscriber.simple.SimpleTradeAuditor;

import org.junit.Before;
import org.junit.Test;

import com.google.common.eventbus.EventBus;

public class SimpleTradeAuditorTest extends EventBusTestBase {

	private SimpleTradeAuditor auditor;
	private EventBus eventBus;
	private TradeAccountEvent.Builder builder;

	@Before
	public void setUp() {
		eventBus = getEventBus();
		auditor = new SimpleTradeAuditor(eventBus);
		builder = getTradeAccountEventBuilder();
	}

	@Test
	public void testSubscribeBuy() throws Exception {
		TradeAccountEvent tradeAccountEvent = builder.tradeType(TradeType.BUY).amount(5000.89).build();
		eventBus.post(tradeAccountEvent);
		List<TradeAccountEvent> events = auditor.getTradeEvents();
		assertThat(events.get(0).getTradeType(), is(TradeType.BUY));
		assertThat(events.get(0).getAmount(), is(5000.89));
	}

	@Test
	public void testSubscribeSell() throws Exception {
		TradeAccountEvent tradeAccountEvent = builder.tradeType(TradeType.SELL).amount(5000.89).build();
		eventBus.post(tradeAccountEvent);
		List<TradeAccountEvent> events = auditor.getTradeEvents();
		assertThat(events.get(0).getTradeType(), is(TradeType.SELL));
		assertThat(events.get(0).getAmount(), is(5000.89));
	}

	@Test
	public void testSubscribeBuySell() throws Exception {
		TradeAccountEvent tradeAccountEvent = builder.tradeType(TradeType.SELL).amount(5000.89).build();
		eventBus.post(tradeAccountEvent);
		tradeAccountEvent = builder.tradeType(TradeType.BUY).amount(80000).build();
		eventBus.post(tradeAccountEvent);
		List<TradeAccountEvent> events = auditor.getTradeEvents();

		assertThat(events.get(0).getTradeType(), is(TradeType.SELL));
		assertThat(events.get(0).getAmount(), is(5000.89));

		assertThat(events.get(1).getTradeType(), is(TradeType.BUY));
		assertThat(events.get(1).getAmount(), is(80000D));
	}

}
