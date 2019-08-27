package com.example.demo.persistence;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Authority;

public interface AuthorityRepository extends CrudRepository<Authority, Long>{
	
	Authority findByName(String name);
}
