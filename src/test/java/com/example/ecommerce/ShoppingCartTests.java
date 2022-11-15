package com.example.ecommerce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import java.util.List;

import com.example.ecommerce.product.Product;
import com.example.ecommerce.shoppingcart.CartItem;
import com.example.ecommerce.shoppingcart.CartItemRepository;
import com.example.ecommerce.user.User;



@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class ShoppingCartTests {
	@Autowired
	private CartItemRepository cartRepo;
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Test
	public void testAddOneCartItem() {
		Product product = entityManager.find(Product.class, 4 );
		User user = entityManager.find(User.class, 1);
		CartItem newItem = new CartItem();
		newItem.setUser(user);
		newItem.setProduct(product);
		newItem.setQuantity(4);
		
		CartItem saveCartItem =  cartRepo.save(newItem);
		
		assertTrue(saveCartItem.getId() > 0);
		
		
	}
	
	@Test
	public void testGetCartItemByCustomer() {
		User user = new User();
		user.setId(3);
		
		List<CartItem> cartItems = cartRepo.findByUser(user);
		
		assertEquals(0, cartItems.size());
	}
}
