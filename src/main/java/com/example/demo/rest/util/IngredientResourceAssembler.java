package com.example.demo.rest.util;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.example.demo.model.Ingredient;
import com.example.demo.rest.IngredientRestController;

public class IngredientResourceAssembler extends ResourceAssemblerSupport<Ingredient, IngredientResource>{

	public IngredientResourceAssembler() {
		super(IngredientRestController.class, IngredientResource.class);
	}

	@Override
	protected IngredientResource instantiateResource(Ingredient ingredient) {

		return new IngredientResource(ingredient);
	}
	
	@Override
	public IngredientResource toResource(Ingredient ingredient) {
		return createResourceWithId(ingredient.getId(), ingredient);
	}

}
