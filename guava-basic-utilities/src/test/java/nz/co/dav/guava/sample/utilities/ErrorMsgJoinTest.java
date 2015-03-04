package nz.co.dav.guava.sample.utilities;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class ErrorMsgJoinTest {

	private Map<String,Set<String>> metaMap = Maps.<String,Set<String>>newHashMap();
	
	@Before
	public void init(){
		Set<String> messageSet = Sets.<String>newHashSet();
		messageSet.add("A-01 type error happened.");
		messageSet.add("A-02 type error happened.");
		messageSet.add("A-03 type error happened.");
		metaMap.put("A", messageSet);
		messageSet = Sets.<String>newHashSet();
		messageSet.add("B-01 type error happened.");
		messageSet.add("B-02 type error happened.");
		metaMap.put("B", messageSet);
	}
	
	@Test
	public void test() {
		List<String> convertErrorList = Lists.<String> newArrayList();
		for (final Map.Entry<String,Set<String>> entry : metaMap.entrySet()) {
			Set<String> errorMessages = entry.getValue();
			if (!errorMessages.isEmpty()) {
				StringBuilder builder = new StringBuilder("Error of [" + entry.getKey() + "]:");
				convertErrorList.add(Joiner.on("|").appendTo(builder, errorMessages).toString());
			}
		}
		if (!convertErrorList.isEmpty()) {
			final String errorMessage = Joiner.on("\n").join(convertErrorList);
			System.out.println(errorMessage);
		}
	}

}
