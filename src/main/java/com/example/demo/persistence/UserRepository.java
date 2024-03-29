package com.example.demo.persistence;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.User;

public interface UserRepository extends CrudRepository<User, Long>{
	
	User findByUsername(String username); 
}
