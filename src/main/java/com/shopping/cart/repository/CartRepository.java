package com.shopping.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.cart.entity.Cart;

/**
 * CartRepository for operations on Cart entities in DB
 * 
 * @author Kusal
 *
 */
@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

}
