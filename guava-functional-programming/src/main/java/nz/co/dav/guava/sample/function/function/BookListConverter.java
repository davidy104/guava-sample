package nz.co.dav.guava.sample.function.function;

import java.util.List;
import java.util.Map;

import nz.co.dav.guava.sample.model.Book;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Maps;

public class BookListConverter implements Function<List<Book>, Map<String, String>> {

	@Override
	public Map<String, String> apply(List<Book> input) {
		Map<String, String> map = Maps.newHashMap();
		Joiner joiner = Joiner.on("|");
		for (Book book : input) {
			map.put(book.getIsbn(), joiner.join(book.getTitle(), book.getPublisher(), book.getPrice()));
		}
		return map;
	}
}
