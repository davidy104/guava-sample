package nz.co.dav.guava.sample.utilities;

import static com.google.common.base.MoreObjects.firstNonNull;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

/**
 * The firstNonNull method takes two arguments and returns the argument that is
 * not null.
 * 
 * @author Davidy
 *
 */
public class ObjectsTest {

	@Test
	public void testFirstNonNullBothNotNull() {
		String value = "foo";
		String returned = firstNonNull(value, "bar");
		assertThat(returned, is(value));
	}

	@Test
	public void testFirstNonNullFirstNull() {
		String value = "bar";
		String returned = firstNonNull(null, value);
		assertThat(returned, is(value));
	}

	@Test
	public void testFirstNonNullSecondNull() {
		String value = "bar";
		String returned = firstNonNull(value, null);
		assertThat(returned, is(value));
	}

	@Test(expected = NullPointerException.class)
	public void testBothNull() {
		// Never do this
		firstNonNull(null, null);
	}

}
