package com.example.demo.rest.util;

import org.springframework.hateoas.ResourceSupport;

import com.example.demo.model.Ingredient;
import com.example.demo.model.Ingredient.Type;

import lombok.Getter;

public class IngredientResource extends ResourceSupport{

	@Getter
	private Type type;

	@Getter
	private String name;

	public IngredientResource(Ingredient ingredient) {
		
		this.name = ingredient.getName();
		this.type = ingredient.getType();
		
		
	}
	
}
