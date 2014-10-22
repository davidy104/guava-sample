package nz.co.dav.guava.sample.collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.google.common.collect.Lists;

public class ListsTest {

	@Test
	public void testPartition() {
		List<Integer> numbers = Lists.newArrayList(1, 2, 3, 4);
		List<List<Integer>> subLists = Lists.partition(numbers, 2);
		List<Integer> s1 = Lists.newArrayList(1, 2);
		List<Integer> s2 = Lists.newArrayList(3, 4);
		assertThat(subLists.get(0), is(s1));
		assertThat(subLists.get(1), is(s2));
	}

	@Test
	public void testReverse() {
		List<Integer> numbers = Lists.newArrayList(1, 2, 3, 4);
		List<Integer> expected = Lists.newArrayList(4, 3, 2, 1);
		List<Integer> reversed = Lists.reverse(numbers);
		assertThat(expected, is(reversed));
	}

}