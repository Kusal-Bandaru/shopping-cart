package com.shopping.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.cart.entity.CartItem;

/**
 * @author Kusal
 *
 */
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

}
