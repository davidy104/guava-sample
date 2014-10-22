package nz.co.dav.guava.sample.function.predicate;

import nz.co.dav.guava.sample.model.City;

import com.google.common.base.Predicate;

public class SmallPopulationPredicate implements Predicate<City> {

	@Override
	public boolean apply(City input) {
		return input.getPopulation() < 500000;
	}
}
