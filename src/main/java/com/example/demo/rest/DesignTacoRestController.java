package com.example.demo.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Taco;
import com.example.demo.persistence.TacoRepository;

@RestController
@RequestMapping(path = "/design", produces = "application/json")
@CrossOrigin(origins = "*")
public class DesignTacoRestController {

	private TacoRepository tacoRepository;
	
	/*
	 * @Autowired EntityLinks entityLinks;
	 */
	
	public DesignTacoRestController(TacoRepository tacoRepository) {
		this.tacoRepository = tacoRepository;
	}
	
	@GetMapping("/recent")
	public Iterable<Taco> recentTacos() {
		PageRequest page = PageRequest.of(
		0, 12, Sort.by("createdAt").descending());
		return tacoRepository.findAll(page).getContent();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {

		Optional<Taco> optTaco = tacoRepository.findById(id);
		
		if (optTaco.isPresent()) {
		
			return new ResponseEntity<>(optTaco.get(), HttpStatus.OK) ;
		}
		
		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}
	
}
