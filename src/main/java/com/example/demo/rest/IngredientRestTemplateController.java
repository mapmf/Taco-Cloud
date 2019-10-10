package com.example.demo.rest;

import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.example.demo.model.Ingredient;

@RepositoryRestController
public class IngredientRestTemplateController {

	private RestTemplate restTemplate;
	
	public IngredientRestTemplateController() {
		restTemplate = restTemplate();
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@ResponseBody
	@GetMapping("/ingredients/template/get/{id}")
	public Ingredient getById(@PathVariable("id") String ingredientId) {
		
		ResponseEntity<Ingredient> ingredientById = getIngredientById(ingredientId);
		
		return ingredientById.getBody();
	}
	
	private ResponseEntity<Ingredient> getIngredientById(String ingredientId) {
		
		return restTemplate.
				getForEntity("http://localhost:8080/rest/ingredients/{id}", Ingredient.class, ingredientId);
		
	}
	
}
