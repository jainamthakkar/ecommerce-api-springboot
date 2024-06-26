package com.jainam.ecommerce.controller;

import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jainam.ecommerce.exception.OrderException;
import com.jainam.ecommerce.model.Order;
import com.jainam.ecommerce.response.ApiResponse;
import com.jainam.ecommerce.service.OrderService;

@RestController
@RequestMapping("/api/admin/orders")
public class AdminOrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping("/")
	public ResponseEntity<List<Order>> getAllOrderHandler(){
		List<Order> orders = orderService.getAllOrders();
		return new ResponseEntity<List<Order>>(orders, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/{orderId}/confirmed")
	public ResponseEntity<Order> confirmedOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization") String jwt) throws OrderException{
		
		Order order = orderService.confirmOrder(orderId);
		
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	@PutMapping("/{orderId}/ship")
	public ResponseEntity<Order> ShippedOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization")String jwt) throws OrderException{
		Order order = orderService.shippedOrder(orderId);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}
	
	@PutMapping("/{orderId}/deliver")
	public ResponseEntity<Order> diliverOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization")String jwt) throws OrderException{
		Order order = orderService.deliveredOrder(orderId);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}	
	
	@PutMapping("/{orderId}/cancel")
	public ResponseEntity<Order> cancelOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization")String jwt) throws OrderException{
		Order order = orderService.canceledOrder(orderId);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}	
	
	@PutMapping("/{orderId}/delete")
	public ResponseEntity<ApiResponse> deleteOrderHandler(@PathVariable Long orderId, @RequestHeader("Authorization")String jwt) throws OrderException{
		
		orderService.deleteOrder(orderId);
		
		ApiResponse res = new ApiResponse();
		res.setMessage("order deleted successfully!!");
		res.setStatus(true);
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
}
