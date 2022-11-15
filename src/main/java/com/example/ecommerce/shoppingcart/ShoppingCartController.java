package com.example.ecommerce.shoppingcart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.user.CustomDetailsService;
import com.example.ecommerce.user.CustomUserDetails;
import com.example.ecommerce.user.User;

@Controller
public class ShoppingCartController{
	
	@Autowired
	private ShoppingCartServices cartServices;
	
	@Autowired
	private CustomDetailsService customerService;
	
	
	
	@GetMapping("/cart")
	public String showShoppingCart(@AuthenticationPrincipal CustomUserDetails user, Model model) {
		
		
//			User user = customerService.getCurrentlyLoggedInUser(authentication);
			List<CartItem> cartItems = cartServices.listCartItems(user);
			if(user != null) {
				String name = user.getName();
				model.addAttribute("name", name);
			}
			
//			
			model.addAttribute("cartItems", cartItems);
//		
//		
		model.addAttribute("pageTitle", "Shopping Cart");
		return "shopping_cart";
		
	}
}
