package com.udit.services.fetchers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udit.models.Cat;
import com.udit.models.Dog;
import com.udit.models.Person;
import com.udit.services.AnimalService;
import com.udit.services.PersonService;

import graphql.schema.DataFetcher;
import lombok.Getter;

@Component
public class Fetchers {

	@Autowired
	private PersonService personService;
	
	@Autowired
	private AnimalService animalService;

	@Getter
	private DataFetcher<List<Person>> allPersons = env -> personService.getPersons();

	@Getter
	private DataFetcher<Person> personBySSN = env -> personService.getPersonBySSN(env.getArgument("ssn"));
	
	@Getter
	private DataFetcher<Person> personByFirstName = env -> personService.getPersonByFirstName(env.getArgument("firstName"));
	
	@Getter
	private DataFetcher<List<Person>> personsGreaterThanAge = env -> personService.getPersonsGreaterThanAge(env.getArgument("age"));
	
	@Getter
	private DataFetcher<List<Person>> mutualFriends = env -> personService.getMutualFriends(env.getArgument("firstName1"), env.getArgument("firstName2"));
	
	
	@Getter
	private DataFetcher<List<Cat>> allCats = env -> animalService.getAllCats();
	
	@Getter
	private DataFetcher<List<Dog>> allDogs = env -> animalService.getAllDogs();

}
