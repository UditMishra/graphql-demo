package com.udit.dbs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.udit.models.Person;

public class PersonManager {

	private static Map<String, Person> personMap;

	static {
		personMap = new HashMap<>();
		
		Person udit = new Person("1", "Udit", "Mishra", 30, new ArrayList<>());
		Person amit = new Person("2", "Amit", "Jha", 28, new ArrayList<>());
		Person ashish = new Person("3", "Ashish", "Kumar", 31, new ArrayList<>());
		Person sumit = new Person("4", "Sumit", "Sagar", 29, new ArrayList<>());
		
		udit.getFriends().add(amit);
		udit.getFriends().add(ashish);
		
		amit.getFriends().add(udit);
		amit.getFriends().add(sumit);
		amit.getFriends().add(ashish);
		
		ashish.getFriends().add(udit);
		ashish.getFriends().add(amit);
		
		sumit.getFriends().add(amit);
		
		personMap.put(udit.getSsn(), udit);
		personMap.put(amit.getSsn(), amit);
		personMap.put(ashish.getSsn(), ashish);
		personMap.put(sumit.getSsn(), sumit);
	}

	public static Map<String, Person> getPersonMap() {
		return personMap;
	}
}
