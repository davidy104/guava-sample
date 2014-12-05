package nz.co.dav.guava.sample.cache;

import nz.co.dav.guava.sample.model.TradeAccount;

import com.google.common.cache.RemovalNotification;


public class TradeAccountRemovalListener extends BaseRemovalListener<String, TradeAccount> {


    public void onRemoval(RemovalNotification<String, TradeAccount> notification) {
        this.removalCause = notification.getCause();
        this.removedKey = notification.getKey();
        this.removedValue = notification.getValue();
    }
}
