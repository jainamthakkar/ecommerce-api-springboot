package com.jainam.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jainam.ecommerce.exception.ProductException;
import com.jainam.ecommerce.model.Product;
import com.jainam.ecommerce.model.Review;
import com.jainam.ecommerce.model.User;
import com.jainam.ecommerce.repository.ReviewRepository;
import com.jainam.ecommerce.request.ReviewRequest;

@Service
public class ReviewServiceImplementation implements ReviewService {

	@Autowired
	private ReviewRepository reviewRepository;
	
	@Autowired
	private ProductService productService;
	
	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		
		Product product = productService.findProductById(req.getProductId());
		
		Review review = new Review();
		
		review.setCreatedAt(LocalDateTime.now());
		review.setProduct(product);
		review.setUser(user);
		review.setReview(req.getReview());
		
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReview(Long productId) {
		
		return reviewRepository.getAllProductsReview(productId);
	}

}
