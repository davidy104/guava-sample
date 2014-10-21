package nz.co.dav.guava.sample.utilities;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.google.common.base.CaseFormat;

public class CaseFormatTest {

	@Test
	public void testToLowerCamelCaseFromLowerHyphen() {
		assertThat(CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, "foo-bar"), is("fooBar"));
	}

	@Test
	public void testToUpperCamelCaseFromLowerUnderScore() {
		assertThat(CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, "foo_bar"), is("FooBar"));
	}

	@Test
	public void testToUpperUnderScoreFromLowerCamelCase() {
		assertThat(CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "fooBar"), is("FOO_BAR"));
	}
}
