package com.IcecreamApp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.IcecreamApp.repository.UserRepository;
import com.IcecreamApp.security.jwt.JwtAuthenticationFilter;
import com.IcecreamApp.security.jwt.JwtAuthorizationFilter;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final ApplicationUserService applicationUserService;
	private final UserRepository userRepository;
	
	public ApplicationSecurityConfig(ApplicationUserService applicationUserService, UserRepository userRepository) {
		this.applicationUserService = applicationUserService;
		this.userRepository = userRepository;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
        // remove csrf and state in session because in jwt we do not need them
        .csrf().disable()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        // add jwt filters (1. authentication, 2. authorization)
        .addFilter(new JwtAuthenticationFilter(authenticationManager()))
        .addFilter(new JwtAuthorizationFilter(authenticationManager(),  this.userRepository))
        .authorizeRequests()
        
		.antMatchers(HttpMethod.POST, "/login").permitAll()
		.antMatchers("/users/**").authenticated()
		.antMatchers("/feedbacks/**").hasAnyRole("ADMIN", "USER")
		.antMatchers("/products/**").hasRole("ADMIN")
		.antMatchers("/categories/**").hasRole("ADMIN")
        .anyRequest().authenticated();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
		.authenticationProvider(this.authenticationProvider());
		
	}
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		daoAuthenticationProvider.setUserDetailsService(this.applicationUserService);
		return daoAuthenticationProvider;
	}
	
	
	@Bean 
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
//	@Override
//	@Bean
//	protected UserDetailsService userDetailsService() {
//		UserDetails SanDang = User.builder()
//				.username("SanDang")
//				.password(this.passwordEncoder.encode("1234"))
//				// .roles(ApplicationUserRole.ADMIN.name()) // ROLE_USER
//				.authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
//				.build();
//		
//		UserDetails JohnDoe = User.builder()
//				.username("JohnDoe")
//				.password(this.passwordEncoder.encode("password"))
//				// .roles(ApplicationUserRole.USER.name()) // ROLE_USER
//				.authorities(ApplicationUserRole.USER.getGrantedAuthorities())
//				.build();
//		return new InMemoryUserDetailsManager(SanDang, JohnDoe);
//	}
}
