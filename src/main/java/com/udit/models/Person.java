package com.udit.models;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Person {
	private String ssn;
	private String firstName;
	private String lastName;
	private int age;
	private List<Person> friends;
}
