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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.model.Ingredient;
import com.example.demo.model.Taco;
import com.example.demo.persistence.IngredientRepository;
import com.example.demo.persistence.TacoRepository;
import com.example.demo.model.Ingredient.Type;
import com.example.demo.model.Order;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {

	private final IngredientRepository ingredientsRepo;
	private TacoRepository designRepository;
	
	@Autowired
	public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepository) {
		this.ingredientsRepo = ingredientRepo;
		this.designRepository = designRepository;
	}
	
	@GetMapping
	public String showDesignForm(Model model) {
		
		addIngredientsToModel(model);
		
		return "design";
	}

	@ModelAttribute(name = "order")
	public Order order() {
		return new Order();
	}
	
	@ModelAttribute(name = "taco")
	public Taco taco() {
		return new Taco();
	}
	
	@PostMapping
	public String processDesign(Model model, @Valid Taco design, Errors errors, @ModelAttribute Order order) {
		
		if(errors.hasErrors()) {
			
			addIngredientsToModel(model);	
			
			return "design";
		}
		
		log.info("Processing design: " + design);
		
		Taco saved = designRepository.save(design);
		order.addDesign(saved);
		
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
