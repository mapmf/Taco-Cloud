package com.example.demo.rest.util;

import java.util.Date;
import java.util.List;

import org.springframework.hateoas.ResourceSupport;

import com.example.demo.model.Ingredient;
import com.example.demo.model.Taco;

import lombok.Getter;

public class TacoResource extends ResourceSupport{

	@Getter
	private final String name;
	
	@Getter
	private final Date createdAt;
	
	@Getter
	private final List<Ingredient> ingredients;
	
	public TacoResource(Taco taco) {

		this.name = taco.getName();
		this.createdAt = taco.getCreatedAt();
		this.ingredients = taco.getIngredients();
	}
}
