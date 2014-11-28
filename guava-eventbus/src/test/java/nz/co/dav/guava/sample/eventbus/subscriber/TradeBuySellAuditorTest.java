package nz.co.dav.guava.sample.eventbus.subscriber;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import nz.co.dav.guava.sample.eventbus.EventBusTestBase;
import nz.co.dav.guava.sample.eventbus.subscriber.complex.TradeBuyAuditor;
import nz.co.dav.guava.sample.eventbus.subscriber.complex.TradeSellAuditor;

import org.junit.Before;
import org.junit.Test;

import com.google.common.eventbus.EventBus;

public class TradeBuySellAuditorTest extends EventBusTestBase {

	private TradeBuyAuditor buyAuditor;
	private TradeSellAuditor sellAuditor;
	private EventBus eventBus;

	@Before
	public void setUp() {
		eventBus = getEventBus();
		buyAuditor = new TradeBuyAuditor(eventBus);
		sellAuditor = new TradeSellAuditor(eventBus);
	}

	@Test
	public void sellOnlyTest() {
		eventBus.post(sellEventBuilder().build());
		assertThat(sellAuditor.getSellEvents().size(), is(1));
		assertThat(buyAuditor.getBuyEvents().isEmpty(), is(true));
	}

	@Test
	public void buyOnlyTest() {
		eventBus.post(buyEventBuilder().build());
		assertThat(sellAuditor.getSellEvents().isEmpty(), is(true));
		assertThat(buyAuditor.getBuyEvents().size(), is(1));
	}
}
