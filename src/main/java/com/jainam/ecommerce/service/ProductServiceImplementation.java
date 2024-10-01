package com.jainam.ecommerce.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jainam.ecommerce.exception.ProductException;
import com.jainam.ecommerce.model.Category;
import com.jainam.ecommerce.model.Product;
import com.jainam.ecommerce.repository.CategoryRepository;
import com.jainam.ecommerce.repository.ProductRepository;
import com.jainam.ecommerce.request.CreateProductRequest;

@Service
public class ProductServiceImplementation implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private UserService userService;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	// ###############################################################
	public Product createProduct(CreateProductRequest req) {

		Category topCategory = categoryRepository.findByName(req.getTopLevelCategory());

		if (topCategory == null) {
			Category topLevelCategory = new Category();
			topLevelCategory.setName(req.getTopLevelCategory());
			topLevelCategory.setLevel(1);

			topCategory = categoryRepository.save(topLevelCategory);
		}

		Category secCategory = categoryRepository.findByName(req.getSecLevelCategory());

		if (secCategory == null) {
			Category secLevelCategory = new Category();
			secLevelCategory.setName(req.getSecLevelCategory());
			secLevelCategory.setLevel(2);

			topCategory = categoryRepository.save(secLevelCategory);
		}

		Category thirdCategory = categoryRepository.findByName(req.getThirdLevelCategory());

		if (thirdCategory == null) {
			Category thirdLevelCategory = new Category();
			thirdLevelCategory.setName(req.getThirdLevelCategory());
			thirdLevelCategory.setLevel(3);

			topCategory = categoryRepository.save(thirdLevelCategory);
		}

		Product product = new Product();
		product.setTitle(req.getTitle());
		product.setColor(req.getColor());
		product.setBrand(req.getBrand());
		product.setCreatedAt(LocalDateTime.now());
		product.setDescription(req.getDescription());
		product.setDiscountPersent(req.getDiscountPersent());
		product.setDiscountedPrice(req.getDiscountedPrice());
		product.setImageUrl(req.getImageUrl());
		product.setQuantity(req.getQuantity());
		product.setSizes(req.getSizes());
		product.setCategory(thirdCategory);
		product.setPrice(req.getPrice());

		Product savedProduct = productRepository.save(product);

		return savedProduct;
	}

	@Override
	public String deleteProduct(Long id) throws ProductException {

		Product product = findProductById(id);
		product.getSizes().clear();
		productRepository.delete(product);

		return "Product Deleted Successfully!!";
	}

	@Override
	public Product updateProduct(Long productId, Product req) throws ProductException {

		Product product = findProductById(productId);

		if (req.getQuantity() != 0) {
			product.setQuantity(req.getQuantity());
		}

		return productRepository.save(product);
	}

	@Override
	public Product findProductById(Long id) throws ProductException {

		Optional<Product> opt = productRepository.findById(id);

		if (opt.isPresent())
			return opt.get();

		throw new ProductException("Product is not found with id: " + id);
	}

	@Override
	public List<Product> findProductByCategory(String cat) throws ProductException {
		// TODO Auto-generated method stub
		return null;
	}

//	###################################################
	@Override
	public Page<Product> getAllProducts(String cat, List<String> colors, List<String> sizes, Integer minPrice,
			Integer maxPrice, Integer minDiscount, String sort, String stock, Integer pageNumber, Integer pageSize) {

//		System.out.println("Filtering products with parameters: " + "category=" + cat + ", colors=" + colors
//				+ ", sizes=" + sizes + ", minPrice=" + minPrice + ", maxPrice=" + maxPrice + ", minDiscount="
//				+ minDiscount + ", sort=" + sort + ", stock=" + stock);

		Pageable pageble = PageRequest.of(pageNumber, pageSize);

		List<Product> products = productRepository.filterProducts(cat, minPrice, maxPrice, minDiscount, sort);

		System.out.println("Found " + products.size() + " products after initial filter");
		if (!colors.isEmpty()) {
			products = products.stream().filter(p -> colors.stream().anyMatch(c -> c.equalsIgnoreCase(p.getColor())))
					.collect(Collectors.toList());
		}

		if (stock != null) {
			if (stock.equals("in_stock")) {
				products = products.stream().filter(p -> p.getQuantity() > 0).collect(Collectors.toList());
			} else if (stock.equals("out_of_stock")) {
				products = products.stream().filter(p -> p.getQuantity() < 1).collect(Collectors.toList());
			}
		}

		int startIndex = (int) pageble.getOffset();
		int endIndex = Math.min(startIndex + pageble.getPageSize(), products.size());

		List<Product> pageContent = products.subList(startIndex, endIndex);

		Page<Product> filteredProducts = new PageImpl<>(pageContent, pageble, products.size());

		return filteredProducts;
	}

	@Override
	public List<Product> findAllProducts() {

		return productRepository.findAll();
	}

}
