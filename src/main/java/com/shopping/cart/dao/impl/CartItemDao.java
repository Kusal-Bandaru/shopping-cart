/**
 * 
 */
package com.shopping.cart.dao.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopping.cart.dao.Dao;
import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.CartItem;
import com.shopping.cart.entity.Product;
import com.shopping.cart.repository.CartItemRepository;

/**
 * @author Kusal
 *
 */
@Component
public class CartItemDao implements Dao<CartItem> {
	
	private static final Logger logger = LoggerFactory.getLogger(CartItemDao.class);

	@Autowired
	CartItemRepository cartItemRepository;

	@Override
	public List<CartItem> getAll() {
		return cartItemRepository.findAll();
	}

	@Override
	public Optional<CartItem> get(int id) {
		return cartItemRepository.findById(id);
	}

	@Override
	public CartItem save(CartItem cartItem) {
		return cartItemRepository.save(cartItem);
	}

	@Override
	public CartItem update(CartItem cartItem) {
		CartItem existingCartItem = cartItemRepository.getOne(cartItem.getId());
		logger.info("Fetched existingCartItem - {}", existingCartItem);
		BeanUtils.copyProperties(cartItem, existingCartItem, "id");
		logger.info("updated existingCartItem - {}", existingCartItem);
		return cartItemRepository.save(existingCartItem);
	}

	@Override
	public void delete(int id) {
		cartItemRepository.deleteById(id);
	}

	public Optional<CartItem> fetchIfItemExists(Cart cart, Product product) {
		List<CartItem> cartItemList = cartItemRepository.fetchIfItemExists(cart, product);
		CartItem existingCartItem = null;
		if (!cartItemList.isEmpty()) {
			existingCartItem = cartItemList.get(0);
		}
		return Optional.ofNullable(existingCartItem);
	}

}
