package nz.co.dav.guava.sample.function.supplier;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import nz.co.dav.guava.sample.model.Book;
import nz.co.dav.guava.sample.service.BookService;

import com.google.common.base.Supplier;

public class BookListSupplier implements Supplier<List<Book>> {

	private BookService bookService;

	public BookListSupplier(BookService bookService) {
		this.bookService = checkNotNull(bookService);
	}

	@Override
	public List<Book> get() {
		return bookService.get();
	}
}
