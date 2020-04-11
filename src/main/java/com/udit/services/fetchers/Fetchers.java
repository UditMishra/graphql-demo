package com.udit.services.fetchers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.udit.models.Person;
import com.udit.services.PersonService;

import graphql.schema.DataFetcher;
import lombok.Getter;

@Component
public class Fetchers {

	@Autowired
	private PersonService service;

	@Getter
	private DataFetcher<List<Person>> allPersons = env -> service.getPersons();

	@Getter
	private DataFetcher<Person> personBySSN = env -> service.getPersonBySSN(env.getArgument("ssn"));
	
	@Getter
	private DataFetcher<Person> personByFirstName = env -> service.getPersonByFirstName(env.getArgument("firstName"));
	
	@Getter
	private DataFetcher<List<Person>> personsGreaterThanAge = env -> service.getPersonsGreaterThanAge(env.getArgument("age"));

}
