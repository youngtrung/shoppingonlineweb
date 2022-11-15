package com.example.ecommerce.shoppingcart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.user.CustomDetailsService;
import com.example.ecommerce.user.CustomUserDetails;

@RestController
public class ShoppingCartRestController {
	
	@Autowired
	private ShoppingCartServices cartServices;
	
	@Autowired
	private CustomDetailsService customerService;
	
	
	@PostMapping("/cart/add/{pid}/{qty}")
	public String addProductToCart(@PathVariable("pid") Integer productId,
									@PathVariable("qty") Integer quantity,
									@AuthenticationPrincipal CustomUserDetails user) {
		
		
		if (user == null) {
			return "You must login to add this product to your shopping cart.";
		} else {
			if (quantity > 0) {
				Integer addedQuantity = cartServices.addProduct(productId, quantity, user.getUser());
				System.out.println("productId:" + productId + " ,quantity:" + quantity);
				return "Item(s) of this product were added to your shopping cart.";
			} else {
				return "You have not chosen the quantity.";
			}
			
			
			
			
		}
		
	}
	
	@PostMapping("/cart/update/{pid}/{qty}")
	public String updateQuantity(@PathVariable("pid") Integer productId,
									@PathVariable("qty") Integer quantity,
									@AuthenticationPrincipal CustomUserDetails user) {
		
		if (user == null) {
			return "You must login to update this product to your shopping cart.";
		} else {
			if (quantity > 0) {
				Integer subtotal = cartServices.updateQuantity(productId, quantity, user.getUser());
				System.out.println("productId:" + productId + " ,quantity:" + quantity);
				return String.valueOf(subtotal);
			} else {
				return "You have not chosen the quantity.";
			}
		}
		
	}
	
	@PostMapping("/cart/remove/{pid}")
	public String removeProductFromCart(@PathVariable("pid") Integer productId,
									@AuthenticationPrincipal CustomUserDetails user) {
		
		if (user == null) {
			return "You must login to remove this product to your shopping cart.";
		} else {
			
				cartServices.removeProduct(productId, user.getUser());
				System.out.println("Remove product:" + productId );
				return "The product has been removed from your shopping cart.";
			
		}
		
	}
	
}
