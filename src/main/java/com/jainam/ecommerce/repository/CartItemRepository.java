package com.jainam.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jainam.ecommerce.model.Cart;
import com.jainam.ecommerce.model.CartItem;
import com.jainam.ecommerce.model.Product;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
	@Query("SELECT ci FROM CartItem ci WHERE ci.cart = :cart AND ci.product = :product AND ci.status = :status AND ci.id=:id")
	public CartItem isCartItemExist(@Param("cart") Cart cart, @Param("product") Product product,
			@Param("status") String status, @Param("id") Long id);
}
