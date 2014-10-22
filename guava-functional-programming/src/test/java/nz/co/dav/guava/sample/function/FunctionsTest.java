package nz.co.dav.guava.sample.function;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import nz.co.dav.guava.sample.function.function.DateFormatFunction;
import nz.co.dav.guava.sample.function.function.LongToDateFunction;
import nz.co.dav.guava.sample.model.City;
import nz.co.dav.guava.sample.model.Climate;
import nz.co.dav.guava.sample.model.Region;
import nz.co.dav.guava.sample.model.State;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class FunctionsTest {

	private String format = "dd/mm/yyyy";
	private String mapString = "foo=bar,red=white,moe=larry";
	private Map<String, String> testMap;
	private Map<String, State> stateMap = Maps.newHashMap();
	private City city;
	private State state;
	private State defaultState;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(FunctionsTest.class);

	@Before
	public void setUp() {
		testMap = Splitter.on(',').withKeyValueSeparator('=').split(mapString);
		city = new City("Austin,TX", "12345", 250000, Climate.SUB_TROPICAL, 45.3);
		state = new State("Texas", "TX", Sets.newHashSet(city), Region.SOUTHWEST);
		defaultState = new State("No State", "XX", new HashSet<City>(), Region.NO_REGION);
		stateMap.put(state.getCode(), state);
	}

	@Test
	public void testInlineFunction() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
		Date date = new Date();
		String expected = dateFormat.format(date);
		Function<Date, String> function = new Function<Date, String>() {
			@Override
			public String apply(Date input) {
				return new SimpleDateFormat("dd/mm/yyyy").format(input);
			}
		};
		assertThat(function.apply(date), is(expected));
	}

	@Test
	public void testTransformList() throws Exception {
		DateFormatFunction function = new DateFormatFunction(format);
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		String dateString = dateFormat.format(date);
		List<Date> dates = Lists.newArrayList();
		dates.add(date);
		dates.add(date);
		List<String> formattedDates = Lists.transform(dates, function);
		assertThat(formattedDates.contains(dateString), is(true));
	}

	@Test
	public void testComposeFunction() throws Exception {
		DateFormatFunction dateFormatFunction = new DateFormatFunction(format);
		LongToDateFunction longToDateFunction = new LongToDateFunction();
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = dateFormat.parse("18/11/2003");
		long time = date.getTime();
		Function<Long, String> composedFunction = Functions.compose(dateFormatFunction, longToDateFunction);
		assertThat(composedFunction.apply(time), is("18/11/2003"));
	}

	@Test
	public void testForMapFunction() throws Exception {
		Function<String, State> stateMapFunction = Functions.forMap(stateMap);
		LOGGER.info("get state: {} ",stateMapFunction.apply("TX"));
		assertThat(stateMapFunction.apply("TX"), is(state));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testForMapFunctionNoDefaultThrowsException() throws Exception {
		Function<String, State> stateMapFunction = Functions.forMap(stateMap);
		stateMapFunction.apply("FOO");
		fail("Should not get here");
	}

	@Test
	public void testForMapFunctionDefaultValue() throws Exception {
		Function<String, State> stateMapFunction = Functions.forMap(stateMap, defaultState);
		assertThat(stateMapFunction.apply("FOO"), is(defaultState));
	}

	@Test
	public void testConstantsFunction() throws Exception {
		String constantValue = "constant-value";
		Function<Object, String> constant = Functions.constant(constantValue);
		assertThat(constant.apply(new Date()), is(constantValue));
		assertThat(constant.apply(2), is(constantValue));
		assertThat(constant.apply(false), is(constantValue));
	}

	@Test
	public void testIdentityFunction() throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date1 = dateFormat.parse("01/06/2009");
		Date date2 = dateFormat.parse("18/11/2003");
		Function<Date, Date> identity = Functions.identity();
		assertThat(identity.apply(date1), is(date1));
		assertThat(identity.apply(date2), is(date2));
	}

}
