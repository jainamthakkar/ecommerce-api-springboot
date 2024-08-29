package com.jainam.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	public String ecom;
	
	public HomeController() {
		this.ecom = "Ecommerce Backend is Running!";
	}
	
	@GetMapping("/")
	public String home() {
		return ecom;
	}
}
