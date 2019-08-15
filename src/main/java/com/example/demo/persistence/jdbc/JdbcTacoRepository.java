package com.example.demo.persistence.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Taco;
import com.example.demo.persistence.TacoRepository;

@Repository
public class JdbcTacoRepository implements TacoRepository{

	JdbcTemplate jdbc;
	
	@Autowired
	public JdbcTacoRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public Taco save(Taco taco) {

		//TODO
		
		return taco;
	}

}
