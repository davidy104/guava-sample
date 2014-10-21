package nz.co.dav.guava.sample.support.model;

import java.util.Map;

public class Person {

	public final String firstName;
	public final String lastName;
	public final String address;
	public final String email;

	public Person(Map<String, String> values) {
		firstName = values.get("first_name");
		lastName = values.get("last_name");
		address = values.get("address");
		email = values.get("email");
	}

}
