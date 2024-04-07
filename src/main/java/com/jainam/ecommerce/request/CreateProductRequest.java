package com.jainam.ecommerce.request;

import java.util.HashSet;
import java.util.Set;

import com.jainam.ecommerce.model.Size;

public class CreateProductRequest {

	private String title;

	private String description;

	private int price;

	private Integer discountedPrice;

	private String discountPersent;

	private int quantity;

	private String brand;

	private String color;

	private Set<Size> sizes = new HashSet<>();

	private String imageUrl;

	private String topLevelCategory;

	private String secLevelCategory;

	private String thirdLevelCategory;
	
	public CreateProductRequest() {
		// TODO Auto-generated constructor stub
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Integer getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(Integer discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public String getDiscountPersent() {
		return discountPersent;
	}

	public void setDiscountPersent(String discountPersent) {
		this.discountPersent = discountPersent;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Set<Size> getSizes() {
		return sizes;
	}

	public void setSizes(Set<Size> sizes) {
		this.sizes = sizes;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getTopLevelCategory() {
		return topLevelCategory;
	}

	public void setTopLevelCategory(String topLevelCategory) {
		this.topLevelCategory = topLevelCategory;
	}

	public String getSecLevelCategory() {
		return secLevelCategory;
	}

	public void setSecLevelCategory(String secLevelCategory) {
		this.secLevelCategory = secLevelCategory;
	}

	public String getThirdLevelCategory() {
		return thirdLevelCategory;
	}

	public void setThirdLevelCategory(String thirdLevelCategory) {
		this.thirdLevelCategory = thirdLevelCategory;
	}
	
	
}
