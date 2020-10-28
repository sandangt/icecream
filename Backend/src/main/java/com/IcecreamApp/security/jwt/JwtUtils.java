package com.IcecreamApp.security.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.IcecreamApp.security.ApplicationUser;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtUtils {
	
	@Value("${IcecreamApp.jwtSecret}")
	protected String SECRET;
	@Value("${IcecreamApp.jwtExpirationTime}")
	protected Long EXPIRATION_TIME;
	@Value("${IcecreamApp.jwtPrefix}")
	protected String TOKEN_PREFIX;
	@Value("${IcecreamApp.jwtHeader}")
	protected String HEADER_STRING;

	public String generateJwtToken(Authentication authentication) {

		ApplicationUser applicationUser = (ApplicationUser) authentication.getPrincipal();
		try {
			return JWT.create()
				.withIssuer("auth0")
	            .withSubject(applicationUser.getUsername())
	            .withExpiresAt(new Date(System.currentTimeMillis() + this.EXPIRATION_TIME))
	            .sign(Algorithm.HMAC512(this.SECRET.getBytes()));
		}
		catch (JWTCreationException e) {
			e.printStackTrace();
			return null;
		}
		
	}

	public DecodedJWT getDecodedBody(String token) {	
	    if (token != null) {
	    	try {
	    		return JWT.require(Algorithm.HMAC512(this.SECRET.getBytes()))
	                .build()
	                .verify(token);
	    	}
	    	catch (JWTVerificationException e) {
	    		e.printStackTrace();
	    		return null;
	    	}
	    }
	    return null;
	}
	
//	public boolean validateJwtToken(String authToken) {
//		try {
//			JWT.require(algorithm)
//			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
//			return true;
//		} catch (SignatureException e) {
//			logger.error("Invalid JWT signature: {}", e.getMessage());
//		} catch (MalformedJwtException e) {
//			logger.error("Invalid JWT token: {}", e.getMessage());
//		} catch (ExpiredJwtException e) {
//			logger.error("JWT token is expired: {}", e.getMessage());
//		} catch (UnsupportedJwtException e) {
//			logger.error("JWT token is unsupported: {}", e.getMessage());
//		} catch (IllegalArgumentException e) {
//			logger.error("JWT claims string is empty: {}", e.getMessage());
//		}
//
//		return false;
//	}
}
