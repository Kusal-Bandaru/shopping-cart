package com.shopping.cart.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopping.cart.dao.Dao;
import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.CartItem;
import com.shopping.cart.entity.Product;
import com.shopping.cart.repository.CartItemRepository;

/**
 * CartItemDao class to perform operations on CartItemRepository
 * 
 * @author Kusal
 *
 */
@Component
public class CartItemDao implements Dao<CartItem> {

	/**
	 * Autowiring cartItem repository to perform operations
	 */
	@Autowired
	CartItemRepository cartItemRepository;

	/**
	 * Get all the list of CartItem entities
	 * 
	 * @return List<CartItem>
	 */
	@Override
	public List<CartItem> getAll() {
		return cartItemRepository.findAll();
	}

	/**
	 * Get an Optional of CartItem Entity based on the id
	 * 
	 * @param id
	 * @return Optional<CartItem>
	 */
	@Override
	public Optional<CartItem> get(int id) {
		return cartItemRepository.findById(id);
	}

	/**
	 * Save a CartItem entity
	 * 
	 * @param cartItem
	 * @return CartItem
	 */
	@Override
	public CartItem save(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}

	/**
	 * Update an existing CartItem Entity
	 * 
	 * @param cartItem
	 * @return CartItem
	 */
	@Override
	public CartItem update(CartItem cartItem) {
		CartItem existingCartItem = cartItemRepository.getOne(cartItem.getId());
		BeanUtils.copyProperties(cartItem, existingCartItem, "id");
		return cartItemRepository.save(existingCartItem);
	}

	/**
	 * Delete an existing CartItemEntity
	 * 
	 * @param id
	 */
	@Override
	public void delete(int id) {
		cartItemRepository.deleteById(id);
	}

	/**
	 * Method to fetch the CartItem entity if it already exists.
	 * 
	 * @param cart
	 * @param product
	 * @return Optional<CartItem>
	 */
	public Optional<CartItem> fetchIfItemExists(Cart cart, Product product) {
		List<CartItem> cartItemList = cartItemRepository.fetchIfItemExists(cart, product);
		CartItem existingCartItem = null;
		if (!cartItemList.isEmpty()) {
			existingCartItem = cartItemList.get(0);
		}
		return Optional.ofNullable(existingCartItem);
	}

}
