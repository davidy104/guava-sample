package nz.co.dav.guava.sample.service;

import java.util.List;

import nz.co.dav.guava.sample.model.Book;

public interface BookService {
	List<Book> findBooksByAuthor(String author);

	Book findBookByIsbn(String isbn);

	List<Book> get();
}
