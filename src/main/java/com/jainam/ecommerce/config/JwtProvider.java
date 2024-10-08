package com.jainam.ecommerce.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtProvider {

	private final SecretKey key;

	public JwtProvider() {
		try {
			key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
		} catch (Exception e) {
			throw new RuntimeException("Failed to initialize JWT provider", e);
		}
	}

	public String generateToken(Authentication auth) {

		String jwt = Jwts.builder().setIssuedAt(new Date()).setExpiration(new Date(new Date().getTime() + 846000000))
				.claim("email", auth.getName()).signWith(key).compact();

		return jwt;
	}

	public String getEmailFromToken(String jwt) {
		
		if(jwt != null && jwt.startsWith("Bearer ")) {
			jwt = jwt.substring(7);
		}
		
//		System.out.println("6. " + jwt);

		Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

//		System.out.println("7. " + claims.toString());
		
		String email = String.valueOf(claims.get("email"));
		
//		System.out.println("8. " + email);

		return email;
	}

}
