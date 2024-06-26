package com.jainam.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jainam.ecommerce.exception.ProductException;
import com.jainam.ecommerce.model.Product;
import com.jainam.ecommerce.model.Rating;
import com.jainam.ecommerce.model.User;
import com.jainam.ecommerce.repository.RatingRepository;
import com.jainam.ecommerce.request.RatingRequest;

@Service
public class RatingServiceImplementation implements RatingService {

	@Autowired
	private RatingRepository ratingRepository;
	
	@Autowired
	private ProductService productService;
	
	
	@Override
	public Rating createRating(RatingRequest req, User user) throws ProductException {
		
		Product product = productService.findProductById(req.getProductId());
		
		Rating rating = new Rating();
		
		rating.setProduct(product);
		rating.setUser(user);
		rating.setCreatedAt(LocalDateTime.now());
		
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getProductsRating(Long productId) {
		
		return ratingRepository.getAllProductsRating(productId);
	}

}
