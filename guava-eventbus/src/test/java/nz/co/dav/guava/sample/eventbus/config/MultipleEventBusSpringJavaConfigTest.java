package nz.co.dav.guava.sample.eventbus.config;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import nz.co.dav.guava.sample.eventbus.EventBusTestBase;
import nz.co.dav.guava.sample.eventbus.events.BuyEvent;
import nz.co.dav.guava.sample.eventbus.events.SellEvent;
import nz.co.dav.guava.sample.eventbus.publisher.complex.BuyTradeExecutor;
import nz.co.dav.guava.sample.eventbus.publisher.complex.SellTradeExecutor;
import nz.co.dav.guava.sample.eventbus.subscriber.complex.TradeBuyAuditor;
import nz.co.dav.guava.sample.eventbus.subscriber.complex.TradeSellAuditor;
import nz.co.dav.guava.sample.model.TradeAccount;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
public class MultipleEventBusSpringJavaConfigTest extends EventBusTestBase {

    BuyTradeExecutor buyTradeExecutor;
    SellTradeExecutor sellTradeExecutor;
    TradeBuyAuditor tradeBuyAuditor;
    TradeSellAuditor tradeSellAuditor;

    @Before
    public void setUp(){
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MultipleEventBusConfig.class);
         buyTradeExecutor = ctx.getBean(BuyTradeExecutor.class);
         sellTradeExecutor = ctx.getBean(SellTradeExecutor.class);
         tradeBuyAuditor = ctx.getBean(TradeBuyAuditor.class);
         tradeSellAuditor = ctx.getBean(TradeSellAuditor.class);
    }

    @Test
    public void testBuyMessage(){
        TradeAccount tradeAccount = new TradeAccount.Builder().build();
        buyTradeExecutor.executeBuyTrade(tradeAccount,5000.65);
        List<BuyEvent> buyEvents = tradeBuyAuditor.getBuyEvents();
        assertThat(buyEvents.get(0).getTradeAccount(),is(tradeAccount));
        assertThat(tradeSellAuditor.getSellEvents().isEmpty(),is(true));
    }

    @Test
    public void testSellMessage(){
        TradeAccount tradeAccount = new TradeAccount.Builder().build();
        sellTradeExecutor.executeSaleTrade(tradeAccount, 5000.65);
        List<SellEvent> sellEvents = tradeSellAuditor.getSellEvents();
        assertThat(sellEvents.get(0).getTradeAccount(),is(tradeAccount));
        assertThat(tradeBuyAuditor.getBuyEvents().isEmpty(),is(true));
    }
}
