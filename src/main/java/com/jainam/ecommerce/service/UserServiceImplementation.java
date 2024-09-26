package com.jainam.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jainam.ecommerce.config.JwtProvider;
import com.jainam.ecommerce.exception.UserException;
import com.jainam.ecommerce.model.User;
import com.jainam.ecommerce.repository.UserRepository;

@Service
public class UserServiceImplementation implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public User findUserById(Long userId) throws UserException {
		
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new UserException("user not found of exception: " + userId);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		
		System.out.println("4. jwt " + jwt);
		
		String email = jwtProvider.getEmailFromToken(jwt);
		
		System.out.println("5. email " + email);
		
		User user = userRepository.findByEmail(email);
		
		if(user==null)
			throw new UserException("User not found: " + email);
		
		return user;
	}

}
