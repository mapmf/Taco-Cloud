package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Taco {
	
	private long id;
	
	private Date createdAt;
	
	@NotNull
	@Size(min=5, message="Name must be at least 5 characters long")
	private String name;
	
	@NotNull(message = "You must choose at least 1 ingredient")
	@Size(min=1, message="You must choose at least 1 ingredient")
	private List<String> ingredients;
}
