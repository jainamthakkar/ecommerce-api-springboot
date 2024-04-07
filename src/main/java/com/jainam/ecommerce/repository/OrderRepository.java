package com.jainam.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jainam.ecommerce.exception.OrderException;
import com.jainam.ecommerce.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	public Order findOrderById(Long id) throws OrderException;

}
