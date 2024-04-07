package com.jainam.ecommerce.service;

import com.jainam.ecommerce.exception.CartItemException;
import com.jainam.ecommerce.exception.UserException;
import com.jainam.ecommerce.model.Cart;
import com.jainam.ecommerce.model.CartItem;
import com.jainam.ecommerce.model.Product;

public interface CartItemService {

	public CartItem createCartItem(CartItem cartItem);
	
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;
	
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);
	
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;
	
	public CartItem findCartItemById(Long id) throws CartItemException;
}
