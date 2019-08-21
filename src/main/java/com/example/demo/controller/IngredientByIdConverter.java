package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.example.demo.model.Ingredient;
import com.example.demo.persistence.IngredientRepository;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient>{

	private IngredientRepository ingredientRepository;
	
	@Autowired
	public IngredientByIdConverter(IngredientRepository ingredientRepository) {
		
		this.ingredientRepository = ingredientRepository;
	}
	
	@Override
	public Ingredient convert(String id) {

		return ingredientRepository.findOne(id);
	}

}
