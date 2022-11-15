package com.example.ecommerce.shoppingcart;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.product.Product;
import com.example.ecommerce.product.ProductRepository;
import com.example.ecommerce.user.CustomUserDetails;
import com.example.ecommerce.user.User;
import com.example.ecommerce.user.UserRepository;

@Service
@Transactional
public class ShoppingCartServices {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private CartItemRepository cartRepo;
	
	@Autowired
	private ProductRepository productRepo;
	
	public List<CartItem> listCartItems(User user) {
		return cartRepo.findByUser(user);
	}

	public List<CartItem> listCartItems(CustomUserDetails customeUserDetails) {
		User user = userRepo.findByEmail(customeUserDetails.getUsername()) ;
		return cartRepo.findByUser(user);
	}
	
	public Integer addProduct(Integer productId, Integer quantity, User user) {
		Integer addedQuantity = quantity;
		
		Product product = productRepo.findById(productId).get();
		
		CartItem cartItem = cartRepo.findByUserAndProduct(user, product);
		
		if (cartItem != null) {
			addedQuantity = cartItem.getQuantity() + quantity;
			cartItem.setQuantity(addedQuantity);
		} else {
			cartItem = new CartItem();
			cartItem.setQuantity(quantity);
			cartItem.setUser(user);
			cartItem.setProduct(product);
		}
		
		cartRepo.save(cartItem);
		
		return addedQuantity;
	}
	
	public Integer updateQuantity(Integer productId, Integer quantity, User user) {
		cartRepo.updateQuantity(quantity, productId, user.getId());
		Product product = productRepo.findById(productId).get();
		Integer subtotal = product.getPrice() * quantity;
		return subtotal;
	}
	
	public void removeProduct(Integer productId, User user) {
		cartRepo.deleteByCustomerAndProduct(user.getId(), productId);
	}

	
	
}
