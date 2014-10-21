package nz.co.dav.guava.sample.utilities;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.google.common.base.Ascii;

public class AsciiTest {

	@Test
	public void testIsLowerCase() {
		assertThat(Ascii.isLowerCase('b'), is(true));
	}

	@Test
	public void testIsUpperCase() {
		assertThat(Ascii.isUpperCase('A'), is(true));
	}

	@Test
	public void testToLowerCase() {
		assertThat(Ascii.toLowerCase("GUAVA"), is("guava"));
	}

	@Test
	public void testToUpperCase() {
		assertThat(Ascii.toUpperCase("guava"), is("GUAVA"));
	}

}
