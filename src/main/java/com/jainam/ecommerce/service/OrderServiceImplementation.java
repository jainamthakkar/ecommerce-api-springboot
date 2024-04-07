package com.jainam.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jainam.ecommerce.exception.OrderException;
import com.jainam.ecommerce.model.Address;
import com.jainam.ecommerce.model.Order;
import com.jainam.ecommerce.model.User;
import com.jainam.ecommerce.repository.CartRepository;
import com.jainam.ecommerce.repository.OrderRepository;

@Service
public class OrderServiceImplementation implements OrderService {

	@Autowired
	private CartRepository cartRepository;
	
	@Autowired
	private CartService cartService;

	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public Order findOrderById(Long orderId) throws OrderException {
		
		Order order = orderRepository.findOrderById(orderId);
		
		return order;
	}

	@Override
	public List<Order> usersOrderHistory(Long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order placedOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order confirmOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order shippedOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order deliveredOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order canceledOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order createOrder(User user, Address shippingAddress) throws OrderException {
		// TODO Auto-generated method stub
		return null;
	}

}
