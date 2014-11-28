package nz.co.dav.guava.sample.eventbus.subscriber;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import nz.co.dav.guava.sample.eventbus.EventBusTestBase;
import nz.co.dav.guava.sample.eventbus.events.SellEvent;
import nz.co.dav.guava.sample.eventbus.subscriber.simple.SimpleTradeAuditor;

import org.junit.Before;
import org.junit.Test;

import com.google.common.eventbus.EventBus;

public class DeadEventSubscriberTest extends EventBusTestBase{

    private DeadEventSubscriber deadEventSubscriber;
    private SimpleTradeAuditor tradeAuditor;
    private EventBus eventBus;

    @Before
    public void setUp(){
        eventBus = getEventBus();
        //Purposely mis-configured - different EventBus, common error
        tradeAuditor = new SimpleTradeAuditor(getEventBus());
        deadEventSubscriber = new DeadEventSubscriber(eventBus);
    }

    @Test
    public void testPublishNoSubscribers(){
        SellEvent sellEvent = sellEventBuilder().build();
        eventBus.post(sellEvent);
        assertThat(tradeAuditor.getTradeEvents().isEmpty(),is(true));
        assertThat(deadEventSubscriber.getDeadEvents().size(),is(1));
        SellEvent wrappedSellEvent = (SellEvent)deadEventSubscriber.getDeadEvents().get(0).getEvent();
        assertThat(wrappedSellEvent,is(sellEvent));
    }

}
