package com.example.demo.persistence.jdbc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Order;
import com.example.demo.model.Taco;
import com.example.demo.persistence.OrderRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class JdbcOrderRepository implements OrderRepository{

	private SimpleJdbcInsert orderTacosInsert;
	private SimpleJdbcInsert orderInsert;
	private ObjectMapper objectMapper;
	
	@Autowired
	public JdbcOrderRepository(JdbcTemplate jdbc) {

		this.orderInsert = new SimpleJdbcInsert(jdbc)
				.withTableName("Taco_Order")
				.usingGeneratedKeyColumns("id");
		
		this.orderTacosInsert = new SimpleJdbcInsert(jdbc)
				.withTableName("Taco_Order_Tacos");
		
		this.objectMapper = new ObjectMapper();
		
	}
	
	@Override
	public Order save(Order order) {

		order.setPlacedAt(new Date());
		
		long orderId = saveOrderDetails(order);
		
		List<Taco> listTacos = order.getTacoList();
		
		for (Taco taco : listTacos) {
			saveTacoToOrder(taco, orderId);
		}
		
		return order;
	}

	private void saveTacoToOrder(Taco taco, long orderId) {
		
		Map<String, Object> values = new HashMap<String, Object>();
		
		values.put("tacoOrder", orderId);
		values.put("taco", taco.getId());
		
		this.orderTacosInsert.execute(values);
		
		
	}

	private long saveOrderDetails(Order order) {
		
		@SuppressWarnings("unchecked")
		Map<String, Object> values = objectMapper.convertValue(order, Map.class);

		values.put("placedAt", order.getPlacedAt());
		
		return this.orderInsert.executeAndReturnKey(values).longValue();
	}
	
}
