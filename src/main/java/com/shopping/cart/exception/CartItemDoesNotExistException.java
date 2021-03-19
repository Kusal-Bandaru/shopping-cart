package com.shopping.cart.exception;

/**
 * @author Kusal
 *
 */
public class CartItemDoesNotExistException extends Exception {

	/**
	 * @param message
	 */
	public CartItemDoesNotExistException(String message) {
		super(message);
	}

}
