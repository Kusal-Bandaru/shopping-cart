package com.shopping.cart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.CartItem;
import com.shopping.cart.entity.Product;

/**
 * @author Kusal
 *
 */
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

//	List<CartItem> fetchIfItemExists(Cart cart, Product product);
}
