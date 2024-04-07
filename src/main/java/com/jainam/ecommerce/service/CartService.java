package com.jainam.ecommerce.service;

import com.jainam.ecommerce.exception.ProductException;
import com.jainam.ecommerce.model.Cart;
import com.jainam.ecommerce.model.User;
import com.jainam.ecommerce.request.AddItemRequest;

public interface CartService {

	public Cart createCart(User user);
	
	public String addCartItem(Long userId, AddItemRequest req) throws ProductException;

	public Cart findUserCart(Long userId);
}
