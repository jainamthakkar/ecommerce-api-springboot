package com.jainam.ecommerce.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jainam.ecommerce.exception.OrderException;
import com.jainam.ecommerce.model.Address;
import com.jainam.ecommerce.model.Cart;
import com.jainam.ecommerce.model.CartItem;
import com.jainam.ecommerce.model.Order;
import com.jainam.ecommerce.model.OrderItem;
import com.jainam.ecommerce.model.User;
import com.jainam.ecommerce.repository.AddressRepository;
import com.jainam.ecommerce.repository.OrderItemRepository;
import com.jainam.ecommerce.repository.OrderRepository;
import com.jainam.ecommerce.repository.UserRepository;

@Service
public class OrderServiceImplementation implements OrderService {
	
	@Autowired
	private CartService cartService;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Order findOrderById(Long orderId) throws OrderException {
		
		Optional<Order> order = orderRepository.findById(orderId);
		
		if(order.isPresent())
			return order.get();
		
		throw new OrderException("Order not exist with id:" + orderId);
	}

	@Override
	public List<Order> usersOrderHistory(Long userId) {
		
		List<Order> orders = orderRepository.getUserOrders(userId);
		
		return orders;
	}

	@Override
	public Order placedOrder(Long orderId) throws OrderException {
		
		Order order = findOrderById(orderId);
		order.setOrderStatus("PLACED");
		order.getPaymentDetails().setStatus("COMPLETED");
		return orderRepository.save(order);
	}

	@Override
	public Order confirmOrder(Long orderId) throws OrderException {
		
		Order order = findOrderById(orderId);
		order.setOrderStatus("CONFIRMED");
		return orderRepository.save(order);
	}

	@Override
	public Order shippedOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("SHIPPED");
		return orderRepository.save(order);
	}

	@Override
	public Order deliveredOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("DELIVERED");
		return orderRepository.save(order);
	}

	@Override
	public Order canceledOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("CANCELLED");
		return orderRepository.save(order);
	}

	@Override
	public List<Order> getAllOrders() {
		
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		
		Order order = findOrderById(orderId);
		orderRepository.deleteById(orderId);
		
	}

	@Override
	public Order createOrder(User user, Address shippingAddress) throws OrderException {
		
		shippingAddress.setUser(user);
		Address address = addressRepository.save(shippingAddress);
		user.getAddress().add(address); // new address will be added to user's profile through List of address
		userRepository.save(user);
		
		Cart cart = cartService.findUserCart(user.getId());
		List<OrderItem> orderItems = new ArrayList<OrderItem>();
		
		for(CartItem item : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			
			orderItem.setPrice(item.getPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSize(item.getSize());
			orderItem.setUserId(item.getUserId());
			orderItem.setDiscountedPrice(item.getDiscountedPrice());
			
			
			OrderItem createdOrderItem = orderItemRepository.save(orderItem);
			orderItems.add(createdOrderItem);
		}
		
		Order createdOrder = new Order();
		createdOrder.setUser(user);
		createdOrder.setOrderItems(orderItems);
		createdOrder.setTotalPrice(cart.getTotalPrice());
		createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
		createdOrder.setDiscount(cart.getDiscount());
		createdOrder.setTotalItem(cart.getTotalItem());
		createdOrder.setShippingAddress(address);
		createdOrder.setOrderDate(LocalDateTime.now());
		createdOrder.setOrderStatus("PENDING");
		createdOrder.setCreatedAt(LocalDateTime.now());
		createdOrder.getPaymentDetails().setStatus("PENDING");
		
		Order savedOrder = orderRepository.save(createdOrder);
		
		for(OrderItem item : orderItems) {
			item.setOrder(savedOrder);
			orderItemRepository.save(item);
		}
		
		return savedOrder;
	}

}
