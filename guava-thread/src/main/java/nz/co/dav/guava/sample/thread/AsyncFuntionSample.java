package nz.co.dav.guava.sample.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.google.common.collect.Maps;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.SettableFuture;

public class AsyncFuntionSample implements AsyncFunction<Long, String> {

	private ConcurrentMap<Long, String> map = Maps.newConcurrentMap();
	private ListeningExecutorService listeningExecutorService;

	public AsyncFuntionSample() {
		map.put(1L, "FOO");
		map.put(2L, "BAR");
		map.put(3L, "BAZ");
		ExecutorService executorService = MoreExecutors.getExitingExecutorService((ThreadPoolExecutor) Executors.newFixedThreadPool(3));
		listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
	}

	@Override
	public ListenableFuture<String> apply(final Long input) throws Exception {
		if (map.containsKey(input)) {
			SettableFuture<String> listenableFuture = SettableFuture.create();
			listenableFuture.set(map.get(input));
			return listenableFuture;
		} else {
			return listeningExecutorService.submit(new Callable<String>() {
				@Override
				public String call() throws Exception {
					String retrieved = "AsyncValue";
					map.putIfAbsent(input, retrieved);
					return retrieved;
				}
			});
		}

	}
}
