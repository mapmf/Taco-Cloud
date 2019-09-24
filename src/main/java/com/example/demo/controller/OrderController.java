package com.example.demo.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.persistence.OrderRepository;
import com.example.demo.properties.OrderProps;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

	private OrderRepository orderRepository;
	
	private OrderProps orderProps;
	
	@Autowired
	public OrderController(OrderRepository orderRepository, OrderProps orderProps) {
		this.orderRepository = orderRepository;
		this.orderProps = orderProps;
	}

	@GetMapping("/current")
	public String orderForm(Model model) {

		return "orderForm";
	}
	
	@GetMapping
	public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
		
		Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
		
		model.addAttribute("orders", orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));
		
		return "orderList";
	}

	@PostMapping
	public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus,
			@AuthenticationPrincipal User user) {

		if (errors.hasErrors()) {
			return "orderForm";
		}

		log.info("Order submitted: " + order);

		order.setUser(user);
		
		orderRepository.save(order);

		sessionStatus.setComplete();

		return "redirect:/";
	}

}
