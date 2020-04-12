package com.udit.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Dog extends Animal {

	private String breed;

	public Dog(String classification, String sound, String breed) {
		super(classification, sound);
		this.breed = breed;
	}
}
