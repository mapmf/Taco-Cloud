package com.example.demo.persistence;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
	
	List<Order> findByDeliveryZip(String deliveryZip);
	List<Order> readOrdersByDeliveryZipAndPlacedAtBetween(String deliveryZip, Date startDate, Date endDate);
	List<Order> findByDeliveryNameAndDeliveryCityAllIgnoringCase(String deliverynName, String deliveryCity);
	List<Order> findByDeliveryCityOrderByDeliveryName(String city);

	@Query(value = "from Order o where o.deliveryCity='Seattle'")
	List<Order> readOrdersDeliveredInSeattle();
}
