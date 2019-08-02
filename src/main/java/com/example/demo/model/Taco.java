package com.example.demo.model;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Taco {
	
	@NotNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String name;
	
	@NotNull(message = "You must choose at least 1 ingredient")
	@Size(min=1, message="You must choose at least 1 ingredient")
	private List<String> ingredients;
}
