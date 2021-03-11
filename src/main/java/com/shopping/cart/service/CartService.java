package com.shopping.cart.service;

import java.util.Set;

import com.shopping.cart.entity.CartItem;
import com.shopping.cart.exception.CartNotAssociatedException;

/**
 * @author Kusal
 *
 */
public interface CartService {

	public Set<CartItem> getCartItemList(Long cartId) throws CartNotAssociatedException;

	public CartItem addItem(CartItem cartItem);

	public CartItem updateItem(CartItem cartItem);

	public void deleteItem(Long itemId);
}
