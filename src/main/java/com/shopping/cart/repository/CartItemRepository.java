package com.shopping.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.CartItem;
import com.shopping.cart.entity.Product;

/**
 * CartItemRepository interface for operations on CartItem Entities in DB
 * 
 * @author Kusal
 *
 */
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

	/**
	 * Method to support NamedQuery to fetch the CartItem if exists in DB
	 * 
	 * @param cart
	 * @param product
	 * @return List<CartItem>
	 */
	List<CartItem> fetchIfItemExists(Cart cart, Product product);
}
