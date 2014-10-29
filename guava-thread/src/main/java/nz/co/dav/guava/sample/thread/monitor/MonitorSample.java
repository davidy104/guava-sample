package nz.co.dav.guava.sample.thread.monitor;

import java.util.ArrayList;
import java.util.List;

import com.google.common.util.concurrent.Monitor;

public class MonitorSample {
    private List<String> list = new ArrayList<String>();
    private static final int MAX_SIZE = 10;

    private Monitor monitor = new Monitor();
    private Monitor.Guard listBelowCapacity = new Monitor.Guard(monitor) {
        @Override
        public boolean isSatisfied() {
            return (list.size() < MAX_SIZE);
        }
    };

    public void addToList(String item) throws InterruptedException {
        monitor.enterWhen(listBelowCapacity);
        try {
            list.add(item);
        } finally {
            monitor.leave();
        }
    }
}
