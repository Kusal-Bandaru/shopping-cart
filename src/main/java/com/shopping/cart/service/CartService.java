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

	/**
	 * Get the cart items list based on cart id.
	 * 
	 * @param cartId
	 * @return Cart
	 * @throws CartNotAssociatedException
	 */
	public Cart getCartItemList(int cartId) throws CartNotAssociatedException;

	/**
	 * Add an item to the cart
	 * 
	 * @param cartItem
	 * @return CartItem
	 * @throws CartNotAssociatedException
	 * @throws ProductDoesNotExistException
	 */
	public CartItem addItem(CartItem cartItem) throws CartNotAssociatedException, ProductDoesNotExistException;

	/**
	 * Update an existing item in the cart
	 * 
	 * @param cartItem
	 * @return CartItem
	 * @throws CartNotAssociatedException
	 * @throws ProductDoesNotExistException
	 * @throws CartItemDoesNotExistException
	 */
	public CartItem updateItem(CartItem cartItem)
			throws CartNotAssociatedException, ProductDoesNotExistException, CartItemDoesNotExistException;

	/**
	 * Delete an item from the cart
	 * 
	 * @param itemId
	 */
	public void deleteItem(int itemId);
}
