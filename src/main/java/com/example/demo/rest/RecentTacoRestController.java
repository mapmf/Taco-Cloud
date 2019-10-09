package com.example.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Taco;
import com.example.demo.persistence.TacoRepository;
import com.example.demo.rest.util.TacoResource;
import com.example.demo.rest.util.TacoResourceAssembler;

@RepositoryRestController
public class RecentTacoRestController {

	private TacoRepository tacoRepository;

	@Autowired
	public RecentTacoRestController(TacoRepository tacoRepository) {
		this.tacoRepository = tacoRepository;
	}

	@GetMapping(path = "/tacos/recents", produces = "application/hal+json")
	public ResponseEntity<Resources<TacoResource>> recentTacos() {

		PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());

		List<Taco> tacos = tacoRepository.findAll(page).getContent();

		List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(tacos);

		Resources<TacoResource> recentResources = new Resources<TacoResource>(tacoResources);

		recentResources.add(linkTo(methodOn().recentTacos()).withRel("recents"));

		return new ResponseEntity<>(recentResources, HttpStatus.OK);
	}

	private ControllerLinkBuilder linkTo(ResponseEntity<Resources<TacoResource>> resources) {
		return ControllerLinkBuilder.linkTo(resources);
	}

	private RecentTacoRestController methodOn() {
		return ControllerLinkBuilder.methodOn(RecentTacoRestController.class);
	}

	@Bean
	public ResourceProcessor<PagedResources<Resource<Taco>>> tacoProcessor(EntityLinks links) {
		return new ResourceProcessor<PagedResources<Resource<Taco>>>() {
			@Override
			public PagedResources<Resource<Taco>> process(PagedResources<Resource<Taco>> resource) {
				resource.add(links.linkFor(Taco.class).slash("recent").withRel("recents"));
				return resource;
			}
		};
	}

}
