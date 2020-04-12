package com.udit.services.resolvers;

import com.udit.models.Cat;
import com.udit.models.Dog;

import graphql.TypeResolutionEnvironment;
import graphql.schema.GraphQLObjectType;
import graphql.schema.TypeResolver;

public class AnimalTypeResolver implements TypeResolver{

	@Override
	public GraphQLObjectType getType(TypeResolutionEnvironment env) {
		System.out.println("Request came : " + env);
		Object obj = env.getObject();
		if(obj instanceof Cat) {
			env.getSchema().getObjectType("Cat");
		}
		else if(obj instanceof Dog) {
			env.getSchema().getObjectType("Dog");
		}
		return env.getSchema().getObjectType("Cat");
	}

}
