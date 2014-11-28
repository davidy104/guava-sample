package nz.co.dav.guava.sample.eventbus.subscriber;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import nz.co.dav.guava.sample.eventbus.EventBusTestBase;

import org.junit.Before;
import org.junit.Test;

import com.google.common.eventbus.EventBus;

public class UnregisteredTradesAuditorTest extends EventBusTestBase {
    private EventBus eventBus;
    private UnregisteredTradesAuditor unregisteredTradesAuditor;


    @Before
    public void setUp() throws Exception {
       eventBus = getEventBus();
       unregisteredTradesAuditor = new UnregisteredTradesAuditor(eventBus);
    }

    @Test
    public void postThenPostAgainUnregistered() {
        eventBus.post(buyEventBuilder().build());
        eventBus.post(sellEventBuilder().build());
        assertThat(unregisteredTradesAuditor.getBuyEvents().size(),is(1));
        assertThat(unregisteredTradesAuditor.getSellEvents().size(),is(1));

        unregisteredTradesAuditor.getBuyEvents().clear();
        unregisteredTradesAuditor.getSellEvents().clear();
        unregisteredTradesAuditor.unregister();

        eventBus.post(buyEventBuilder().build());
        eventBus.post(sellEventBuilder().build());
        assertThat(unregisteredTradesAuditor.getBuyEvents().isEmpty(),is(true));
        assertThat(unregisteredTradesAuditor.getSellEvents().isEmpty(),is(true));


    }
}
