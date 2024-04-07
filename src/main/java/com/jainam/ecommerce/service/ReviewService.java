package com.jainam.ecommerce.service;

import java.util.List;

import com.jainam.ecommerce.exception.ProductException;
import com.jainam.ecommerce.model.Review;
import com.jainam.ecommerce.model.User;
import com.jainam.ecommerce.request.ReviewRequest;

public interface ReviewService {

	public Review createReview(ReviewRequest req, User user) throws ProductException;
	
	public List<Review> getAllReview(Long productId);
	
}
