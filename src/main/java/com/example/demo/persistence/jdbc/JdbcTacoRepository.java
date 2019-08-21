package com.example.demo.persistence.jdbc;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Ingredient;
import com.example.demo.model.Taco;
import com.example.demo.persistence.TacoRepository;

@Repository
public class JdbcTacoRepository implements TacoRepository{

	private SimpleJdbcInsert tacoInsert;
	private SimpleJdbcInsert tacoIngredientInsert;
	
	@Autowired
	public JdbcTacoRepository(JdbcTemplate jdbc) {
		
		tacoInsert = new SimpleJdbcInsert(jdbc)
				.withTableName("Taco")
				.usingGeneratedKeyColumns("id");

		tacoIngredientInsert = new SimpleJdbcInsert(jdbc)
				.withTableName("Taco_Ingredients");
	
	}
	
	@Override
	public Taco save(Taco taco) {
		
		taco.setCreatedAt(new Date());
		
		long tacoId = saveTacoInfo(taco);
		
		taco.setId(tacoId);
		
		List<Ingredient> ingredients = taco.getIngredients();
		
		for (Ingredient ingredient : ingredients) {
			
			saveIngredientToTaco(tacoId, ingredient);
		}
		
		return taco;
	}

	private void saveIngredientToTaco(long tacoId, Ingredient ingredient) {
		Map<String, Object> values = new HashMap<String, Object>();
		
		values.put("taco", tacoId);
		values.put("ingredient", ingredient.getId());
		
		tacoIngredientInsert.execute(values);
	}

	private long saveTacoInfo(Taco taco) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("name", taco.getName());
		map.put("createdAt", taco.getCreatedAt());
		
		return tacoInsert.executeAndReturnKey(map).longValue();
	}

}
