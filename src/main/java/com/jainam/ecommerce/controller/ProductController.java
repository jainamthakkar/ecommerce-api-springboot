package com.jainam.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jainam.ecommerce.exception.ProductException;
import com.jainam.ecommerce.model.Product;
import com.jainam.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public ResponseEntity<Page<Product>> findProductByCategoryHandler(
			@RequestParam(required = false) String category,
		    @RequestParam List<String> color,
		    @RequestParam List<String> size,
		    @RequestParam(required = false) Integer minPrice,
		    @RequestParam(required = false) Integer maxPrice,
		    @RequestParam(required = false) Integer minDiscount,
		    @RequestParam(required = false) String sort,
		    @RequestParam(required = false) String stock,
		    @RequestParam(defaultValue = "0") Integer pageNumber,
		    @RequestParam(defaultValue = "12") Integer pageSize){

//		System.out.println("complete products");
//		System.out.println("Received params: category=" + category + ", color=" + color + ", size=" + size);

		try {
			Page<Product> res = productService.getAllProducts(category, color, size, minPrice, maxPrice, minDiscount,
					sort, stock, pageNumber, pageSize);
			System.out.println("Sending Response: " + new ObjectMapper().writeValueAsString(res));
			return new ResponseEntity<>(res, HttpStatus.ACCEPTED);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("products/id/{productId}")
	public ResponseEntity<Product> findProductByIdHandler(@PathVariable Long productId) throws ProductException {

		Product product = productService.findProductById(productId);

		return new ResponseEntity<Product>(product, HttpStatus.ACCEPTED);
	}

//	@GetMapping("products/search")
//	public ResponseEntity<List<Product>> searchProductHandler(@RequestParam String q) throws ProductException{
//		
//		List<Product> products = productService.searchProduct(q);
//		
//		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
//	}
}
