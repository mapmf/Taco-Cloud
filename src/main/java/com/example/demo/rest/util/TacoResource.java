package com.example.demo.rest.util;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.example.demo.model.Ingredient;
import com.example.demo.model.Taco;

import lombok.Getter;

public class TacoResource extends ResourceSupport{

	private static final IngredientResourceAssembler ingredientAssembler = new IngredientResourceAssembler();
	
	@Getter
	private final String name;
	
	@Getter
	private final Date createAt;

	@Getter
	private final List<IngredientResource> ingredients;
	
	public TacoResource(Taco taco) {

		name = taco.getName();
		createAt = taco.getCreatedAt();
		ingredients = ingredientAssembler.toResources(taco.getIngredients());
	}
}
