package nz.co.dav.guava.sample.function;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import nz.co.dav.guava.sample.function.function.BookListConverter;
import nz.co.dav.guava.sample.function.supplier.BookListSupplier;
import nz.co.dav.guava.sample.model.Book;
import nz.co.dav.guava.sample.service.BookService;

import org.junit.Test;

import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import com.google.common.collect.Lists;

public class SupplierTest {

	 private Supplier<Map<String, String>> bookMapSupplier;
	    private BookService bookService = mock(BookService.class);
	    private BookListSupplier bookListSupplier = new BookListSupplier(bookService);
	    private Function<List<Book>, Map<String, String>> function = new BookListConverter();


	    @Test
	    public void testComposedMemoizeSupplier() throws Exception {
	        Book book = new Book.Builder().author("Pennypacker, HJ").isbn("ISBN-98765432").title("How I Made Millions").publisher("Acme Publishers").price(4999.99).build();
	        when(bookService.get()).thenReturn(Lists.newArrayList(book));
	        Supplier<List<Book>> cachedSupplier = Suppliers.memoize(bookListSupplier);
	        bookMapSupplier = Suppliers.compose(function, cachedSupplier);
	        Map<String, String> bookMap = bookMapSupplier.get();
	        assertThat(bookMap.get("ISBN-98765432"), is("How I Made Millions|Acme Publishers|4999.99"));
	        Thread.sleep(15);

	        bookMap = bookMapSupplier.get();
	        assertThat(bookMap.get("ISBN-98765432"), is("How I Made Millions|Acme Publishers|4999.99"));
	        Thread.sleep(15);

	        bookMap = bookMapSupplier.get();
	        assertThat(bookMap.get("ISBN-98765432"), is("How I Made Millions|Acme Publishers|4999.99"));
	        Thread.sleep(15);

	        bookMap = bookMapSupplier.get();
	        assertThat(bookMap.get("ISBN-98765432"), is("How I Made Millions|Acme Publishers|4999.99"));

	        verify(bookService, times(1)).get();
	    }

	    @Test
	    public void testComposedMemoizeWithExpirationSupplier() throws Exception {
	        Book book = new Book.Builder().author("Pennypacker, HJ").isbn("ISBN-98765432").title("How I Made Millions").publisher("Acme Publishers").price(4999.99).build();
	        when(bookService.get()).thenReturn(Lists.newArrayList(book));
	        Supplier<List<Book>> cachedSupplier = Suppliers.memoizeWithExpiration(bookListSupplier,10L,TimeUnit.MILLISECONDS);
	        bookMapSupplier = Suppliers.compose(function, cachedSupplier);
	        Map<String, String> bookMap = bookMapSupplier.get();
	        assertThat(bookMap.get("ISBN-98765432"), is("How I Made Millions|Acme Publishers|4999.99"));
	        Thread.sleep(15);

	        bookMap = bookMapSupplier.get();
	        assertThat(bookMap.get("ISBN-98765432"), is("How I Made Millions|Acme Publishers|4999.99"));
	        Thread.sleep(15);

	        bookMap = bookMapSupplier.get();
	        assertThat(bookMap.get("ISBN-98765432"), is("How I Made Millions|Acme Publishers|4999.99"));
	        Thread.sleep(15);

	        bookMap = bookMapSupplier.get();
	        assertThat(bookMap.get("ISBN-98765432"), is("How I Made Millions|Acme Publishers|4999.99"));

	        verify(bookService, times(4)).get();
	    }

}
