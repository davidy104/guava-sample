package nz.co.dav.guava.sample.function.supplier;

import java.util.List;

import nz.co.dav.guava.sample.model.Book;
import nz.co.dav.guava.sample.service.BookService;

import com.google.common.collect.Lists;

class MockBookService implements BookService {

	@Override
	public List<Book> findBooksByAuthor(String author) {
		return Lists.newArrayList(new Book.Builder().author(author).build());
	}

	@Override
	public Book findBookByIsbn(String isbn) {
		return new Book.Builder().isbn(isbn).build();
	}

	@Override
	public List<Book> get() {
		return Lists.newArrayList(new Book.Builder().build());
	}
}
