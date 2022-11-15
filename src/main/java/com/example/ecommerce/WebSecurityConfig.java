package com.example.ecommerce;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;



import com.example.ecommerce.user.CustomDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	
	
	
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeHttpRequests()
		.antMatchers("/list_users", "/cart").authenticated()
		.anyRequest().permitAll()
		.and()
		.formLogin()
		.loginPage("/login")
			.usernameParameter("email")
			.defaultSuccessUrl("/product")
//			.successHandler(new RefererRedirectionAuthenticationSuccessHandler())
			.permitAll()
		.and()
		.logout().logoutSuccessUrl("/").permitAll()
		;
		
		
		return http.build();
	}
}
