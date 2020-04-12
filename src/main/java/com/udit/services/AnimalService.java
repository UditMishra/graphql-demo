package com.udit.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.udit.dbs.AnimalManager;
import com.udit.models.Cat;
import com.udit.models.Dog;

@Service
public class AnimalService {

	public List<Cat> getAllCats() {
		List<Cat> cats = new ArrayList<>();
		AnimalManager.getAnimalMap().values().forEach(animal -> {
			if (animal instanceof Cat) {
				cats.add((Cat) animal);
			}
		});
		return cats;
	}

	public List<Dog> getAllDogs() {
		List<Dog> dogs = new ArrayList<>();
		AnimalManager.getAnimalMap().values().forEach(animal -> {
			if (animal instanceof Dog) {
				dogs.add((Dog) animal);
			}
		});
		return dogs;
	}
}
