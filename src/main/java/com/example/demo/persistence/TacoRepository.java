package com.example.demo.persistence;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long>{

}
