package com.example.demo.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.demo.model.Taco;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long>{

	Page<Taco> findAll(Pageable page);
	

}
