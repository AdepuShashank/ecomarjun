package com.shashank.ecom.Services;

import java.util.List;
import java.util.Optional;

import com.shashank.ecom.models.OrderStatus;
import com.shashank.ecom.models.Product;
import com.shashank.ecom.models.User;
import org.aspectj.weaver.ast.Or;
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

	public Long CreateOrder(User user, List<Product> products){
		Order order = new Order();

		Double totalAmount = 0.0;
		for(Product p : products){
			totalAmount += p.getPrice();
		}

		order.setUser(user);
		order.setProducts(products);
		order.setPrice(totalAmount);
		order.setOrderstatus(OrderStatus.CREATED);

		Order savedOrder = orderRepository.save(order);

		System.out.println(savedOrder.toString());

		return savedOrder.getId();
	}
}
