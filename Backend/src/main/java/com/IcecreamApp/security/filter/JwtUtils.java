package com.IcecreamApp.security.filter;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.IcecreamApp.security.ApplicationUser;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

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

		ApplicationUser userPrincipal = (ApplicationUser) authentication.getPrincipal();

		return Jwts.builder()
				.setSubject((userPrincipal.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + this.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, this.SECRET)
				.compact();
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(this.SECRET).parseClaimsJws(token).getBody().getSubject();
	}

//	public boolean validateJwtToken(String authToken) {
//		try {
//			Jwts.parser().setSigningKey(this.SECRET).parseClaimsJws(authToken);
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
//}

}
