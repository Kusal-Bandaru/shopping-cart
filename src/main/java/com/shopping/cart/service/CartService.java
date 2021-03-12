package com.shopping.cart.service;

import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.CartItem;
import com.shopping.cart.exception.CartNotAssociatedException;
import com.shopping.cart.exception.ProductDoesNotExistException;

/**
 * @author Kusal
 *
 */
public interface CartService {

	public Cart getCartItemList(Long cartId) throws CartNotAssociatedException;

	public CartItem addItem(CartItem cartItem) throws CartNotAssociatedException, ProductDoesNotExistException;

	public CartItem updateItem(CartItem cartItem);

	public void deleteItem(Long itemId);
}
