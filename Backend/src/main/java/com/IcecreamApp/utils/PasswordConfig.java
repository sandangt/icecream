package com.IcecreamApp.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordConfig {

	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
