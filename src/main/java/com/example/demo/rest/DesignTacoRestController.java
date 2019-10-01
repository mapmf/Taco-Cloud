package com.example.demo.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Taco;
import com.example.demo.persistence.TacoRepository;

@CrossOrigin
@RestController
@RequestMapping(path = "/design", produces = "application/json")
public class DesignTacoRestController {

	private TacoRepository tacoRepository;

	/*
	 * @Autowired EntityLinks entityLinks;
	 */

	public DesignTacoRestController(TacoRepository tacoRepository) {
		this.tacoRepository = tacoRepository;
	}

	@GetMapping("/recent")
	public Resources<Resource<Taco>> recentTacos() {
		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());

		List<Taco> tacoList = tacoRepository.findAll(page).getContent();

		Resources<Resource<Taco>> recentResources = Resources.wrap(tacoList);

		recentResources
			.add(linkTo(methodOn(DesignTacoRestController.class).recentTacos())
			.withRel("recents"));

		return recentResources;
	}

	private ControllerLinkBuilder linkTo(Resources<Resource<Taco>> resources) {
		return ControllerLinkBuilder.linkTo(resources);
	}

	private DesignTacoRestController methodOn(Class<DesignTacoRestController> clazz) {
		return ControllerLinkBuilder.methodOn(clazz);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {

		Optional<Taco> optTaco = tacoRepository.findById(id);

		if (optTaco.isPresent()) {

			return new ResponseEntity<>(optTaco.get(), HttpStatus.OK);
		}

		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
	}

	@PostMapping(consumes = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Taco postTaco(@RequestBody Taco taco) {
		return tacoRepository.save(taco);
	}

}
