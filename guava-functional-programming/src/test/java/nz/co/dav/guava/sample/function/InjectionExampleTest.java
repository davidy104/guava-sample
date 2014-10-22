package nz.co.dav.guava.sample.function;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;

import nz.co.dav.guava.sample.function.config.ApplicationContextConfig;
import nz.co.dav.guava.sample.function.depinjection.InjectionExample;
import nz.co.dav.guava.sample.model.Book;
import nz.co.dav.guava.sample.model.City;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.common.collect.Lists;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationContextConfig.class)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class InjectionExampleTest {

	@Autowired
	private InjectionExample example;
	private static List<City> cities;
	private static City city = new City.Builder().averageRainfall(55.9).population(400000).build();
	private static City city2 = new City.Builder().averageRainfall(24.3).population(1000000).build();

	@BeforeClass
	public static void setUp() throws Exception {
		cities = Lists.newArrayList(city, city2);
	}

	@Test
	public void testConvertBooks() throws Exception {
		Book b = new Book.Builder().isbn("1234567").title("book1").publisher("acme").price(59.99).build();
		Book b2 = new Book.Builder().isbn("23456878").title("book2").publisher("acme").price(39.99).build();
		List<Book> books = Lists.newArrayList(b, b2);
		Map<String, String> bookMap = example.convertBooks(books);
		String expectedValue = "book1|acme|59.99";
		assertThat(bookMap.get("1234567"), is(expectedValue));
	}

	@Test
	public void testGetListOfBooks() throws Exception {
		List<Book> books = example.getListOfBooks();
		assertThat(books.size(), is(5));
	}

	@Test
	public void testFilterByPopulation() throws Exception {
		List<City> cityList = example.filterByPopulation(cities);
		assertThat(cityList.size(), is(1));
		assertThat(cityList.get(0), is(city));
	}

	@Test
	public void testFilterByRainfall() throws Exception {
		List<City> cityList = example.filterByRainfall(cities);
		assertThat(cityList.size(), is(1));
		assertThat(cityList.get(0), is(city2));
	}
}
