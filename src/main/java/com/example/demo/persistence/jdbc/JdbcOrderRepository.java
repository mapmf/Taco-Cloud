package com.example.demo.persistence.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Ingredient;
import com.example.demo.model.Order;
import com.example.demo.persistence.OrderRepository;

@Repository
public class JdbcOrderRepository implements OrderRepository{

	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcOrderRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public Order save(Order order) {

		//TODO
		
		return order;
	}
	
}
