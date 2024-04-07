package com.jainam.ecommerce.service;

import java.util.List;

import com.jainam.ecommerce.exception.ProductException;
import com.jainam.ecommerce.model.Rating;
import com.jainam.ecommerce.model.User;
import com.jainam.ecommerce.request.RatingRequest;

public interface RatingService {

	public Rating createRating(RatingRequest req, User user) throws ProductException;
	
	public List<Rating> getProductsRating(Long productId);
	
}
