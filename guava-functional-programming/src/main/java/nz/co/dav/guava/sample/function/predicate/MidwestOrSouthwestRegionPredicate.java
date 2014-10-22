package nz.co.dav.guava.sample.function.predicate;

import nz.co.dav.guava.sample.model.Region;
import nz.co.dav.guava.sample.model.State;

import com.google.common.base.Predicate;

public class MidwestOrSouthwestRegionPredicate implements Predicate<State> {

	@Override
	public boolean apply(State input) {
		return input.getRegion().equals(Region.MIDWEST) ||
				input.getRegion().equals(Region.SOUTHWEST);
	}
}
