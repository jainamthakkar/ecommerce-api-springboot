package com.jainam.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jainam.ecommerce.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
