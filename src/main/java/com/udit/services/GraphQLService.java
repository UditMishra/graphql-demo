package com.udit.services;

import java.io.IOException;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternUtils;
import org.springframework.stereotype.Service;

import com.udit.services.fetchers.Fetchers;
import com.udit.services.resolvers.AnimalTypeResolver;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.TypeRuntimeWiring;
import graphql.schema.idl.errors.SchemaProblem;
import lombok.Getter;

@Service
public class GraphQLService {

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private Fetchers fetchers;

	@Getter
	private GraphQL graphQL;

	@PostConstruct
	public void loadSchema() throws IOException {

		SchemaParser parser = new SchemaParser();
		TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();

		Resource[] resources = loadResources("classpath:**/*.graphqls");

		Arrays.stream(resources).forEach(resource -> {
			try {
				typeRegistry.merge(parser.parse(resource.getFile()));
			} catch (SchemaProblem | IOException e) {
				e.printStackTrace();
			}
		});

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
										.dataFetcher("allCats", fetchers.getAllCats())
										.dataFetcher("allDogs", fetchers.getAllDogs())
					 )
				.type(TypeRuntimeWiring.newTypeWiring("Animal")
						.typeResolver(new AnimalTypeResolver())
				)				
				.build();
	}

	Resource[] loadResources(String pattern) throws IOException {
		return ResourcePatternUtils.getResourcePatternResolver(resourceLoader).getResources(pattern);
	}
}
