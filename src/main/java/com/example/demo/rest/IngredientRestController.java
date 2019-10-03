package com.example.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Ingredient;
import com.example.demo.persistence.IngredientRepository;
import com.example.demo.rest.util.IngredientResource;
import com.example.demo.rest.util.IngredientResourceAssembler;

@RestController
@CrossOrigin
@RequestMapping(path = "/ingredient", produces = "application/json")
public class IngredientRestController {

	IngredientRepository ingredientRepository;
	
	@Autowired
	public IngredientRestController(IngredientRepository ingredientRepository) {
		this.ingredientRepository = ingredientRepository;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Ingredient> get(@PathVariable("id") String id) {
		
		Optional<Ingredient> optIngredient = ingredientRepository.findById(id);
		
		if(optIngredient.isPresent()) {
			
			return new ResponseEntity<Ingredient>(optIngredient.get(), HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(path="/list")
	public Resources<IngredientResource> list(){
		
		Iterable<Ingredient> ingredients = ingredientRepository.findAll();
		
		IngredientResourceAssembler ingredientResourceAssembler = new IngredientResourceAssembler();
		
		List<IngredientResource> ingredientResourceList = ingredientResourceAssembler.toResources(ingredients);
		
		Resources<IngredientResource> resources = new Resources<IngredientResource>(ingredientResourceList);
		
		IngredientRestController methodOn = ControllerLinkBuilder.methodOn(IngredientRestController.class);
		
		resources.add(ControllerLinkBuilder.linkTo(methodOn.list()).withRel("list"));
		
		return resources;
	}
}
