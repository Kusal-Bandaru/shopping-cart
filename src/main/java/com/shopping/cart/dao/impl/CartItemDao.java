/**
 * 
 */
package com.shopping.cart.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.shopping.cart.dao.Dao;
import com.shopping.cart.entity.CartItem;
import com.shopping.cart.repository.CartItemRepository;

/**
 * @author Kusal
 *
 */
public class CartItemDao implements Dao<CartItem> {

	@Autowired
	CartItemRepository cartItemRepository;

	@Override
	public List<CartItem> getAll() {
		return cartItemRepository.findAll();
	}

	@Override
	public Optional<CartItem> get(long id) {
		return cartItemRepository.findById(id);
	}

	@Override
	public CartItem save(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}

	@Override
	public CartItem update(CartItem cartItem) {
		CartItem existingCartItem = cartItemRepository.getOne(cartItem.getId());
		BeanUtils.copyProperties(cartItem, existingCartItem, "id");
		return cartItemRepository.save(existingCartItem);
	}

	@Override
	public void delete(long id) {
		cartItemRepository.deleteById(id);
	}

}
