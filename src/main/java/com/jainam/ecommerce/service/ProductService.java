package com.jainam.ecommerce.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jainam.ecommerce.exception.ProductException;
import com.jainam.ecommerce.model.Product;
import com.jainam.ecommerce.request.CreateProductRequest;

public interface ProductService {

	public Product createProduct(CreateProductRequest req);
	
	public String deleteProduct(Long id) throws ProductException;
	
	public Product updateProduct(Long productId, Product product) throws ProductException;
	
	public Product findProductById(Long id) throws ProductException;
	
	public List<Product> findProductByCategory(String cat) throws ProductException;
	
	public Page<Product> getAllProducts(String cat, List<String> colors, List<String> sizes, Integer minPrice, Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize);

	public List<Product> findAllProducts();

//	public List<Product> searchProduct(String q);
	
	
}
