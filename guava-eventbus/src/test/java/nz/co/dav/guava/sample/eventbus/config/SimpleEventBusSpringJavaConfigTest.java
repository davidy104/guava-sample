package nz.co.dav.guava.sample.eventbus.config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import nz.co.dav.guava.sample.eventbus.events.TradeAccountEvent;
import nz.co.dav.guava.sample.eventbus.events.TradeType;
import nz.co.dav.guava.sample.eventbus.publisher.simple.SimpleTradeExecutor;
import nz.co.dav.guava.sample.eventbus.subscriber.simple.SimpleTradeAuditor;
import nz.co.dav.guava.sample.model.TradeAccount;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SimpleEventBusSpringJavaConfigTest {

    SimpleTradeExecutor tradeExecutor;
    SimpleTradeAuditor  tradeAuditor;

    @Before
    public void setUp(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(SimpleEventBusConfig.class);
         tradeExecutor = ctx.getBean(SimpleTradeExecutor.class);
         tradeAuditor = ctx.getBean(SimpleTradeAuditor.class);
    }

    @Test
    public void testSendMessage(){
        TradeAccount tradeAccount = new TradeAccount.Builder().build();
        tradeExecutor.executeTrade(tradeAccount,5000.65, TradeType.BUY);
        List<TradeAccountEvent> tradeAccountEvents = tradeAuditor.getTradeEvents();
        assertThat(tradeAccountEvents.get(0).getTradeAccount(),is(tradeAccount));
    }
}
