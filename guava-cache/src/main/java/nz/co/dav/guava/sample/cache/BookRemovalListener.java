package nz.co.dav.guava.sample.cache;

import nz.co.dav.guava.sample.model.Book;

import com.google.common.cache.RemovalNotification;

public class BookRemovalListener extends BaseRemovalListener<String, Book> {

	@Override
	public void onRemoval(RemovalNotification<String, Book> notification) {
		this.removalCause = notification.getCause();
		this.removedKey = notification.getKey();
		this.removedValue = notification.getValue();
	}
}
