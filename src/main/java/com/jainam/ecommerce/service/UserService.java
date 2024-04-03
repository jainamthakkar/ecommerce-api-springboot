package com.jainam.ecommerce.service;

import com.jainam.ecommerce.exception.UserException;
import com.jainam.ecommerce.model.User;

public interface UserService {

	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String Jwt) throws UserException;
}
