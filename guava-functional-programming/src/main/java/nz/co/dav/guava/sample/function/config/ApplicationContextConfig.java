package nz.co.dav.guava.sample.function.config;

import java.util.List;
import java.util.Map;

import nz.co.dav.guava.sample.function.function.BookListConverter;
import nz.co.dav.guava.sample.function.predicate.LowRainfallPredicate;
import nz.co.dav.guava.sample.function.predicate.SmallPopulationPredicate;
import nz.co.dav.guava.sample.function.supplier.BookListSupplier;
import nz.co.dav.guava.sample.model.Book;
import nz.co.dav.guava.sample.model.City;
import nz.co.dav.guava.sample.service.BookServiceImpl;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Supplier;

@Configuration
@ComponentScan(basePackages = { "nz.co.dav.guava.sample.function.depinjection" })
public class ApplicationContextConfig {
	@Bean(autowire = Autowire.BY_NAME, name = "lowRainfall")
	public Predicate<City> lowRainFallPredicate() {
		return new LowRainfallPredicate();
	}

	@Bean(autowire = Autowire.BY_NAME, name = "smallPopulation")
	public Predicate<City> smallPopulationPredicate() {
		return new SmallPopulationPredicate();
	}

	@Bean
	public Supplier<List<Book>> bookListSupplier() {
		return new BookListSupplier(new BookServiceImpl());
	}

	@Bean
	Function<List<Book>, Map<String, String>> bookListConverter() {
		return new BookListConverter();
	}

}
