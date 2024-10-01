package com.jainam.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jainam.ecommerce.exception.UserException;
import com.jainam.ecommerce.model.User;
import com.jainam.ecommerce.service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/profile")
	public ResponseEntity<User> getUserProfileHandler(@RequestHeader("Authorization") String jwt) throws UserException{
		
//		System.out.println("1. inside profile: "+jwt);
		
//		User user = userService.findUserById(152l);
		
		User user = userService.findUserProfileByJwt(jwt);
		
//		System.out.println("3. "+ user.getEmail());
		
		return new ResponseEntity<User>(user, HttpStatus.ACCEPTED);
	}
}
