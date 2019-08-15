package com.example.demo.persistence;

import com.example.demo.model.Ingredient;

public interface IngredientRepository {
	
	Iterable<Ingredient> findAll();
	
	Ingredient findOne(String id);
	
	Ingredient save(Ingredient ingredient);
	
}
