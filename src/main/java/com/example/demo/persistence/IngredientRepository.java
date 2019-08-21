package com.example.demo.persistence;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Ingredient;

public interface IngredientRepository extends CrudRepository<Ingredient, String>{
	
}
