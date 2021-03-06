package nz.co.dav.guava.sample.thread.async;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import nz.co.dav.guava.sample.SearchingTestBase;
import nz.co.dav.guava.sample.support.model.Person;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;

public class AsyncFunctionTest extends SearchingTestBase {

	private int numberTasks;
	private CountDownLatch startSignal;
	private CountDownLatch doneSignal;
	private ListeningExecutorService executorService;

	@Before
	public void setUp() throws Exception {
		numberTasks = 5;
		startSignal = new CountDownLatch(1);
		doneSignal = new CountDownLatch(numberTasks);
		executorService = MoreExecutors.listeningDecorator(Executors.newCachedThreadPool());
	}

	@After
	public void tearDown() {
		executorService.shutdownNow();
	}

	@Test
	public void testAsyncTransform() throws Exception {
		AsyncFunction<List<String>, List<Person>> queryFunction = new AsyncFunction<List<String>, List<Person>>() {
			@Override
			public ListenableFuture<List<Person>> apply(final List<String> ids) {
				return dbService.getPersonsByIdAsync(ids);
			}
		};

		ListenableFuture<List<String>> indexSearch = luceneSearcher.searchAsync("firstName:martin");

		ListenableFuture<List<Person>> results = Futures.transform(indexSearch, queryFunction, executorService);
		List<Person> persons = results.get(1, TimeUnit.SECONDS);
		assertThat(persons.size(), is(74));
		for (Person person : persons) {
			assertThat(person.firstName, is("Martin"));
		}
	}

	@Test
	public void testTransformSearch() throws Exception {
		Function<List<String>, List<Person>> transformSearchResults = new Function<List<String>, List<Person>>() {
			@Override
			public List<Person> apply(List<String> ids) {
				return dbService.getPersonsById(ids);
			}
		};

		ListenableFuture<List<String>> indexSearch = luceneSearcher.searchAsync("firstName:martin");
		ListenableFuture<List<Person>> transformedResults = Futures.transform(indexSearch, transformSearchResults, executorService);

		List<Person> persons = transformedResults.get(1, TimeUnit.SECONDS);
		int expectedSize = 74;
		assertThat(persons.size(), is(expectedSize));
		for (Person person : persons) {
			assertThat(person.firstName, is("Martin"));
		}
	}

}
