package com.shashank.ecom.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.shashank.ecom.Exceptions.OrderNOtFoundException;
import com.shashank.ecom.Repository.OrderRepository;
import com.shashank.ecom.models.Order;

@Service
public class OrderService {
	OrderRepository orderRepository;
	
	public OrderService (OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}
	
	public Order GetSingleOrder(long id) throws OrderNOtFoundException{
		Optional<Order> GetASingleOrderById;
		GetASingleOrderById = orderRepository.findById(id);
		
		Order od;
		
		if(GetASingleOrderById.isEmpty()) {
			throw new OrderNOtFoundException("No orders are found");
		}
		else {
			od = GetASingleOrderById.get();
		}
			
		return od;
		
	}
	
	public List<Order> GetAllOrders() {
		List<Order> allorders = orderRepository.findAll();
		
		return allorders;
	}
}
