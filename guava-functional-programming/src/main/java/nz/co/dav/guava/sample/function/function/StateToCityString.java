package nz.co.dav.guava.sample.function.function;

import nz.co.dav.guava.sample.model.State;

import com.google.common.base.Function;
import com.google.common.base.Joiner;

public class StateToCityString implements Function<State, String> {

	@Override
	public String apply(State input) {
		return Joiner.on(",").join(input.getMainCities());
	}
}
