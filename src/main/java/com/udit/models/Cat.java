package com.udit.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cat extends Animal {

	private String type;

	public Cat(String classification, String sound, String type) {
		super(classification, sound);
		this.type = type;
	}
}
