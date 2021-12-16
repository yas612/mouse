package com.microservices.orders.services;

import java.util.List;
import java.util.Optional;



import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.microservices.orders.entity.Order;
import com.microservices.orders.exception.OrderRequestException;
import com.microservices.orders.repositories.OrderRepository;

@Service
@Slf4j
public class OrderServiceImp implements OrderService {

	@Autowired
	private OrderRepository orderRepository;
	
	public Order addorder(  Order order ){
		orderRepository.insert( order );
		System.out.println("Added Successfully");
		return order;
	}
	
	
    public List<Order> fetchAllOrders() {
		return  orderRepository.findAll();
    }

	public Order update(Order order) {
		Optional<Order> optionalOrder = Optional.ofNullable(orderRepository.findByOrderId(order.getOrderId()));
		if(optionalOrder.isPresent()){
			return  orderRepository.save(order);
		}else{
			return null;
		}

	}

	public ResponseEntity<Object> deleteById(String id)
	{
		boolean isUserExist=orderRepository.existsById(id);
		 if(isUserExist) {
			 orderRepository.deleteById(id);
			 return new ResponseEntity<Object>("user deleted with id "+id,HttpStatus.OK);
		 }
		 else
		 {
		 	throw new OrderRequestException("CAN NOT DELETE AS USER NOT FOUND WITH THIS ID ::");
		 }
	}
}
