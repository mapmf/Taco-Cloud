package com.example.demo.persistence;

import com.example.demo.model.Taco;

public interface TacoRepository {

	Taco save(Taco taco);
}
