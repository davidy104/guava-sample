package nz.co.dav.guava.sample.function.function;

import java.util.Date;

import com.google.common.base.Function;

public class StringToDateFunction implements Function<String, Function<Date, String>> {

	@Override
	public Function<Date, String> apply(String input) {
		return new DateFormatFunction(input);
	}
}
