package com.IcecreamApp.security.jwt;

import org.springframework.stereotype.Component;

@Component
public class JwtUtils {

	protected String SECRET = "mySecret";
	protected Long EXPIRATION_TIME = (long) 86400000;
	protected String TOKEN_PREFIX = "Bearer";
	protected String HEADER_STRING = "Authorization";
}
