package com.example.ecommerce.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.ecommerce.user.CustomUserDetails;

@Controller
public class ProductController {
	
	@Autowired
	ProductRepository productRepo;
	
	@GetMapping("/product")
	public String showProduct(@AuthenticationPrincipal CustomUserDetails user, Model model) {
		if (user != null) {
		String name = user.getName();
		model.addAttribute("name", name);
		}
		model.addAttribute("products", productRepo.findAll());
		return "product_v2";
	}
	
	@GetMapping("/testproduct")
	public String testProduct(Model model) {
		 
		model.addAttribute("products", productRepo.findAll());
		return "test_product_2";
	}
}
