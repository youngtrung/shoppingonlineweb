package com.example.ecommerce.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
public class CustomDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository repo;
	

	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = repo.findByEmail(email);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		return new CustomUserDetails(user);
	}
	
	
	public User getCurrentlyLoggedInUser(Authentication authentication) {
		if (authentication == null) return null;
		User user = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if (principal instanceof CustomUserDetails) {
			String username = ((CustomUserDetails)principal).getUsername();
			user = repo.findByEmail(username);
		} else {
			String username = principal.toString();
			user = repo.findByEmail(username);
		
		}
		return user;
	}
	
	public List<User> getUser() {
		return repo.findAll();
	}
	
}
