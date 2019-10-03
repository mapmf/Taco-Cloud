package com.example.demo.rest.util;

import org.springframework.hateoas.ResourceSupport;

import com.example.demo.model.Ingredient;
import com.example.demo.model.Ingredient.Type;

import lombok.Getter;

public class IngredientResource extends ResourceSupport{

	@Getter
	private final String name;
	
	@Getter
	private Type type;
	
	public IngredientResource(Ingredient ingredient) {
	
		name = ingredient.getName();
		
		type = ingredient.getType();
	}
	
}
