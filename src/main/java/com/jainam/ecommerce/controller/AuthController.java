package com.jainam.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jainam.ecommerce.config.JwtProvider;
import com.jainam.ecommerce.exception.UserException;
import com.jainam.ecommerce.model.User;
import com.jainam.ecommerce.repository.UserRepository;
import com.jainam.ecommerce.request.LoginRequest;
import com.jainam.ecommerce.response.AuthResponse;
import com.jainam.ecommerce.service.CustomUserServiceImplementation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private UserRepository userRepository;
 
	@Autowired
	private JwtProvider jwtProvider;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CustomUserServiceImplementation customUserServiceImplementation;

//	public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder,
//		CustomUserServiceImplementation customUserServiceImplementation) {
//	super();
//	this.userRepository = userRepository;
//	this.passwordEncoder = passwordEncoder;
//	this.customUserServiceImplementation = customUserServiceImplementation;
//}

	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createUserHandler(@RequestBody User user) throws UserException{
		
		String email = user.getEmail();
		String password = user.getPassword();
		String firstName = user.getFirstName();
		String lastName = user.getLastName();
		
		User isEmailExists = userRepository.findByEmail(email);
		
		if(isEmailExists != null) {
			throw new UserException("user already exists: " + email);
		}
		
		User newUser = new User();
		newUser.setEmail(email);
		newUser.setPassword(passwordEncoder.encode(password));
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		
		User savedUser = userRepository.save(newUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.generateToken(authentication);
		
		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Signup Success");
		
		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest) throws UserException {

		String userName = loginRequest.getEmail();
		String password = loginRequest.getPassword();

		Authentication authentication = authenticate(userName, password);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);

		AuthResponse authResponse = new AuthResponse();
		authResponse.setJwt(token);
		authResponse.setMessage("Signin Success");
		
		return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.CREATED);
	}

	private Authentication authenticate(String userName, String password) {

		logger.info("Authenticating user with userName: {}, password: {}", userName, password);
		
		UserDetails userDetails = customUserServiceImplementation.loadUserByUsername(userName);

		logger.info("Loaded UserDetails: {}", userDetails);
		
		if (userDetails == null) {
	        logger.error("Invalid Username: {}", userName);
	        throw new BadCredentialsException("Invalid Username");
	    }

	    // Log the value of userDetails.getPassword()
	    logger.info("Stored password: {}", userDetails.getPassword());

	    if (!passwordEncoder.matches(password, userDetails.getPassword())) {
	        logger.error("Invalid Password for user: {}", userName);
	        throw new BadCredentialsException("Invalid Password");
	    }

	    logger.info("Authentication successful for user: {}", userName);
	    return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

}
