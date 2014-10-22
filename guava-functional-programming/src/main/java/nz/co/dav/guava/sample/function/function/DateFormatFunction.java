package nz.co.dav.guava.sample.function.function;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.google.common.base.Function;

public class DateFormatFunction implements Function<Date, String> {

	private String dateFormat;

	public DateFormatFunction(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	@Override
	public String apply(Date input) {
		return new SimpleDateFormat(dateFormat).format(input);
	}
}
