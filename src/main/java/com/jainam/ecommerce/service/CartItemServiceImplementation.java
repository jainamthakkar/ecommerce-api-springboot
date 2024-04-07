package com.jainam.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jainam.ecommerce.exception.CartItemException;
import com.jainam.ecommerce.exception.UserException;
import com.jainam.ecommerce.model.Cart;
import com.jainam.ecommerce.model.CartItem;
import com.jainam.ecommerce.model.Product;
import com.jainam.ecommerce.model.User;
import com.jainam.ecommerce.repository.CartItemRepository;
import com.jainam.ecommerce.repository.CartRepository;

@Service
public class CartItemServiceImplementation implements CartItemService {

	@Autowired
	private CartItemRepository cartItemRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private CartRepository cartRepository;

	@Override
	public CartItem createCartItem(CartItem cartItem) {

		cartItem.setQuantity(1);
		cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
		cartItem.setDiscountedPrice((cartItem.getProduct().getDiscountedPrice()) * (cartItem.getQuantity()));

		CartItem newCartItem = cartItemRepository.save(cartItem);

		return newCartItem;
	}

	@Override
	public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException {

		CartItem item = findCartItemById(id);
		User user = userService.findUserById(item.getUserId());

		if (item.getUserId().equals(userId)) {
			item.setQuantity(cartItem.getQuantity());
			item.setPrice(item.getProduct().getPrice() * item.getQuantity());
			item.setDiscountedPrice(item.getProduct().getDiscountedPrice() * item.getQuantity());
		}

		return cartItemRepository.save(item);
	}

	@Override
	public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {

		CartItem cartItem = cartItemRepository.isCartItemExist(cart, product, size, userId);

		return cartItem;
	}

	@Override
	public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {

		CartItem cartItem = findCartItemById(cartItemId);
		
		User user = userService.findUserById(userId);
		
		User reqUser = userService.findUserById(userId);
		
		if (user.getId().equals(reqUser.getId())) {
			cartItemRepository.deleteById(cartItemId);
		}else {
			throw new CartItemException("You can't remove other user's cartItem");
		}
	}

	@Override
	public CartItem findCartItemById(Long id) throws CartItemException {

		Optional<CartItem> opt = cartItemRepository.findById(id);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		
		throw new CartItemException("Cart Item not found id:" + id);
	}

}
