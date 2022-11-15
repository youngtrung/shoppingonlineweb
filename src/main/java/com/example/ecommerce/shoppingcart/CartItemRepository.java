package com.example.ecommerce.shoppingcart;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.ecommerce.product.Product;
import com.example.ecommerce.user.User;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	public List<CartItem> findByUser(com.example.ecommerce.user.User user);
	
	public CartItem findByUserAndProduct(com.example.ecommerce.user.User user, Product product);

	@Modifying
	@Query("UPDATE CartItem c SET c.quantity = ?1 WHERE c.product.id= ?2" + " AND c.user.id = ?3")
	public void updateQuantity(Integer quantity, Integer productId, Integer customerId);
	
	@Query("DELETE FROM CartItem c WHERE c.user.id = ?1 AND c.product.id = ?2")
	@Modifying
	public void deleteByCustomerAndProduct(Integer customerId, Integer productId);
}
