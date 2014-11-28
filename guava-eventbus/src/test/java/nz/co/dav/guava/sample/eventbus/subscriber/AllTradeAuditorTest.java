package nz.co.dav.guava.sample.eventbus.subscriber;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import nz.co.dav.guava.sample.eventbus.EventBusTestBase;

import org.junit.Before;
import org.junit.Test;

import com.google.common.eventbus.EventBus;

public class AllTradeAuditorTest extends EventBusTestBase  {

    private AllTradesAuditor allTradesAuditor;
    private EventBus eventBus;

    @Before
    public void setUp(){
        eventBus = getEventBus();
        allTradesAuditor = new AllTradesAuditor(eventBus);
    }

    @Test
    public void sellOnlyTest(){
        eventBus.post(sellEventBuilder().build());
        assertThat(allTradesAuditor.getSellEvents().size(),is(1));
        assertThat(allTradesAuditor.getBuyEvents().isEmpty(),is(true));
    }

    @Test
    public void buyOnlyTest(){
        eventBus.post(buyEventBuilder().build());
        assertThat(allTradesAuditor.getSellEvents().isEmpty(),is(true));
        assertThat(allTradesAuditor.getBuyEvents().size(),is(1));
    }

    @Test
    public void buySellEventsSameTimeTest(){
        eventBus.post(buyEventBuilder().build());
        eventBus.post(sellEventBuilder().build());
        assertThat(allTradesAuditor.getBuyEvents().size(),is(1));
        assertThat(allTradesAuditor.getSellEvents().size(),is(1));
    }
}
