package nz.co.dav.guava.sample.eventbus.events;

import java.util.Date;

import nz.co.dav.guava.sample.model.TradeAccount;

public class BuyEvent extends TradeAccountEvent {

    public BuyEvent(TradeAccount tradeAccount, double amount, Date tradExecutionTime) {
        super(tradeAccount, amount, tradExecutionTime, TradeType.BUY);
    }


    public static class Builder {
        private TradeAccount tradeAccount = new TradeAccount.Builder().build();
        private double amount = 500.75;
        private Date executionTime = new Date();


        public Builder tradeAccount(TradeAccount tradeAccount) {
            this.tradeAccount = tradeAccount;
            return this;
        }

        public Builder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder executionTime(Date executionTime) {
            this.executionTime = executionTime;
            return this;
        }

        public BuyEvent build(){
            return new BuyEvent(this.tradeAccount,this.amount,this.executionTime);
        }
    }
}
