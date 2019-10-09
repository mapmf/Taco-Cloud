package com.example.demo.rest.util;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import com.example.demo.model.Taco;
import com.example.demo.rest.RecentTacoRestController;

public class TacoResourceAssembler extends ResourceAssemblerSupport<Taco, TacoResource>{

	public TacoResourceAssembler() {
		super(Taco.class, TacoResource.class);
	}

	@Override
	public TacoResource toResource(Taco taco) {

		return createResourceWithId(taco.getId(), taco);
	}
	
	@Override
	protected TacoResource instantiateResource(Taco taco) {

		return new TacoResource(taco);
	}

}
