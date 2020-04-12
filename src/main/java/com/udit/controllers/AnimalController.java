package com.udit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.udit.services.GraphQLService;

import graphql.ExecutionResult;

@RestController
public class AnimalController {

	@Autowired
	private GraphQLService service;

	@PostMapping("/animals")
	public ResponseEntity<ExecutionResult> getPersons(@RequestBody String query) {
		ExecutionResult result = service.getGraphQL().execute(query);
		return ResponseEntity.ok(result);
	}
}
