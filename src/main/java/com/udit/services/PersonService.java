package com.udit.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.udit.dbs.DatabaseManager;
import com.udit.models.Person;

@Service
public class PersonService {

	public List<Person> getPersons() {
		return new ArrayList<>(DatabaseManager.getPersonMap().values());
	}

	public Person getPersonBySSN(String ssn) {
		return DatabaseManager.getPersonMap().get(ssn);
	}

	public Person getPersonByFirstName(String firstName) {
		return getPersons().stream().filter(p -> firstName.equals(p.getFirstName())).findFirst().get();
	}

	public List<Person> getPersonsGreaterThanAge(int age) {
		return getPersons().stream().filter(p -> p.getAge() > age).collect(Collectors.toList());
	}

	public List<Person> getMutualFriends(String firstName1, String firstName2) {

		Person person1 = getPersons().stream().filter(p -> firstName1.equals(p.getFirstName())).findFirst().get();
		Person person2 = getPersons().stream().filter(p -> firstName2.equals(p.getFirstName())).findFirst().get();

		List<Person> mutualFriends = new ArrayList<>(person1.getFriends());
		mutualFriends.retainAll(person2.getFriends());
		
		return mutualFriends;
	}
}
