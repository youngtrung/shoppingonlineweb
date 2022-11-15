package com.example.ecommerce.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class AppController {
	
	@Autowired
	private UserRepository repo;
	
	@GetMapping("/")
	public String viewHomePage(@AuthenticationPrincipal CustomUserDetails user, Model model) {
		if (user != null) {
			String name = user.getName();
			model.addAttribute("name", name);
			}
		return "index";
	}
	
	@GetMapping("/about")
	public String viewAbout(@AuthenticationPrincipal CustomUserDetails user, Model model) {
		if (user != null) {
			String name = user.getName();
			model.addAttribute("name", name);
			}
		return "about";
	}
	
	@GetMapping("/register") 
	public String showSignUpForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
//	@GetMapping("/laptop")
//	public String showLaptop(@AuthenticationPrincipal CustomUserDetails user, Model model) {
//		if (user != null) {
//			String name = user.getName();
//			model.addAttribute("name", name);
//			}
//		return "laptop";
//	}
//	
//	@GetMapping("/computer")
//	public String showComputer(@AuthenticationPrincipal CustomUserDetails user, Model model) {
//		return "computer";
//	}
//	
	
	
	@GetMapping("/contact")
	public String showContact(@AuthenticationPrincipal CustomUserDetails user, Model model) {
		if (user != null) {
			String name = user.getName();
			model.addAttribute("name", name);
			}
		return "contact";
	}
	
	@GetMapping("/register_success")
	public String showRegSuccess(@AuthenticationPrincipal CustomUserDetails user, Model model) {
		if (user != null) {
			String name = user.getName();
			model.addAttribute("name", name);
			}
		return "register_success";
	}
	
	@PostMapping("/process_register")
	public String processRegistration(User user) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		repo.save(user);
		return "register_success";
	}
	
	@GetMapping("/login") 
	public String login(@AuthenticationPrincipal CustomUserDetails user, Model model) {
		if (user != null) {
			String name = user.getName();
			model.addAttribute("name", name);
			}
		return "login";
	}
}
