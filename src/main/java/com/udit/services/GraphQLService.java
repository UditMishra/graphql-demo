package com.udit.services;

import java.io.File;
import java.io.IOException;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.udit.services.fetchers.Fetchers;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.Getter;

@Service
public class GraphQLService {

	@Value("classpath:persons.graphqls")
	Resource resource;

	@Autowired
	private Fetchers fetchers;
	
	@Getter
	private GraphQL graphQL;

	@PostConstruct
	public void loadSchema() throws IOException {

		File schemaFile = resource.getFile();

		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(schemaFile);
		RuntimeWiring runtimeWiring = buildRunTimeWiring();

		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(typeRegistry, runtimeWiring);

		graphQL = GraphQL.newGraphQL(schema).build();
	}

	public RuntimeWiring buildRunTimeWiring() {
		return RuntimeWiring.newRuntimeWiring()
				.type("Query", 
						typeWiring -> typeWiring
										.dataFetcher("allPersons", fetchers.getAllPersons())
										.dataFetcher("person", fetchers.getPersonBySSN())
										.dataFetcher("byFirstName", fetchers.getPersonByFirstName())
										.dataFetcher("greaterThanAge", fetchers.getPersonsGreaterThanAge())
										.dataFetcher("mutualFriends", fetchers.getMutualFriends())
					 )
				.build();
	}
}
