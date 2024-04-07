package com.jainam.ecommerce.service;

import java.util.List;

import com.jainam.ecommerce.exception.OrderException;
import com.jainam.ecommerce.model.Address;
import com.jainam.ecommerce.model.Order;
import com.jainam.ecommerce.model.User;

public interface OrderService {

	public Order findOrderById(Long orderId) throws OrderException;
	
	public List<Order> usersOrderHistory(Long orderId);
	
	public Order placedOrder(Long orderId) throws OrderException;
	
	public Order confirmOrder(Long orderId) throws OrderException;
	
	public Order shippedOrder(Long orderId) throws OrderException;
	
	public Order deliveredOrder(Long orderId) throws OrderException;
	
	public Order canceledOrder(Long orderId) throws OrderException;
	
	public List<Order> getAllOrders();
	
	public void deleteOrder(Long orderId) throws OrderException;

	public Order createOrder(User user, Address shippingAddress) throws OrderException;
	
}
