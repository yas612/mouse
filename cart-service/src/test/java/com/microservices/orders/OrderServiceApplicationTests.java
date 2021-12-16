package com.microservices.orders;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import com.microservices.orders.entity.Order;

import com.microservices.orders.repositories.OrderRepository;
import com.microservices.orders.services.OrderService;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderServiceApplicationTests {
	@Autowired
	OrderService service;
	
	@MockBean
	OrderRepository repository;

	@Test
	public void addorderTest()
	{
		 Order order = new Order("123",2,"12","flipkart",200);
		   when(repository.insert(order)).thenReturn(order);
		   assertEquals(order,service.addorder(order));
	}
	
	@Test
	public void fetchAllOrders()
	{

		when(repository.findAll()).thenReturn(Stream.of
			    (new Order("1",2,"12","swiggy",100),new Order("2",1,"134","boat",500))
			    .collect(Collectors.toList()));
			  assertEquals(2,service.fetchAllOrders().size());
			  
			  
	}

	

}
