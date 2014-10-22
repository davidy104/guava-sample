package nz.co.dav.guava.sample.function.predicate;

import nz.co.dav.guava.sample.model.City;
import nz.co.dav.guava.sample.model.Climate;

import com.google.common.base.Predicate;

public class TemperateClimatePredicate implements Predicate<City> {

	@Override
	public boolean apply(City input) {
		return input.getClimate().equals(Climate.TEMPERATE);
	}
}
