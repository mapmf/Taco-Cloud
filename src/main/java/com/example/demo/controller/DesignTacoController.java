package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Ingredient;
import com.example.demo.model.Taco;
import com.example.demo.persistence.IngredientRepository;
import com.example.demo.model.Ingredient.Type;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignTacoController {

	private final IngredientRepository ingredientsRepo;

	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo) {
		this.ingredientsRepo = ingredientRepo;
	}
	
	@GetMapping
	public String showDesignForm(Model model) {
		
		addIngredientsToModel(model);
		
		model.addAttribute("taco", new Taco());
		
		return "design";
	}
	
	@PostMapping
	public String processDesign(Model model, @Valid Taco design, Errors errors) {
		
		if(errors.hasErrors()) {
			
			addIngredientsToModel(model);	
			
			return "design";
		}
		
		log.info("Processing design: " + design);
		return "redirect:/orders/current";
	}

	private void addIngredientsToModel(Model model) {
		
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		
		ingredientsRepo.findAll().forEach(i -> ingredients.add(i));
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(),
			filterByType(ingredients, type));
		}
	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		return ingredients.stream()
						.filter(i -> i.getType().equals(type))
						.collect(Collectors.toList());
	}
	
}
