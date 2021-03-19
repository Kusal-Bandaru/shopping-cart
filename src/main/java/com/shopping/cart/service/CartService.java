package com.shopping.cart.service;

import com.shopping.cart.entity.Cart;
import com.shopping.cart.entity.CartItem;
import com.shopping.cart.exception.CartItemDoesNotExistException;
import com.shopping.cart.exception.CartNotAssociatedException;
import com.shopping.cart.exception.ProductDoesNotExistException;

/**
 * CartService Interface for business logic related to cart operations
 * 
 * @author Kusal
 *
 */
public interface CartService {

	public Cart getCartItemList(int cartId) throws CartNotAssociatedException;

	public CartItem addItem(CartItem cartItem) throws CartNotAssociatedException, ProductDoesNotExistException;

	public CartItem updateItem(CartItem cartItem)
			throws CartNotAssociatedException, ProductDoesNotExistException, CartItemDoesNotExistException;

	public void deleteItem(int itemId);
}
