package com.udit.dbs;

import java.util.HashMap;
import java.util.Map;

import com.udit.models.Animal;
import com.udit.models.Cat;
import com.udit.models.Dog;

public class AnimalManager {

	private static Map<String, Animal> animalMap = new HashMap<>();

	static {
		animalMap.put("House Cat", new Cat("Mammal", "Meow", "House Cat"));
		animalMap.put("Farm Cat", new Cat("Mammal", "Growl", "Farm Cat"));
		animalMap.put("Feral Cat", new Cat("Mammal", "Purr", "Feral Cat"));
		animalMap.put("Beagle", new Dog("Mammal", "Bark", "Beagle"));
		animalMap.put("Doberman", new Dog("Mammal", "Bark", "Doberman"));
		animalMap.put("Pitbull", new Dog("Mammal", "Bark", "Pitbull"));
	}

	public static Map<String, Animal> getAnimalMap() {
		return animalMap;
	}
}
