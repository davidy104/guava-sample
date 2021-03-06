package nz.co.dav.guava.sample.collections;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class BiMapTest {

	@Test(expected = IllegalArgumentException.class)
	public void testBiMapSameValueDifferentKey() throws Exception {
		BiMap<String, String> biMap = HashBiMap.create();
		biMap.put("1", "Tom");
		biMap.put("2", "Tom");
	}

	@Test
	public void testBiMapForcePut() throws Exception {
		BiMap<String, String> biMap = HashBiMap.create();
		biMap.put("1", "Tom");
		biMap.forcePut("2", "Tom");
		assertThat(biMap.containsKey("1"), is(false));
		assertThat(biMap.containsKey("2"), is(true));
	}

	@Test
	public void testBiMapInverse() throws Exception {
		BiMap<String, String> biMap = HashBiMap.create();
		biMap.put("1", "Tom");
		biMap.put("2", "Harry");
		assertThat(biMap.get("1"), is("Tom"));
		assertThat(biMap.get("2"), is("Harry"));
		BiMap<String, String> inverseMap = biMap.inverse();
		assertThat(inverseMap.get("Tom"), is("1"));
		assertThat(inverseMap.get("Harry"), is("2"));
	}
}
