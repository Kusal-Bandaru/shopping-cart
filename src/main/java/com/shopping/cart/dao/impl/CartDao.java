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
 * @author Kusal
 *
 */
@Component
public class CartDao implements Dao<Cart> {

	@Autowired
	CartRepository cartRepository;

	@Override
	public List<Cart> getAll() {
		return cartRepository.findAll();
	}

	@Override
	public Optional<Cart> get(int id) {
		return cartRepository.findById(id);
	}

	@Override
	public Cart save(Cart cart) {
		return cartRepository.save(cart);
	}

	@Override
	public Cart update(Cart cart) {
		Cart existingCart = cartRepository.getOne(cart.getId());
		BeanUtils.copyProperties(cart, existingCart, "id");
		return cartRepository.save(existingCart);
	}

	@Override
	public void delete(int id) {
		cartRepository.deleteById(id);
	}

}
