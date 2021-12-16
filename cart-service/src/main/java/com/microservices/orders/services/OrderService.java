package com.microservices.orders.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.microservices.orders.entity.Order;
import com.microservices.orders.repositories.OrderRepository;

import lombok.extern.slf4j.Slf4j;


public interface OrderService {
	
	
	public Order addorder(  Order order );
	 public List<Order> fetchAllOrders();
	 public Order update(Order order);
	 public ResponseEntity<Object> deleteById(String id);
	 
	

}
