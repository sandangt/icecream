package com.IcecreamApp.security.jwt;

public class JwtProperties {
	protected static String SECRET = "mySecret";
	protected static Long EXPIRATION_TIME = (long) 86400000;
	protected static String TOKEN_PREFIX = "Bearer";
	protected static String HEADER_STRING = "Authorization";
}
