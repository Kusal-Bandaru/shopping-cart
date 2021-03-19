package com.shopping.cart.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopping.cart.dao.Dao;
import com.shopping.cart.entity.Cart;
import com.shopping.cart.repository.CartRepository;

/**
 * CartDao class to perform operations on CartRepository
 * 
 * @author Kusal
 *
 */
@Component
public class CartDao implements Dao<Cart> {

	/**
	 * Autowiring cart repository to perform operations
	 */
	@Autowired
	CartRepository cartRepository;

	/**
	 * Get all the list of cart entities
	 * 
	 * @return List<Cart>
	 */
	@Override
	public List<Cart> getAll() {
		return cartRepository.findAll();
	}

	/**
	 * Get an Optional of Cart Entity based on the id
	 * 
	 * @param id
	 * @return Optional<Cart>
	 */
	@Override
	public Optional<Cart> get(int id) {
		return cartRepository.findById(id);
	}

	/**
	 * Save a cart entity
	 * 
	 * @param cart
	 * @return Cart
	 */
	@Override
	public Cart save(Cart cart) {
		return cartRepository.save(cart);
	}

	/**
	 * Update an existing cart Entity
	 * 
	 * @param cart
	 * @return Cart
	 */

	@Override
	public Cart update(Cart cart) {
		Cart existingCart = cartRepository.getOne(cart.getId());
		BeanUtils.copyProperties(cart, existingCart, "id");
		return cartRepository.save(existingCart);
	}

	/**
	 * Delete an existing entity
	 * 
	 * @param id
	 */
	@Override
	public void delete(int id) {
		cartRepository.deleteById(id);
	}

}
