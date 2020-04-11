package com.udit.dbs;

import java.util.HashMap;
import java.util.Map;

import com.udit.models.Person;

public class DatabaseManager {

	private static Map<String, Person> personMap;

	static {
		personMap = new HashMap<>();
		personMap.put("1", new Person("1", "Udit", "Mishra", 30));
		personMap.put("2", new Person("2", "Amit", "Jha", 28));
		personMap.put("3", new Person("3", "Ashish", "Kumar", 29));
	}

	public static Map<String, Person> getPersonMap() {
		return personMap;
	}
}
