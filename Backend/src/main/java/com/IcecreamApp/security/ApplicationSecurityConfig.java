package com.IcecreamApp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final PasswordEncoder passwordEncoder;
	
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers("/", "/users/*", "index", "/css/*", "js/*").permitAll()
		.antMatchers("/products/**", "/categories/**").hasRole(ApplicationUserRole.ADMIN.name())
		.anyRequest()
		.authenticated()
		.and()
		.httpBasic();
	}
	
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails SanDang = User.builder()
				.username("SanDang")
				.password(this.passwordEncoder.encode("1234"))
				.roles(ApplicationUserRole.ADMIN.name()) // ROLE_USER
				.build();
		
		UserDetails JohnDoe = User.builder()
				.username("JOhnDoe")
				.password(this.passwordEncoder.encode("password"))
				.roles(ApplicationUserRole.USER.name()) // ROLE_USER
				.build();
		return new InMemoryUserDetailsManager(SanDang, JohnDoe);
	}
}
