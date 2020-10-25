package com.IcecreamApp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private final PasswordEncoder passwordEncoder;
	
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
		.antMatchers("/", "index", "/css/*", "js/*").permitAll()
		.antMatchers("/products/**", "/categories/**").hasRole(ApplicationUserRole.ADMIN.name())
//		.antMatchers(HttpMethod.DELETE, "/users/**").hasAuthority(ApplicationUserPermission.USER_WRITE.name())
//		.antMatchers(HttpMethod.POST, "/users").hasAuthority(ApplicationUserPermission.USER_WRITE.name())
//		.antMatchers(HttpMethod.PUT, "/users/**").hasAuthority(ApplicationUserPermission.USER_WRITE.name())
//		.antMatchers(HttpMethod.GET, "/users").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.USER.name())
		.anyRequest()
		.authenticated()
		.and()
		// .httpBasic();
		.formLogin()
		.loginPage("/login").permitAll()
		.defaultSuccessUrl("/hello",true)
		.and()
		.rememberMe();
	}
	
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails SanDang = User.builder()
				.username("SanDang")
				.password(this.passwordEncoder.encode("1234"))
				// .roles(ApplicationUserRole.ADMIN.name()) // ROLE_USER
				.authorities(ApplicationUserRole.ADMIN.getGrantedAuthorities())
				.build();
		
		UserDetails JohnDoe = User.builder()
				.username("JohnDoe")
				.password(this.passwordEncoder.encode("password"))
				// .roles(ApplicationUserRole.USER.name()) // ROLE_USER
				.authorities(ApplicationUserRole.USER.getGrantedAuthorities())
				.build();
		return new InMemoryUserDetailsManager(SanDang, JohnDoe);
	}
}
