package nz.co.dav.guava.sample.function.supplier;

import java.util.Map;

import nz.co.dav.guava.sample.function.predicate.MidwestOrSouthwestRegionPredicate;
import nz.co.dav.guava.sample.model.City;
import nz.co.dav.guava.sample.model.Climate;
import nz.co.dav.guava.sample.model.Region;
import nz.co.dav.guava.sample.model.State;

import com.google.common.base.Function;
import com.google.common.base.Functions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.base.Supplier;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;


public class ComposedPredicateSupplier implements Supplier<Predicate<String>> {

	@Override
	public Predicate<String> get() {
		City city = new City("Austin,TX", "12345", 250000, Climate.SUB_TROPICAL, 45.3);
		State state = new State("Texas", "TX", Sets.newHashSet(city), Region.SOUTHWEST);
		City city1 = new City("New York,NY", "12345", 2000000, Climate.TEMPERATE, 48.7);
		State state1 = new State("New York", "NY", Sets.newHashSet(city1), Region.NORTHEAST);
		Map<String, State> stateMap = Maps.newHashMap();
		stateMap.put(state.getCode(), state);
		stateMap.put(state1.getCode(), state1);
		Function<String, State> mf = Functions.forMap(stateMap);
		return Predicates.compose(new MidwestOrSouthwestRegionPredicate(), mf);
	}
}
