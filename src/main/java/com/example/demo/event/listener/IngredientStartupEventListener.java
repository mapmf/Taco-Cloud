package com.example.demo.event.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.example.demo.model.Ingredient;
import com.example.demo.persistence.IngredientRepository;

@Component
public class IngredientStartupEventListener implements ApplicationListener<ContextRefreshedEvent> {

	private IngredientRepository ingredientRepository;

	@Autowired
	public IngredientStartupEventListener(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {

		long count = ingredientRepository.count();

		if (count == 0) {

			ingredientRepository.save(new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP));

			ingredientRepository.save(new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP));

			ingredientRepository.save(new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN));

			ingredientRepository.save(new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN));

			ingredientRepository.save(new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES));

			ingredientRepository.save(new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES));

			ingredientRepository.save(new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE));

			ingredientRepository.save(new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE));

			ingredientRepository.save(new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE));

			ingredientRepository.save(new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE));

		}

	}

}
