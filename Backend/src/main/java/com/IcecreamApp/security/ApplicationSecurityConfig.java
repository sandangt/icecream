package com.IcecreamApp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.IcecreamApp.security.filter.JwtAuthorizationFilter;
import com.IcecreamApp.security.filter.UnauthEntryPointJwt;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final ApplicationUserService applicationUserService;
	private final UnauthEntryPointJwt unauthorizedHandler;
	
	public ApplicationSecurityConfig(ApplicationUserService applicationUserService, 
			UnauthEntryPointJwt unauthorizedHandler) {
		this.applicationUserService = applicationUserService;
		this.unauthorizedHandler = unauthorizedHandler;
	}

	@Bean
	public JwtAuthorizationFilter authorizationJwtTokenFilter() {
		return new JwtAuthorizationFilter();
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        // remove csrf and state in session because in jwt we do not need them
		.cors().and().csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        // add jwt filters (1. authentication, 2. authorization)
        .authorizeRequests()
		.antMatchers(HttpMethod.POST, "/auth/login").permitAll()
		.antMatchers("/users/{userId}/**").access("@WebFilter.checkUserId(authentication,#userId)")
		.antMatchers("/users/**").hasRole("ADMIN")
		.antMatchers("/feedbacks/**").hasAnyRole("ADMIN", "USER")
		.antMatchers(HttpMethod.GET, "/products/**").hasRole("ADMIN")
		.antMatchers(HttpMethod.GET, "/categories/**").hasRole("ADMIN")
        .anyRequest().authenticated()
        .and()
		.logout()
        .logoutUrl("/logout")
        .and()
		.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
		
		http.addFilterBefore(authorizationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(applicationUserService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean 
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
