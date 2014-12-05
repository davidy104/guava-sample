package nz.co.dav.guava.sample.cache;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import nz.co.dav.guava.sample.model.TradeAccount;
import nz.co.dav.guava.sample.service.TradeAccountService;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.base.Ticker;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheBuilderSpec;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalCause;


public class CacheBuilderSpecTest {
    private static TradeAccountService tradeAccountService;

    @BeforeClass
    public static void startUpBeforeAll() {
        tradeAccountService = new TradeAccountService();
    }

    @AfterClass
    public static void tearDownAfterAll() throws Exception {
        tradeAccountService.shutdown();
    };

    @Test
    public void testCacheBuilderSpec() throws Exception{
        TradeAccountRemovalListener removalListener = new TradeAccountRemovalListener();
        String spec = "concurrencyLevel=10,expireAfterAccess=1s,softValues";
        CacheBuilderSpec cacheBuilderSpec = CacheBuilderSpec.parse(spec);
        CacheBuilder cacheBuilder = CacheBuilder.from(cacheBuilderSpec);
        LoadingCache<String, TradeAccount> tradeAccountCache = cacheBuilder.ticker(Ticker.systemTicker())
            .removalListener(removalListener)
            .build(new CacheLoader<String, TradeAccount>() {
                @Override
                public TradeAccount load(String key) throws Exception {
                    return tradeAccountService.getTradeAccountById(key);
                }
            });

        //112,"Pennypacker, HJ",700889.32
        TradeAccount tradeAccount = tradeAccountCache.get("112");
        assertThat(tradeAccount.getBalance(), is(700889.32));
        Thread.sleep(1500L);
        tradeAccountCache.get("112");
        assertThat(removalListener.getRemovalCause(), is(RemovalCause.EXPIRED));
        assertThat(removalListener.getRemovedValue(), is(tradeAccount));

    }
}
